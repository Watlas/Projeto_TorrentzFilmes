package br.com.watlas.modal;

import java.util.Date;

public class Visualiza {
    private int visu_iden;
    private Boolean visu_completo;
    private Date visu_dataVisualizacao;
    private Usuario visu_usuario_iden;
    private Filme visu_filme_iden;

    public Visualiza() {

    }

    public int getVisu_iden() {
        return visu_iden;
    }

    public void setVisu_iden(int visu_iden) {
        this.visu_iden = visu_iden;
    }

    public Boolean getVisu_completo() {
        return visu_completo;
    }

    public void setVisu_completo(Boolean visu_completo) {
        this.visu_completo = visu_completo;
    }

    public Date getVisu_dataVisualizacao() {
        return visu_dataVisualizacao;
    }

    public void setVisu_dataVisualizacao(Date visu_dataVisualizacao) {
        this.visu_dataVisualizacao = visu_dataVisualizacao;
    }

    public Usuario getVisu_usuario_iden() {
        return visu_usuario_iden;
    }

    public void setVisu_usuario_iden(Usuario visu_usuario_iden) {
        this.visu_usuario_iden = visu_usuario_iden;
    }

    public Filme getVisu_filme_iden() {
        return visu_filme_iden;
    }

    public void setVisu_filme_iden(Filme visu_filme_iden) {
        this.visu_filme_iden = visu_filme_iden;
    }
}
