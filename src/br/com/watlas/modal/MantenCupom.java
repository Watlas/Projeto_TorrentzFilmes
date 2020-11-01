package br.com.watlas.modal;

import java.util.Date;
import java.util.List;

public class MantenCupom {
    private Date mantemCupomCriacao;
    private List<Administrador> MantemCup_adm_iden;
    private List<Cupom> MantemCup_cup_iden;

    public MantenCupom() {
    }

    public MantenCupom(Date mantemCupomCriacao, List<Administrador> mantemCup_adm_iden, List<Cupom> mantemCup_cup_iden) {
        this.mantemCupomCriacao = mantemCupomCriacao;
        MantemCup_adm_iden = mantemCup_adm_iden;
        MantemCup_cup_iden = mantemCup_cup_iden;
    }

    public Date getMantemCupomCriacao() {
        return mantemCupomCriacao;
    }

    public void setMantemCupomCriacao(Date mantemCupomCriacao) {
        this.mantemCupomCriacao = mantemCupomCriacao;
    }

    public List<Administrador> getMantemCup_adm_iden() {
        return MantemCup_adm_iden;
    }

    public void setMantemCup_adm_iden(List<Administrador> mantemCup_adm_iden) {
        MantemCup_adm_iden = mantemCup_adm_iden;
    }

    public List<Cupom> getMantemCup_cup_iden() {
        return MantemCup_cup_iden;
    }

    public void setMantemCup_cup_iden(List<Cupom> mantemCup_cup_iden) {
        MantemCup_cup_iden = mantemCup_cup_iden;
    }
}

