package projeto.curso.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projeto.curso.manutencao.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe de domninio do cliente com declaração de variaveis, construtores, get e sets, hashcode equals
 */
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String cpfCnpj;
    private String telefone;
    private Integer tipo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Endereco endereco;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<OrdemDeServico> ordemDeServico = new ArrayList<>();

    public Cliente(){

    }

    public Cliente(Integer id, String nome, String email, String cpfCnpj, String telefone, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.tipo = (tipo==null) ? null : tipo.getCod();
    }


    public List<OrdemDeServico> getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(List<OrdemDeServico> ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setCliente(this);
        }
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

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
