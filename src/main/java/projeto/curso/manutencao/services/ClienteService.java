package projeto.curso.manutencao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.Endereco;
import projeto.curso.manutencao.dto.ClienteDTO;
import projeto.curso.manutencao.dto.ClienteNewDTO;
import projeto.curso.manutencao.repositories.ClienteRepository;
import projeto.curso.manutencao.repositories.EnderecoRepository;
import projeto.curso.manutencao.services.exception.DataIntegrityException;
import projeto.curso.manutencao.services.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<Cliente> findAll() {
        return repo.findAll();
    }


    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(ClienteNewDTO objDto) {
        Cliente cli = new Cliente();

        cli = this.fromDTO(objDto);

        return repo.save(cli);
    }


    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não e possivel excluir esse cliente, pois ele possui endereço e ordem de serviço associados");
        }
    }

    public Cliente update(Cliente obj){
        return repo.save(obj);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO){
        Cliente cliente = this.find(id);
        this.updateDatabase(cliente, clienteDTO);
        return repo.save(cliente);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getEstado());
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfCnpj(), objDto.getTelefone(), objDto.getTipoCliente());
        enderecoRepository.save(end);
        cli.setEndereco(end);
        end.setCliente(cli);
        return cli;
    }

    public void updateDatabase(Cliente cliente, ClienteDTO objDto){
        cliente.setNome(objDto.getNome());
        cliente.setTelefone(objDto.getTelefone());
        cliente.setCpfCnpj(objDto.getCpfCnpj());
        cliente.setEmail(objDto.getEmail());
        cliente.setTipo(objDto.getTipoCliente());
    }

}







