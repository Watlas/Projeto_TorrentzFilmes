package br.com.watlas.modal;

import java.util.Date;
import java.util.List;

public class MantenCupom {
    private int mantemCupom_iden;
    private Administrador mantemcup_adm_iden;
    private Cupom mantemcup_cup_iden;

    public MantenCupom() {
    }

    public MantenCupom(int mantemCupom_iden, Administrador mantemcup_adm_iden, Cupom mantemcup_cup_iden) {
        this.mantemCupom_iden = mantemCupom_iden;
        this.mantemcup_adm_iden = mantemcup_adm_iden;
        this.mantemcup_cup_iden = mantemcup_cup_iden;
    }

    public int getMantemCupom_iden() {
        return mantemCupom_iden;
    }

    public void setMantemCupom_iden(int mantemCupom_iden) {
        this.mantemCupom_iden = mantemCupom_iden;
    }

    public Administrador getMantemcup_adm_iden() {
        return mantemcup_adm_iden;
    }

    public void setMantemcup_adm_iden(Administrador mantemcup_adm_iden) {
        this.mantemcup_adm_iden = mantemcup_adm_iden;
    }

    public Cupom getMantemcup_cup_iden() {
        return mantemcup_cup_iden;
    }

    public void setMantemcup_cup_iden(Cupom mantemcup_cup_iden) {
        this.mantemcup_cup_iden = mantemcup_cup_iden;
    }
}

