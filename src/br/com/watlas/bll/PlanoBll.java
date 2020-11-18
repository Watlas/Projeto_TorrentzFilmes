package br.com.watlas.bll;

import br.com.watlas.dal.PlanoDal;
import br.com.watlas.modal.Plano;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class PlanoBll implements ICRUD_GENERIC {

    PlanoDal dal = null;

    public PlanoBll() throws Exception {
        dal = new PlanoDal();
    }

    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaPlano((Plano) objeto);
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
            String m = e.getMessage();
            if(m.contains("update or delete on table \"planos\" violates foreign key constraint \"contrata_con_pla_iden_fkey\" on table \"contrata\"")){
                m = "Existe um usuario cadastrado com esse plano\n" +
                        "não sera possivel apagar!";
            }
            throw new Exception(m);
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaPlano((Plano) objeto);
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

    public List getAllPesquisa(String nome) throws Exception {
        try {
            return dal.getAllPesquisa(nome);
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaPlano(Plano objeto) throws Exception {
        String nome = objeto.getPlano_nome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de plano inválido!");
            }
        }

        if (objeto.getPlano_nome().equals("")) {
            throw new Exception("Informe nome do Plano");
        }
        if (objeto.getPlano_preco() < 0 || objeto.getPlano_preco() > 500) {
            throw new Exception("Preço invalido > 0 e < 500 meu amigo!");
        }
        if (objeto.getPlano_acessSimutaneo() < 1 || objeto.getPlano_acessSimutaneo() > 10) {
            throw new Exception("acesso simutaneo tem que ser maior que 1 e menor que 10!");
        }


        List<Plano> planoList = dal.getAll();
        for (Plano aux : planoList) {
            if ((objeto.getPlano_iden() != aux.getPlano_iden()) && (objeto.getPlano_nome().toUpperCase().
                    equalsIgnoreCase(aux.getPlano_nome().toUpperCase()))) {
                throw new Exception("O Plano --> " + objeto.getPlano_nome() + "\nJá existe no cadastro de planos!\n");
            }


        }
    }
}
