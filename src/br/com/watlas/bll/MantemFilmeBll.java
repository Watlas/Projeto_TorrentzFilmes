package br.com.watlas.bll;

import br.com.watlas.dal.MantemFilmeDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class MantemFilmeBll implements ICRUD_GENERIC {

    MantemFilmeDal dal ;

    public MantemFilmeBll()throws Exception{
        dal = new MantemFilmeDal();
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
        return null;
    }
}
