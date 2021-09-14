package projeto.curso.manutencao.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projeto.curso.manutencao.domain.Equipamento;
import projeto.curso.manutencao.dto.EquipamentoDTO;
import projeto.curso.manutencao.repositories.EquipamentoRepository;
import projeto.curso.manutencao.services.exception.DataIntegrityException;
import projeto.curso.manutencao.services.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repo;

    public Equipamento find(Integer id) {
        Optional<Equipamento> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Equipamento não encontrado! Id: " + id + ", Tipo: " + Equipamento.class.getName()));
    }

    public Equipamento insert(Equipamento obj) {
        obj.setId(null);
        return repo.save(obj);
    }


    public Equipamento update(EquipamentoDTO obj, Integer id) {
        Equipamento equipamento = find(id);
        Equipamento equi = fromDTOUpdate(equipamento, obj);
        equi.setId(id);
        return repo.save(equi);
    }


    public Equipamento fromDTO(EquipamentoDTO objDto) {
        return fromDTOUpdate(null, objDto);
    }


    public Equipamento fromDTOUpdate(Equipamento equipamento, EquipamentoDTO objDto) {

        equipamento.setId(null);
        equipamento.setNome(objDto.getNome());
        equipamento.setDefeito(objDto.getDefeito());
        equipamento.setMarca(objDto.getMarca());
        equipamento.setDescricao(objDto.getDescricao());
        return equipamento;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não e possivel excluir esse equipamento, pois ela possui uma ordem de servico associada");
        }    }
    public List<Equipamento> findAll(){
        return repo.findAll();
    }


}
