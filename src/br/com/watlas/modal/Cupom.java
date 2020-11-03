package br.com.watlas.modal;

import java.math.BigDecimal;
import java.sql.Date;

public class Cupom {
    private int cupom_iden;
    private String nome;
    private Date cupom_DataGeracao;
    private Double cupom_porcentagem;

    public Cupom() {
    }

    public Cupom(int cupom_iden, String nome, Date cupom_DataGeracao,
                 Double cupom_porcentagem) {
        this.cupom_iden = cupom_iden;
        this.nome = nome;
        this.cupom_DataGeracao = cupom_DataGeracao;
        this.cupom_porcentagem = cupom_porcentagem;
    }

    public int getCupom_iden() {
        return cupom_iden;
    }

    public void setCupom_iden(int cupom_iden) {
        this.cupom_iden = cupom_iden;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCupom_DataGeracao() {
        return cupom_DataGeracao;
    }

    public void setCupom_DataGeracao(Date cupom_DataGeracao) {
        this.cupom_DataGeracao = cupom_DataGeracao;
    }

    public Double getCupom_porcentagem() {
        return cupom_porcentagem;
    }

    public void setCupom_porcentagem(Double cupom_porcentagem) {
        this.cupom_porcentagem = cupom_porcentagem;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "cupom_iden=" + cupom_iden +
                ", nome='" + nome + '\'' +
                ", cupom_DataGeracao=" + cupom_DataGeracao +
                ", cupom_porcentagem=" + cupom_porcentagem +
                '}';
    }
}
