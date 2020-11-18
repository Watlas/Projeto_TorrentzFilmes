package br.com.watlas.bll;

import br.com.watlas.dal.AdministradorDal;
import br.com.watlas.modal.Administrador;

import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdministradorBll implements ICRUD_GENERIC {

    AdministradorDal dal = null;


    public AdministradorBll() throws Exception {
        dal = new AdministradorDal<>();
    }

    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaAdministrador((Administrador) objeto);
            dal.add(objeto);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(int n) throws Exception {
        try {
            dal.delete(n);
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

        // Verifica se EMAIL é válido
        if (isValidEmailAddressRegex(objeto.getAdm_email()) == false) {
            throw new Exception("Não foi possível concluir sua solicitação"
                    + "\nO EMAIL informado não é válido");
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

    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }


}
