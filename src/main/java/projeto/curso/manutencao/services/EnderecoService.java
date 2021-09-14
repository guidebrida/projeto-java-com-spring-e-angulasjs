package projeto.curso.manutencao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.Endereco;
import projeto.curso.manutencao.repositories.EnderecoRepository;
import projeto.curso.manutencao.services.exception.DataIntegrityException;
import projeto.curso.manutencao.services.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repo;


    public Endereco find(Integer id) {
        Optional<Endereco> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Endereço não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
    }

    public Endereco insert(Endereco obj) {
        obj.setId(null);
        return repo.save(obj);
    }

//    public Endereco update(Endereco obj) {
//        Endereco end = find(obj.getId());
//        return repo.save(obj);
//    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não e possivel excluir esse endereço, pois ele possui um cliente");
        }
    }

    public List<Endereco> findAll() {
        return repo.findAll();
    }

}