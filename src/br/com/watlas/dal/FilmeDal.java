package br.com.watlas.dal;

import br.com.watlas.modal.Categoria;
import br.com.watlas.modal.Filme;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilmeDal<T> implements ICRUD_GENERIC<T> {

    private Connection conexao;
    Filme filme = new Filme();

    public FilmeDal() throws Exception {
        conexao = ConexaoDal.getConexao();
    }


    @Override
    public void Add(T objeto) throws Exception {
        filme = (Filme) objeto;
        String sql = "INSERT INTO filmes (fil_caminho, fil_titulo, fil_ano ,fil_sintopse,fil_capa, fil_cat_iden)" +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, filme.getFilme_caminho());
            ps.setObject(2, filme.getFilme_titulo());
            ps.setObject(3, filme.getFilme_ano());
            ps.setObject(4, filme.getFilme_sintopse());
            ps.setObject(5, filme.getFilme_capa());
            ps.setObject(6, filme.getFilme_cat_iden().getCategoria_iden());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;

        }

    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM filmes WHERE fil_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, n);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Update(T objeto) throws Exception {
        String sql = "UPDATE filmes SET fil_caminho, fil_titulo, fil_ano ,fil_sintopse,fil_capa, fil_cat_iden" +
                "WHERE fil_iden=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, filme.getFilme_caminho());
            ps.setObject(2, filme.getFilme_titulo());
            ps.setObject(3, filme.getFilme_ano());
            ps.setObject(4, filme.getFilme_sintopse());
            ps.setObject(5, filme.getFilme_capa());
            ps.setObject(6, filme.getFilme_cat_iden().getCategoria_iden());
            ps.setObject(7, filme.getFilme_iden());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {

        CategoriaDal categoriaDal = new CategoriaDal();
        String sql = "SELECT * FROM filmes";
        List<Filme> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                filme = new Filme();
                filme.setFilme_ano(rs.getInt("fil_ano"));
                filme.setFilme_caminho(rs.getString("fil_caminho"));
                filme.setFilme_capa(rs.getString("fil_capa"));
                filme.setFilme_iden(rs.getInt("fil_iden"));
                filme.setFilme_sintopse(rs.getString("fil_sintopse"));
                filme.setFilme_titulo(rs.getString("fil_titulo"));
                int id = rs.getInt("fil_cat_iden");
                Categoria categoria = (Categoria) categoriaDal.getById(id);
                filme.setFilme_cat_iden(categoria);
                lista.add(filme);

            }
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        CategoriaDal categoriaDal = new CategoriaDal();
        String sql = "SELECT * FROM filmes WHERE fil_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                filme = new Filme();
                filme.setFilme_ano(rs.getInt("fil_ano"));
                filme.setFilme_caminho(rs.getString("fil_caminho"));
                filme.setFilme_capa(rs.getString("fil_capa"));
                filme.setFilme_iden(rs.getInt("fil_iden"));
                filme.setFilme_sintopse(rs.getString("fil_sintopse"));
                filme.setFilme_titulo(rs.getString("fil_titulo"));
                int id = rs.getInt("fil_cat_iden");
                Categoria categoria = (Categoria) categoriaDal.getById(id);
                filme.setFilme_cat_iden(categoria);
            }
        } catch (Exception e) {

        }

        return filme;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        CategoriaDal categoriaDal = new CategoriaDal();
        String sql = "SELECT * FROM filmes WHERE fil_titulo =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                filme = new Filme();
                filme.setFilme_ano(rs.getInt("fil_ano"));
                filme.setFilme_caminho(rs.getString("fil_caminho"));
                filme.setFilme_capa(rs.getString("fil_capa"));
                filme.setFilme_iden(rs.getInt("fil_iden"));
                filme.setFilme_sintopse(rs.getString("fil_sintopse"));
                filme.setFilme_titulo(rs.getString("fil_titulo"));
                int id = rs.getInt("fil_cat_iden");
                Categoria categoria = (Categoria) categoriaDal.getById(id);
                filme.setFilme_cat_iden(categoria);
            }
        } catch (Exception e) {

        }

        return filme;
    }
}
