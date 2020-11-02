package br.com.watlas.util;

import br.com.watlas.dal.FilmeDal;
import br.com.watlas.dal.UsuarioDal;
import br.com.watlas.dal.VisualizaDal;
import br.com.watlas.modal.*;


import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) throws Exception {

        Usuario usuario = new Usuario();
        UsuarioDal usuarioDal = new UsuarioDal();
        usuario.setUsuario_iden(2);
        usuarioDal.Add(usuario);


        Filme filme = new Filme();
        FilmeDal filmeDal = new FilmeDal();
        Visualiza visualiza = new Visualiza();
        VisualizaDal visualizaDal = new VisualizaDal();
        List<Filme> listaFilme = new ArrayList<>();
        filme.setFilme_iden(1);
        listaFilme.add(filme);
        List<Usuario> listausuario = new ArrayList<>();
        listausuario.add(usuario);

        visualiza.setVisu_filme_iden(listaFilme);
        visualiza.setVisu_usuario_iden(listausuario);
        visualiza.setVisu_dataVisualizacao(new java.sql.Date(new java.util.Date().getTime()));
        visualiza.setVisu_completo(true);
     //   visualizaDal.Add(visualiza);

        List<Visualiza> lista =  visualizaDal.getAll();

        for (Visualiza obj: lista) {
            System.out.println(filmeDal.getById(obj.getVisu_filme_iden().get(lista.size()-1).getFilme_iden()));
           System.out.println(obj.toString());
        }






    }

}
