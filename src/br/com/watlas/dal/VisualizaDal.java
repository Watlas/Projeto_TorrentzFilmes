package br.com.watlas.dal;

import br.com.watlas.modal.Filme;
import br.com.watlas.modal.Usuario;
import br.com.watlas.modal.Visualiza;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisualizaDal implements ICRUD_GENERIC {

    private Connection conexao;
    Visualiza visualiza = new Visualiza();

    public VisualizaDal() throws Exception {
        conexao = ConexaoDal.getConexao();
    }


    @Override
    public void Add(Object objeto) throws Exception {
        visualiza = (Visualiza) objeto;
        String sql = "INSERT INTO vizualiza (viz_completo, viz_data_vizualizacao ,vizu_usuario_iden, vizu_Filmes_iden) " +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, visualiza.getVisu_completo());
            ps.setObject(2, visualiza.getVisu_dataVisualizacao());
            ps.setObject(3, visualiza.getVisu_usuario_iden().getUsuario_iden());
            ps.setObject(4, visualiza.getVisu_filme_iden().getFilme_iden());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM vizualiza WHERE vis_iden";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, n);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void Update(Object objeto) throws Exception {
        visualiza = (Visualiza) objeto;
        String sql = "UPDATE vizualiza SET viz_completo=?, viz_data_vizualizacao =?,vizu_usuario_iden=?, vizu_Filmes_iden =?" +
                "WHERE vis_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, visualiza.getVisu_completo());
            ps.setObject(2, visualiza.getVisu_dataVisualizacao());
            ps.setObject(3, visualiza.getVisu_usuario_iden().getUsuario_iden());
            ps.setObject(4, visualiza.getVisu_filme_iden().getFilme_iden());
            ps.setObject(5, visualiza.getVisu_iden());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public List getAll() throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        FilmeDal filmeDal = new FilmeDal();
        String sql = "SELECT * FROM vizualiza";
        List<Visualiza> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                visualiza = new Visualiza();
                visualiza.setVisu_iden(rs.getInt("vis_iden"));
                visualiza.setVisu_completo(rs.getBoolean("viz_completo"));
                visualiza.setVisu_dataVisualizacao(rs.getDate("viz_data_vizualizacao"));

                //COMP. DE USUARIO+VISU
                int idusu = rs.getInt("vizu_usuario_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                visualiza.setVisu_usuario_iden(usuarioList);

                //COMP. DE FILME + VISU
                int idfil = rs.getInt("vizu_filmes_iden");
                Filme filmeList = (Filme) filmeDal.getById(idfil);
                visualiza.setVisu_filme_iden(filmeList);

                lista.add(visualiza);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        FilmeDal filmeDal = new FilmeDal();
        String sql = "SELECT * FROM vizualiza WHERE vis_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                visualiza.setVisu_completo(rs.getBoolean("viz_completo"));
                visualiza.setVisu_dataVisualizacao(rs.getDate("viz_data_vizualizacao"));

                //COMP. DE USUARIO+VISU

                int idusu = rs.getInt("vizu_usuario_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                visualiza.setVisu_usuario_iden(usuarioList);

                //COMP. DE FILME + VISU

                int idfil = rs.getInt("vizu_Filmes_iden");
                Filme filmeList = (Filme) filmeDal.getById(idfil);
                visualiza.setVisu_filme_iden(filmeList);
            }

        }catch (Exception e){
            throw e;
        }
        return visualiza;
    }

    public Object getFilmeUsuario(int usu_iden, int usu_film) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        FilmeDal filmeDal = new FilmeDal();
        String sql = "SELECT * FROM vizualiza WHERE vizu_filmes_iden = ? AND vizu_usuario_iden = ? AND viz_completo = false";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,usu_iden);
            preparedStatement.setObject(2,usu_film);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                visualiza.setVisu_completo(rs.getBoolean("viz_completo"));
                visualiza.setVisu_dataVisualizacao(rs.getDate("viz_data_vizualizacao"));

                //COMP. DE USUARIO+VISU

                int idusu = rs.getInt("vizu_usuario_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                visualiza.setVisu_usuario_iden(usuarioList);

                //COMP. DE FILME + VISU

                int idfil = rs.getInt("vizu_Filmes_iden");
                Filme filmeList = (Filme) filmeDal.getById(idfil);
                visualiza.setVisu_filme_iden(filmeList);
            }

        }catch (Exception e){
            throw e;
        }
        return visualiza;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        return null;
    }

    public List getAllUsu(int n) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        FilmeDal filmeDal = new FilmeDal();
        String sql = "SELECT * FROM vizualiza WHERE vizu_usuario_iden="+n;
        List<Visualiza> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                visualiza = new Visualiza();
                visualiza.setVisu_iden(rs.getInt("vis_iden"));
                visualiza.setVisu_completo(rs.getBoolean("viz_completo"));
                visualiza.setVisu_dataVisualizacao(rs.getDate("viz_data_vizualizacao"));

                //COMP. DE USUARIO+VISU
                int idusu = rs.getInt("vizu_usuario_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                visualiza.setVisu_usuario_iden(usuarioList);

                //COMP. DE FILME + VISU
                int idfil = rs.getInt("vizu_filmes_iden");
                Filme filmeList = (Filme) filmeDal.getById(idfil);
                visualiza.setVisu_filme_iden(filmeList);

                lista.add(visualiza);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
