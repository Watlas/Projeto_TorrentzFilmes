package br.com.watlas.modal;

import java.sql.Array;
import java.util.Date;
import java.util.List;

public class Contrata {

    private int contrato_iden;
    private Date contrato_dataInicio;
    private Date contrato_dataFim;
    private boolean contrato_status;
    private Usuario con_usuario_iden;
    private Planos con_planos_iden;

    public Contrata() {
    }

    public Contrata(int contrato_iden, Date contrato_dataInicio, Date contrato_dataFim,
                    boolean contrato_status, Usuario con_usuario_iden, Planos con_planos_iden) {
        this.contrato_iden = contrato_iden;
        this.contrato_dataInicio = contrato_dataInicio;
        this.contrato_dataFim = contrato_dataFim;
        this.contrato_status = contrato_status;
        this.con_usuario_iden = con_usuario_iden;
        this.con_planos_iden = con_planos_iden;
    }

    public int getContrato_iden() {
        return contrato_iden;
    }

    public void setContrato_iden(int contrato_iden) {
        this.contrato_iden = contrato_iden;
    }

    public Date getContrato_dataInicio() {
        return contrato_dataInicio;
    }

    public void setContrato_dataInicio(Date contrato_dataInicio) {
        this.contrato_dataInicio = contrato_dataInicio;
    }

    public Date getContrato_dataFim() {
        return contrato_dataFim;
    }

    public void setContrato_dataFim(Date contrato_dataFim) {
        this.contrato_dataFim = contrato_dataFim;
    }

    public boolean getContrato_status() {
        return contrato_status;
    }

    public void setContrato_status(boolean contrato_status) {
        this.contrato_status = contrato_status;
    }

    public Usuario getCon_usuario_iden() {
        return con_usuario_iden;
    }

    public void setCon_usuario_iden(Usuario con_usuario_iden) {
        this.con_usuario_iden = con_usuario_iden;
    }

    public Planos getCon_planos_iden() {
        return con_planos_iden;
    }

    public void setCon_planos_iden(Planos con_planos_iden) {
        this.con_planos_iden = con_planos_iden;
    }
}
