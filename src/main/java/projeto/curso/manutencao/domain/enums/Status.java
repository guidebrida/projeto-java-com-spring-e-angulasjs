package projeto.curso.manutencao.domain.enums;

public enum Status {
    FAZENDOORCAMENENTO(0, "FAZENDO_ORCAMENTO"),
    AGUARDANDOAPROVACAO(1, "AGUARDANDO_APROVACAO"),
    APROVADO(2, "APROVADO"),
    REJEITADO(3, "REJEITADO"),
    CONCLUIDO(4, "CONCLUIDO");

    private int cod;
    private String descricao;


    Status(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Status x : Status.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
