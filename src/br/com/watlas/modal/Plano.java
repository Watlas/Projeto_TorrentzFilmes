package br.com.watlas.modal;

import java.math.BigDecimal;

public class Plano {
    private int plano_iden;
    private double plano_preco;
    private String plano_nome;
    private int plano_acessSimutaneo;

    public Plano() {
    }

    public Plano(int plano_acessSimutaneo, int plano_iden, Double plano_preco, String nome) {
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

    public double getPlano_preco() {
        return plano_preco;
    }

    public void setPlano_preco(double plano_preco) {
        this.plano_preco = plano_preco;
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
        return "Plano{" +
                "plano_iden=" + plano_iden +
                ", plano_preco=" + plano_preco +
                ", nome='" + plano_nome + '\'' +
                '}';
    }
}
