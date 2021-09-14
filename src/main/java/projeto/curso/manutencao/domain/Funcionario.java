package projeto.curso.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projeto.curso.manutencao.domain.enums.Funcionarios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer funcionario;
    private String usuario;
    private String senha;

    public Funcionario() {
    }

    public Funcionario(Integer id, Funcionarios funcionario, String usuario, String senha) {
        this.id = id;
        this.funcionario = (funcionario==null) ? null : funcionario.getCod();
        this.usuario = usuario;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionarios getFuncionario() {
        return Funcionarios.toEnum(funcionario);
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(funcionario, that.funcionario) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionario, usuario);
    }
}

