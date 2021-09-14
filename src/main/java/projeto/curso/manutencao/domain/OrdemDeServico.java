package projeto.curso.manutencao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import projeto.curso.manutencao.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Classe de domninio da Ordemdeserviço com declaração de variaveis, construtores, get e sets, hashcode equals
 */
@Entity
public class OrdemDeServico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime instante;
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordemDeServico")
    private List<Equipamento> equipamentos = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    public String imageUrl;
    public OrdemDeServico() {
    }

    public OrdemDeServico(Integer id, Status status, String imageUrl) {
        this.id = id;
        this.instante = LocalDateTime.now();
        this.status = (status == null) ? null : status.getCod();
        this.imageUrl = imageUrl;

    }



    public Status getStatus() {
        return Status.toEnum(status);
    }

    public void setStatus(Status status) {
        if (status != null) {
            this.status = status.getCod();
        }else {
            this.status = 0;
        }

    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemDeServico pedido = (OrdemDeServico) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /*
   @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrdemDeServico{\n");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh/mm/ss");
        sb.append(" ID da OS = ").append(getId());
        sb.append("\n instante = ").append(sdf.format(getInstante().getTime()));
        sb.append("\n status codigo = ").append(getStatus().getCod());
       sb.append("\n status descrição = ").append(getStatus().getDescricao());
       sb.append("\n equipamentos = ").append(getEquipamentos().addAll(getEquipamentos()));
        sb.append("\n cliente = ").append(getCliente().getNome());
        sb.append("\n");
        sb.append('}');

        return sb.toString();
    }
     */



}