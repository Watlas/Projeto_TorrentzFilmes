package br.com.watlas.bll;

import br.com.watlas.dal.FilmeDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class FilmeBll implements ICRUD_GENERIC {

    FilmeDal dal= null;

    public FilmeBll()throws Exception {
        dal = new FilmeDal();
    }


    @Override
    public void Add(Object objeto) throws Exception {
        try {
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
}
