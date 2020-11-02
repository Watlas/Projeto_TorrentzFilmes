package br.com.watlas.modal;

public class Administrador {
    private int adm_iden;
    private String adm_nome;
    private String adm_email;
    private String senha;

    public Administrador() {
    }

    public Administrador(int adm_iden, String adm_nome,
                         String adm_email, String senha) {
        this.adm_iden = adm_iden;
        this.adm_nome = adm_nome;
        this.adm_email = adm_email;
        this.senha = senha;
    }

    public int getAdm_iden() {
        return adm_iden;
    }

    public void setAdm_iden(int adm_iden) {
        this.adm_iden = adm_iden;
    }

    public String getAdm_nome() {
        return adm_nome;
    }

    public void setAdm_nome(String adm_nome) {
        this.adm_nome = adm_nome;
    }

    public String getAdm_email() {
        return adm_email;
    }

    public void setAdm_email(String adm_email) {
        this.adm_email = adm_email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "adm_iden=" + adm_iden +
                ", adm_nome='" + adm_nome + '\'' +
                ", adm_email='" + adm_email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }


}
