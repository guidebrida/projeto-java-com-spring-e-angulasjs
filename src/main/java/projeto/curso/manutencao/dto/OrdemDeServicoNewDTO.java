package projeto.curso.manutencao.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

public class OrdemDeServicoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate instante;
    private Integer status;
    private Integer clienteId;
    @NotEmpty(message = "Prenchimento obrigatório")
    private String nome;
    @NotEmpty(message = "Prenchimento obrigatório")
    private String marca;
    @NotEmpty(message = "Prenchimento obrigatório")
    private String descricao;
    @NotEmpty(message = "Prenchimento obrigatório")
    private String defeito;
    private String imageUrl;


    public OrdemDeServicoNewDTO() {
    }

    public OrdemDeServicoNewDTO(LocalDate instante, Integer statusId, Integer clienteId, String nome, String marca, String descricao, String defeito, String imageUrl) {
        this.instante = instante;
        this.status = statusId;
        this.clienteId = clienteId;
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.defeito = defeito;
        this.imageUrl = imageUrl;

    }

    public LocalDate getInstante() {
        return instante;
    }

    public void setInstante(LocalDate instante) {
        this.instante = instante;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}





