package br.com.watlas.bll;

import br.com.watlas.dal.UsuarioDal;
import br.com.watlas.modal.Usuario;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class UsuarioBll implements ICRUD_GENERIC {


    UsuarioDal dal;

    public UsuarioBll() throws Exception{
        dal = new UsuarioDal();
    }


    @Override
    public void Add(Object objeto) throws Exception {
        try {
            validaUsuario((Usuario) objeto);
            dal.Add(objeto);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            if(mensagem.contains("insert or update on table \"usuario\" violates foreign key constraint \"usuario_usu_cup_iden_fkey\"")){
                mensagem = "CUPOM NAO EXISTE";
            }
            throw new Exception(mensagem);
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        try {

            dal.Delete(n);
        } catch (Exception e) {
            String m = e.getMessage();
            if(m.contains("update or delete on table \"usuario\" violates foreign key constraint \"contrata_con_usu_iden_fkey\" on table \"contrata\"")){
                m ="Esse Usuario possui um contrato ativo, nao sera possivel excluir";
            }
            if(m.contains("update or delete on table \"usuario\" violates foreign key constraint \"vizualiza_vizu_usuario_iden_fkey\" on table \"vizualiza\"")){
                m = "Esse Usuario possui historico de vizualizaçoes, não sera possivel apagar.";
            }
            throw new Exception(m);
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaUsuario((Usuario) objeto);
            dal.Update(objeto);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {
        try {
            return dal.getAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object getById(int n) throws Exception {
        try {
            return dal.getById(n);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        try {
            if (nome.equals("")){
                throw new Exception("INFORME O NOME DO USUARIO");
            }
            return dal.getByNome(nome);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            if(mensagem.contains("Cannot invoke \"String.equals(Object)\" because the return value of \"br.com.watlas.modal.Usuario.getSenha()")){
                mensagem = "Usuario nao existe no sistema";
            }
            if(mensagem.contains("String.equals(Object)")){
                mensagem = "Usuario nao existe no sistema";
            }
            throw new Exception(mensagem);
        }
    }
    public List getByNomePesquisa(String nome) throws Exception {
        try {
            if (nome.equals("")){
                throw new Exception("INFORME O NOME DO USUARIO");
            }
            return dal.getByNomePesquisa(nome);
        }catch (Exception e){
            throw e;
        }

    }

    public void validaUsuario(Usuario objeto)throws Exception{
        String nome = objeto.getNome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de usuario inválido!");
            }
        }
        if(!objeto.getCpf().matches("[0-9]+")){
            throw new Exception("no campo CPF digite NUMEROS.");
        }
        if (objeto.getCpf().equals("")) {
            throw new Exception("Informe o CPF do usuario");
        }
        if (objeto.getEmail().equals("")) {
            throw new Exception("Informe o Email do usuario");
        }
        if (objeto.getSenha().equals("")) {
            throw new Exception("Informe a Senha do usuario");
        }
        if (objeto.getNome().equals("")) {
            throw new Exception("Informe a Senha do usuario");
        }

        List<Usuario> listaDeUsuario = dal.getAll();
        for (Usuario aux : listaDeUsuario) {
            if ((objeto.getUsuario_iden() != aux.getUsuario_iden()) && (objeto.getNome().toUpperCase().
                    equalsIgnoreCase(aux.getNome().toUpperCase()))){
                throw new Exception("O nome --> " + objeto.getNome() + "\nJá existe no cadastro de usuarios!\n");
            }
            if ((objeto.getUsuario_iden() != aux.getUsuario_iden()) && (objeto.getEmail().toUpperCase().
                    equalsIgnoreCase(aux.getEmail().toUpperCase()))){
                throw new Exception("O Email --> " + objeto.getEmail() + "\nJá existe no cadastro de usuarios!\n");
            }
            if ((objeto.getUsuario_iden() != aux.getUsuario_iden()) && (objeto.getCpf().toUpperCase().
                    equalsIgnoreCase(aux.getCpf().toUpperCase()))){
                throw new Exception("O CPF --> " + objeto.getCpf() + "\nJá existe no cadastro de usuarios!\n");
            }



        }
    }

}
