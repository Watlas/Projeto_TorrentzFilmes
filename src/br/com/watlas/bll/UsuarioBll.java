package br.com.watlas.bll;

import br.com.watlas.dal.UsuarioDal;
import br.com.watlas.modal.Usuario;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioBll implements ICRUD_GENERIC {


    UsuarioDal dal;

    public UsuarioBll() throws Exception{
        dal = new UsuarioDal();
    }


    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaUsuario((Usuario) objeto);
            dal.add(objeto);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            if(mensagem.contains("insert or update on table \"usuario\" violates foreign key constraint \"usuario_usu_cup_iden_fkey\"")){
                mensagem = "CUPOM NAO EXISTE";
            }
            throw new Exception(mensagem);
        }
    }

    @Override
    public void delete(int n) throws Exception {
        try {

            dal.delete(n);
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
        String invalidos = "'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de usuario inválido!");
            }
        }
        // Verifica se EMAIL é válido
        if (isValidEmailAddressRegex(objeto.getEmail()) == false) {
            throw new Exception("Não foi possível concluir sua solicitação"
                    + "\nO EMAIL informado não é válido");
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

    public static boolean isValidCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

}
