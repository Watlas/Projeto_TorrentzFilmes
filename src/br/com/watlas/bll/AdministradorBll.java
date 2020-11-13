package br.com.watlas.bll;

import br.com.watlas.dal.AdministradorDal;
import br.com.watlas.modal.Administrador;

import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class AdministradorBll implements ICRUD_GENERIC {

    AdministradorDal dal = null;


    public AdministradorBll() throws Exception {
        dal = new AdministradorDal<>();
    }

    @Override
    public void Add(Object objeto) throws Exception {
        try {
            validaAdministrador((Administrador) objeto);
            dal.Add(objeto);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        try {
            dal.Delete(n);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaAdministrador((Administrador) objeto);
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
            return dal.getByNome(nome);
        } catch (Exception e) {
            throw e;
        }

    }

    public void validaAdministrador(Administrador objeto)throws Exception{
        String nome = objeto.getAdm_nome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de usuario inválido!");
            }
        }
        if (objeto.getSenha().equals("")) {
            throw new Exception("Informe o CPF do Administrador");
        }
        if (objeto.getAdm_email().equals("")) {
            throw new Exception("Informe o Email do Administrador");
        }
        if (objeto.getSenha().equals("")) {
            throw new Exception("Informe a Senha do Administrador");
        }
        if (objeto.getAdm_nome().equals("")) {
            throw new Exception("Informe a nome do Administrador");
        }

        List<Administrador> administradorList = dal.getAll();
        for (Administrador aux : administradorList) {
            if ((objeto.getAdm_iden() != aux.getAdm_iden()) && (objeto.getAdm_nome().toUpperCase().
                    equalsIgnoreCase(aux.getAdm_nome().toUpperCase()))){
                throw new Exception("O nome --> " + objeto.getAdm_nome() + "\nJá existe no cadastro de Administradores!\n");
            }
            if ((objeto.getAdm_iden() != aux.getAdm_iden()) && (objeto.getAdm_email().toUpperCase().
                    equalsIgnoreCase(aux.getAdm_email().toUpperCase()))){
                throw new Exception("O Email --> " + objeto.getAdm_email() + "\nJá existe no cadastro de Administradores!\n");
            }




        }
    }

}
