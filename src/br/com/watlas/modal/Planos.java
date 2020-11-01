package br.com.watlas.modal;

import java.math.BigDecimal;

public class Planos {
    private int plano_iden;
    private BigDecimal plano_preco;
    private String plano_nome;
    private int plano_acessSimutaneo;

    public Planos() {
    }

    public Planos(int plano_acessSimutaneo, int plano_iden, BigDecimal plano_preco, String nome) {
        this.plano_acessSimutaneo = plano_acessSimutaneo;
        this.plano_iden = plano_iden;
        this.plano_preco = plano_preco;
        this.plano_nome = nome;
    }

    public int getPlano_iden() {
        return plano_iden;
    }

    public void setPlano_iden(int plano_iden) {
        this.plano_iden = plano_iden;
    }

    public BigDecimal getPlano_preco() {
        return plano_preco;
    }

    public void setPlano_preco(BigDecimal plano_preco) {
        this.plano_preco = plano_preco;
    }

    public String getNome() {
        return plano_nome;
    }

    public void setNome(String nome) {
        this.plano_nome = nome;
    }

    public String getPlano_nome() {
        return plano_nome;
    }

    public void setPlano_nome(String plano_nome) {
        this.plano_nome = plano_nome;
    }

    public int getPlano_acessSimutaneo() {
        return plano_acessSimutaneo;
    }

    public void setPlano_acessSimutaneo(int plano_acessSimutaneo) {
        this.plano_acessSimutaneo = plano_acessSimutaneo;
    }

    @Override
    public String toString() {
        return "Planos{" +
                "plano_iden=" + plano_iden +
                ", plano_preco=" + plano_preco +
                ", nome='" + plano_nome + '\'' +
                '}';
    }
}
