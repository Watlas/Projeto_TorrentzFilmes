package br.com.watlas.modal;

public class MantemFilme {
    private int MantemFilme_iden;
    private Administrador mantemFilme_adm_iden;
    private Filme mantemFilme_fil_iden;

    public MantemFilme() {
    }

    public MantemFilme(int mantemFilme_iden, Administrador mantemFilme_adm_iden, Filme mantemFilme_fil_iden) {
        MantemFilme_iden = mantemFilme_iden;
        this.mantemFilme_adm_iden = mantemFilme_adm_iden;
        this.mantemFilme_fil_iden = mantemFilme_fil_iden;
    }

    public int getMantemFilme_iden() {
        return MantemFilme_iden;
    }

    public void setMantemFilme_iden(int mantemFilme_iden) {
        MantemFilme_iden = mantemFilme_iden;
    }

    public Administrador getMantemFilme_adm_iden() {
        return mantemFilme_adm_iden;
    }

    public void setMantemFilme_adm_iden(Administrador mantemFilme_adm_iden) {
        this.mantemFilme_adm_iden = mantemFilme_adm_iden;
    }

    public Filme getMantemFilme_fil_iden() {
        return mantemFilme_fil_iden;
    }

    public void setMantemFilme_fil_iden(Filme mantemFilme_fil_iden) {
        this.mantemFilme_fil_iden = mantemFilme_fil_iden;
    }
}
