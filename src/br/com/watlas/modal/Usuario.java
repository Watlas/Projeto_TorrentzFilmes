package br.com.watlas.modal;

import java.util.Date;

public class Usuario {
    private int usuario_iden;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Cupom cupom;

    public Usuario() {
    }

    public Usuario(int usuario_iden, String nome, String email, String cpf,
                   String senha, Cupom cupom) {
        this.usuario_iden = usuario_iden;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.cupom = cupom;
    }

    public int getUsuario_iden() {
        return usuario_iden;
    }

    public void setUsuario_iden(int usuario_iden) {
        this.usuario_iden = usuario_iden;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_iden=" + usuario_iden +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", cupom=" + cupom +
                '}';
    }
}
