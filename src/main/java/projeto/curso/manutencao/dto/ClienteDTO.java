package projeto.curso.manutencao.dto;

import com.amazonaws.services.dynamodbv2.xspec.S;
import projeto.curso.manutencao.domain.Cliente;
import projeto.curso.manutencao.domain.Endereco;
import projeto.curso.manutencao.domain.OrdemDeServico;
import projeto.curso.manutencao.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class ClienteDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String cpfCnpj;
    private String telefone;
    private TipoCliente tipoCliente;
    private List<OrdemDeServico> ordemDeServico;
    private Endereco endereco;



    public ClienteDTO(){
    }

    public ClienteDTO(Cliente obj){
        id  = obj.getId();
        nome =obj.getNome();
        email = obj.getEmail();
        cpfCnpj = obj.getCpfCnpj();
        telefone =obj.getTelefone();
        tipoCliente = obj.getTipo();
        ordemDeServico = obj.getOrdemDeServico();
        endereco = obj.getEndereco();


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }



    public List<OrdemDeServico> getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(List<OrdemDeServico> ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }
}
