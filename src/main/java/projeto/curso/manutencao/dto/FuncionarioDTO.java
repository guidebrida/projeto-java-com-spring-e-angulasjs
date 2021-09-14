package projeto.curso.manutencao.dto;

import projeto.curso.manutencao.domain.Funcionario;
import projeto.curso.manutencao.domain.enums.Funcionarios;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class FuncionarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Funcionarios funcionarios;
    private String usuario;
    private String senha;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Integer id, String senha, String usuario, Funcionarios tipo) {
        this.id = id;
        this.senha = senha;
        this.usuario = usuario;
        this.funcionarios = tipo;
    }


    public FuncionarioDTO(Funcionario obj) {
        this.id = obj.getId();
        this.funcionarios = obj.getFuncionario();
        this.usuario = obj.getUsuario();
        this.senha = obj.getSenha();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionarios getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionarios funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

