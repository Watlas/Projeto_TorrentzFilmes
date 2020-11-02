package br.com.watlas.modal;

public class Filme {
    private int filme_iden;
    private String filme_titulo;
    private int filme_ano;
    private String filme_caminho;
    private String filme_sintopse;
    private Categoria filme_cat_iden;
    private String filme_capa;

    public Filme() {
    }

    public Filme(int filme_iden, String filme_titulo, int filme_ano,
                 String filme_sintopse, Categoria filme_cat_iden, String filme_caminho, String capa) {
        this.filme_iden = filme_iden;
        this.filme_titulo = filme_titulo;
        this.filme_ano = filme_ano;
        this.filme_sintopse = filme_sintopse;
        this.filme_cat_iden = filme_cat_iden;
        this.filme_caminho = filme_caminho;
        this.filme_capa = capa;
    }

    public int getFilme_iden() {
        return filme_iden;
    }

    public void setFilme_iden(int filme_iden) {
        this.filme_iden = filme_iden;
    }

    public String getFilme_titulo() {
        return filme_titulo;
    }

    public void setFilme_titulo(String filme_titulo) {
        this.filme_titulo = filme_titulo;
    }

    public int getFilme_ano() {
        return filme_ano;
    }

    public void setFilme_ano(int filme_ano) {
        this.filme_ano = filme_ano;
    }

    public String getFilme_caminho() {
        return filme_caminho;
    }

    public void setFilme_caminho(String filme_caminho) {
        this.filme_caminho = filme_caminho;
    }

    public String getFilme_sintopse() {
        return filme_sintopse;
    }

    public void setFilme_sintopse(String filme_sintopse) {
        this.filme_sintopse = filme_sintopse;
    }

    public Categoria getFilme_cat_iden() {
        return filme_cat_iden;
    }

    public void setFilme_cat_iden(Categoria filme_cat_iden) {
        this.filme_cat_iden = filme_cat_iden;
    }

    public String getFilme_capa() {
        return filme_capa;
    }

    public void setFilme_capa(String filme_capa) {
        this.filme_capa = filme_capa;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "filme_iden=" + filme_iden +
                ", filme_titulo='" + filme_titulo + '\'' +
                ", filme_ano=" + filme_ano +
                ", filme_caminho='" + filme_caminho + '\'' +
                ", filme_sintopse='" + filme_sintopse + '\'' +
                ", filme_cat_iden=" + filme_cat_iden +
                ", filme_capa='" + filme_capa + '\'' +
                '}';
    }
}
