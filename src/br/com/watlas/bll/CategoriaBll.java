package br.com.watlas.bll;

import br.com.watlas.dal.CategoriaDal;
import br.com.watlas.modal.Categoria;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;

public class CategoriaBll implements ICRUD_GENERIC {

    CategoriaDal dal;

    public CategoriaBll() throws Exception {
        dal = new CategoriaDal();
    }

    @Override
    public void add(Object objeto) throws Exception {
        try {
            validaCategoria((Categoria) objeto);
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
            if (m.contains("update or delete on table \"categorias\" violates foreign key constraint \"filmes_fil_cat_iden_fkey\" on table \"filmes\"")) {
                m = "EXISTE UM FILME CADASTRADO COM ESSA CATEGORIA\n" +
                        " NAO É POSSIVEL APAGAR.";
            }
            throw new Exception(m);
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        try {
            validaCategoria((Categoria) objeto);
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

    public void validaCategoria(Categoria objeto) throws Exception {
        String nome = objeto.getCategoria_nome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome da categoria inválido!");
            }
        }

        if (objeto.getCategoria_nome().equals("")) {
            throw new Exception("Coloca o nome da categoria ai mano");
        }


        List<Categoria> listaDeUsuario = dal.getAll();
        for (Categoria aux : listaDeUsuario) {
            if ((objeto.getCategoria_iden() != aux.getCategoria_iden()) && (objeto.getCategoria_nome().toUpperCase().
                    equalsIgnoreCase(aux.getCategoria_nome().toUpperCase()))) {
                throw new Exception("A categoria --> " + objeto.getCategoria_nome() + "\nJá existe no cadastro de Categorias!\n");
            }


        }

    }
}
