package br.com.watlas.bll;

import br.com.watlas.dal.VisualizaDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class VizualizaBll implements ICRUD_GENERIC {

    VisualizaDal dal = null;

    public VizualizaBll() throws Exception{
        dal = new VisualizaDal();
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

    public Object getFilmeUsuario(int usu_iden, int usu_film) throws Exception {
        try {
            return dal.getFilmeUsuario(usu_iden,usu_film );
        }catch (Exception e){
            throw e;
        }
    }
    public List getAllUsu(int n) throws Exception {
        try {
                return dal.getAllUsu(n);
        }catch (Exception e){
            throw e;
        }
    }
}
