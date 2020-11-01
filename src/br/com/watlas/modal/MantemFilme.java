package br.com.watlas.modal;

public class MantemFilme {

    private Administrador mantemFilme_adm_iden;
    private Filme mantemFilme_fil_iden;

    public MantemFilme() {
    }

    public MantemFilme(Administrador mantemFilme_adm_iden, Filme mantemFilme_fil_iden) {
        this.mantemFilme_adm_iden = mantemFilme_adm_iden;
        this.mantemFilme_fil_iden = mantemFilme_fil_iden;
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
