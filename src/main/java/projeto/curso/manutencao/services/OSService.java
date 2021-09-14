package projeto.curso.manutencao.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.Equipamento;
import projeto.curso.manutencao.domain.OrdemDeServico;
import projeto.curso.manutencao.domain.enums.Status;

import projeto.curso.manutencao.dto.OrdemDeServicoDTO;
import projeto.curso.manutencao.dto.OrdemDeServicoNewDTO;
import projeto.curso.manutencao.dto.OrdemDeServicoStatusDTO;
import projeto.curso.manutencao.repositories.EquipamentoRepository;
import projeto.curso.manutencao.repositories.OSRepository;

import projeto.curso.manutencao.services.exception.DataIntegrityException;
import projeto.curso.manutencao.services.exception.ObjectNotFoundException;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository repo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.damage.size}")
    private Integer size;

    @Value("${img.prefix.os.damage}")
    private String prefix;

    /**
     * Método para buscar ordens de servico.
     *
     * @param id identificador da busca
     * @return retorna o arquivo buscado ou uma exception
     */
    public OrdemDeServico find(Integer id) {
        Optional<OrdemDeServico> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de serviço não encontrado! Id: " + id + ", Tipo: " + OrdemDeServico.class.getName()));
    }


    @Transactional
    public OrdemDeServico insert(OrdemDeServicoNewDTO objDto) {
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        ordemDeServico = this.fromDTO(objDto);
        return repo.save(ordemDeServico);
    }


    /**
     * o find(obj.getId()) busca o metodo acima o find para aproveitar o ObjectNotFoundException
     *
     * @param obj objeto da ordem de serviço
     * @return atualiza ou retorna uma exception
     */
    public OrdemDeServico update(OrdemDeServico obj) {
        OrdemDeServico newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);//busca o metodo get localizado a cima e se acha deleta se não joga a exceção abaixo
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não e possivel excluir essa ordem de serviço, pois ela possui equipamentos associados");
        }
    }

    /**
     * busca todos os arquivos salvos no banco de dados atraves do metodo findall
     *
     * @return retorna todos os arquivos encontrados
     */
    public List<OrdemDeServico> findAll() {
        return repo.findAll();
    }

    public OrdemDeServico fromDTO(OrdemDeServicoDTO objDto) {
        return new OrdemDeServico(objDto.getId(), null, null);
    }

    public OrdemDeServico fromDTO(OrdemDeServicoNewDTO objDto) {
        OrdemDeServico ord = new OrdemDeServico();
        Cliente cli = clienteService.find(objDto.getClienteId());
        Equipamento eq = new Equipamento(null, objDto.getNome(), objDto.getMarca(), objDto.getDescricao(), objDto.getDefeito());
        ord.setInstante(LocalDateTime.now());
        ord.setStatus(Status.toEnum(objDto.getStatus()));
        ord.setCliente(cli);
        ord.setImageUrl(objDto.getImageUrl());
        equipamentoRepository.save(eq);
        eq.setOrdemDeServico(ord);
        ord.setEquipamentos(eq.getOrdemDeServico().getEquipamentos());
        return ord;
    }

    private void updateData(OrdemDeServico newObj, OrdemDeServico obj) {
        newObj.setInstante(obj.getInstante());
        newObj.setCliente(obj.getCliente());
        newObj.setEquipamentos(obj.getEquipamentos());
        newObj.setImageUrl(obj.getImageUrl());
    }

//certo
    public URI uploadDamagePictures(MultipartFile multipartFile, Integer id) {
        OrdemDeServico ordemDeServico  = this.find(id);
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + ordemDeServico.getId() +    ".jpg";
        URI Danos = s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
        ordemDeServico.setImageUrl(Danos.toString());
        repo.save(ordemDeServico);
        return Danos;
    }

    public OrdemDeServico updateStatus(OrdemDeServicoStatusDTO obj, Integer id) {
        OrdemDeServico newObj = find(id);
         newObj.setStatus(Status.toEnum(obj.getStatus()));
        if (newObj.getStatus() == Status.AGUARDANDOAPROVACAO)
            emailService.sendOrderConfirmationHtmlEmail(newObj);
        return repo.save(newObj);
    }

    public OrdemDeServico aprovarStatus(Integer id){
        OrdemDeServico obj = find(id);
        obj.setStatus(Status.APROVADO);
        return repo.save(obj);
    }

    public OrdemDeServico rejeitarStatus(Integer id){
        OrdemDeServico obj = find(id);
        obj.setStatus(Status.REJEITADO);
        return repo.save(obj);
    }

}