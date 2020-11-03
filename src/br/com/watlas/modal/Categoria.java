package br.com.watlas.modal;

public class Categoria {
    private int categoria_iden;
    private String categoria_nome;

    public Categoria() {
    }

    public Categoria(int categoria_iden, String categoria_nome) {
        this.categoria_iden = categoria_iden;
        this.categoria_nome = categoria_nome;
    }

    public int getCategoria_iden() {
        return categoria_iden;
    }

    public void setCategoria_iden(int categoria_iden) {
        this.categoria_iden = categoria_iden;
    }

    public String getCategoria_nome() {
        return categoria_nome;
    }

    public void setCategoria_nome(String categoria_nome) {
        this.categoria_nome = categoria_nome;
    }

    @Override
    public String toString() {
        return
                categoria_iden +
                "-" + categoria_nome;
    }
}
