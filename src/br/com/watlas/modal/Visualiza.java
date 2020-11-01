package br.com.watlas.modal;

import java.util.Date;
import java.util.List;

public class Visualiza {
    private int visu_iden;
    private Boolean visu_completo;
    private Date visu_dataVisualizacao;
    private List<Usuario> visu_usuario_iden;
    private List<Filme> visu_filme_iden;

    public Visualiza() {

    }

    public Visualiza(int visu_iden, Boolean visu_completo,  List<Usuario> visu_usuario_iden, List<Filme> visu_filme_iden) {
        this.visu_iden = visu_iden;
        this.visu_completo = visu_completo;
        this.visu_usuario_iden = visu_usuario_iden;
        this.visu_filme_iden = visu_filme_iden;
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

    public List<Usuario> getVisu_usuario_iden() {
        return visu_usuario_iden;
    }

    public void setVisu_usuario_iden(List<Usuario> visu_usuario_iden) {
        this.visu_usuario_iden = visu_usuario_iden;
    }

    public List<Filme> getVisu_filme_iden() {
        return visu_filme_iden;
    }

    public void setVisu_filme_iden(List<Filme> visu_filme_iden) {
        this.visu_filme_iden = visu_filme_iden;
    }
}
