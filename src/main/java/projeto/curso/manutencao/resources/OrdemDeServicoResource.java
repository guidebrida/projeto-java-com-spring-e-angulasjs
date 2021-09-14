package projeto.curso.manutencao.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.OrdemDeServico;
import projeto.curso.manutencao.domain.enums.Status;
import projeto.curso.manutencao.dto.ClienteNewDTO;
import projeto.curso.manutencao.dto.OrdemDeServicoNewDTO;
import projeto.curso.manutencao.dto.OrdemDeServicoDTO;
import projeto.curso.manutencao.dto.OrdemDeServicoStatusDTO;
import projeto.curso.manutencao.services.OSService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/ordemdeservicos")
public class OrdemDeServicoResource {

    @Autowired
    private OSService service;

    /**
     * resource para buscar a ordem de serviço no banco
     * ResponseEntity e um metodo do spring que encapsula mensagem em http
     *
     * @param id identificador da busca
     * @return
     */
    @PreAuthorize("hasAnyRole('TECNICO' ,'ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        OrdemDeServico obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * resource para postar a ordem de serviço no banco
     *
     * @param objDto objeto da ordem de serviço que e inserido numa id vazia
     * @return retorna
     */
    @PreAuthorize("hasAnyRole('RECEPCIONISTA', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody OrdemDeServicoNewDTO objDto) {
        OrdemDeServico obj = service.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    /**
     * atualiza as informações da ordem de serviço
     *
     * @param objDto ele busca o parametro antigo
     * @param id     serve para mostrar qual o endpoint do arquivo que sera editado
     * @return retorna o arquivo atualizado
     */
    @PreAuthorize("hasAnyRole('TECNICO' ,'ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody OrdemDeServicoDTO objDto, @PathVariable Integer id) {
        OrdemDeServico obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /**
     * deleta buscando o id
     *
     * @param id id para achar onde deve ser deletado
     * @return deve retornar um no content pois o arquivo foi deletado
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * busca todos os arquivos atraves de lista
     *
     * @return retorna uma lista de ordens de serviço
     */
        @GetMapping
        public ResponseEntity<List<OrdemDeServicoDTO>> findAll() {
        List<OrdemDeServico> list = service.findAll();
        List<OrdemDeServicoDTO> listDto = list.stream().map(obj -> new OrdemDeServicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('TECNICO' ,'ADMIN')")
    @PostMapping("/{id}/picture")
    public ResponseEntity<Void> uploadDamage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable Integer id) {
        URI uri = service.uploadDamagePictures(multipartFile, id);
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('TECNICO' ,'ADMIN')")
    @PutMapping(value = "/{id}/status")
    public ResponseEntity<Void> updateStatus(@Valid @RequestBody OrdemDeServicoStatusDTO ordemDeServicoStatusDTO, @PathVariable Integer id) {
        OrdemDeServico obj = service.updateStatus(ordemDeServicoStatusDTO, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/aprovar")
    public ResponseEntity<Void> aprovar(@PathVariable Integer id) {
        OrdemDeServico obj = service.aprovarStatus(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/rejeitar")
    public ResponseEntity<Void> rejeitar(@PathVariable Integer id) {
        OrdemDeServico obj = service.rejeitarStatus(id);
        return ResponseEntity.noContent().build();
    }

}
