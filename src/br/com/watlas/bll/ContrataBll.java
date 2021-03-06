package br.com.watlas.bll;

import br.com.watlas.dal.ContrataDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class ContrataBll implements ICRUD_GENERIC {

    ContrataDal dal = null;

    public ContrataBll()throws Exception{
        dal = new ContrataDal();
    }

    @Override
    public void add(Object objeto) throws Exception {
        try {

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
    public Object getByIdusu(int n) throws Exception {
        try {
            return dal.getByIdUsu(n);
        }catch (Exception e){
            throw e;
        }
    }

    public List getAllusu(int n) throws Exception {
        try {
            return dal.getAllusu(n);
        } catch (Exception e) {
            throw e;
        }
    }





}
