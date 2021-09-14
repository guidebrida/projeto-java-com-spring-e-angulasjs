package projeto.curso.manutencao.domain.enums;

public enum Funcionarios {
    RECEPCIONISTA(1,"ROLE_RECEPCIONISTA"),
    TECNICO(2, "ROLE_TECNICO"),
    ADMIN(3, "ROLE_ADMIN");


    private int cod;
    private String descricao;

    Funcionarios(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Funcionarios toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Funcionarios x : Funcionarios.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
