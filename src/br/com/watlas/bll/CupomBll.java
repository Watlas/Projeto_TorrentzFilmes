package br.com.watlas.bll;

import br.com.watlas.dal.CupomDal;
import br.com.watlas.modal.Cupom;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class CupomBll implements ICRUD_GENERIC {

    CupomDal dal = null;

    public CupomBll()throws Exception{
        dal = new CupomDal();
    }


    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaCupom((Cupom) objeto);
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
            if(m.contains("update or delete on table \"cupons\" violates foreign key constraint \"usuario_usu_cup_iden_fkey\" on table \"usuario\"")){
                m ="Existe Usuario(s) cadastrado(s) com esse cupom\n" +
                        " não é possivel apaga-lo";
            }
            throw new Exception(m);
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaCupom((Cupom) objeto);
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

    public void validaCupom(Cupom objeto)throws Exception{


        if (objeto.getNome().equals("")) {
            throw new Exception("Informe o nome do Cupom");
        }
        if (objeto.getCupom_porcentagem() < 1 || objeto.getCupom_porcentagem() > 60) {
            throw new Exception("Cupom tem que ser porcentagem > 1& e < 60%");
        }


        List<Cupom> cupomList = dal.getAll();
        for (Cupom aux : cupomList) {
            if ((objeto.getCupom_iden() != aux.getCupom_iden()) && (objeto.getNome().toUpperCase().
                    equalsIgnoreCase(aux.getNome().toUpperCase()))){
                throw new Exception("O nome --> " + objeto.getNome() + "\nJá existe no cadastro de Cupons!\n");
            }
            if ((objeto.getCupom_iden() != aux.getCupom_iden()) && objeto.getCupom_porcentagem() == aux.getCupom_porcentagem()){
                throw new Exception("Ja existe um cupom com esse desconto, não faz sentido cadastrar.");
            }


        }
    }
}
