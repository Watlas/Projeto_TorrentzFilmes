package br.com.watlas.bll;

import br.com.watlas.dal.FilmeDal;
import br.com.watlas.modal.Filme;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class FilmeBll implements ICRUD_GENERIC {

    FilmeDal dal= null;

    public FilmeBll()throws Exception {
        dal = new FilmeDal();
    }


    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaFilme((Filme) objeto);
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
            if(m.contains("update or delete on table \"filmes\" violates foreign key constraint \"vizualiza_vizu_filmes_iden_fkey\" on table \"vizualiza\"")){
                m = "Um ou mais Usuarios Vizualizaram esse filme, nao sera\n " +
                        "possivel apagar.";
            }
            throw new Exception(m);
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaFilme((Filme) objeto);
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
    public List getAllPesquisa(String fil) throws Exception {
        try {
return  dal.getAllPesquisa(fil);
        }catch (Exception e){
            throw e;
        }
    }

    public void validaFilme(Filme objeto)throws Exception{

        if (objeto.getFilme_titulo().equals("")) {
            throw new Exception("Informe o Titulo do Filme");
        }
        if (objeto.getFilme_capa().equals("")) {
            throw new Exception("Informe a capa do Filme");
        }
        if (objeto.getFilme_ano() >2020 ||  objeto.getFilme_ano() < 1895) {
            throw new Exception("Ano invalido");
        }

        if (objeto.getFilme_caminho().equals("")) {
            throw new Exception("Informe o caminho do filme");
        }
        if (objeto.getFilme_sintopse().equals("")) {
            throw new Exception("Informe a sintopse do filme");
        }
        if(objeto.getFilme_cat_iden().getCategoria_nome().equals("")){
            throw new Exception("Informe a categoria do Filme");
        }

        List<Filme> listaDeFilme = dal.getAll();
        for (Filme aux : listaDeFilme) {
            if ((objeto.getFilme_iden() != aux.getFilme_iden()) && (objeto.getFilme_titulo().toUpperCase().
                    equalsIgnoreCase(aux.getFilme_titulo().toUpperCase()))){
                throw new Exception("O filme --> " + objeto.getFilme_titulo() + "\nJá existe no cadastro de Filmes!\n");
            }
            if ((objeto.getFilme_iden() != aux.getFilme_iden()) && (objeto.getFilme_caminho().toUpperCase().
                    equalsIgnoreCase(aux.getFilme_caminho().toUpperCase()))){
                throw new Exception("O caminho Já existe no cadastro de Filmes!\n");
            }
            if ((objeto.getFilme_iden() != aux.getFilme_iden()) && (objeto.getFilme_capa().toUpperCase().
                    equalsIgnoreCase(aux.getFilme_capa().toUpperCase()))){
                throw new Exception("essa capa Já existe no cadastro de Filmes!\n");
            }



        }
    }
}
