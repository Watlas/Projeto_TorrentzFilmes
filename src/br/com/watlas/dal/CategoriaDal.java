package br.com.watlas.dal;

import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Categoria;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDal<T> implements ICRUD_GENERIC<T> {

    private Connection conexao;
    Categoria categoria = null;

    public CategoriaDal() throws Exception {
        categoria = new Categoria();
        conexao = ConexaoDal.getConexao();

    }
    @Override
    public void Add(T objeto) throws Exception {
        categoria = (Categoria) objeto;
        String sql = "INSERT INTO categorias(cat_iden, cat_nome) " +
                    "VALUES (DEFAULT, ?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,categoria.getCategoria_nome());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM categorias WHERE cat_iden =?s";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void Update(T objeto) throws Exception {
        categoria = (Categoria) objeto;
        String sql = "UPDATE categorias SET cat_nome=? WHERE cat_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, categoria.getCategoria_nome());
            preparedStatement.setObject(2, categoria.getCategoria_nome());
            preparedStatement.executeUpdate();

        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List getAll() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                categoria = new Categoria();
                categoria.setCategoria_iden(rs.getInt("cat_iden"));
                categoria.setCategoria_nome(rs.getString("cat_nome"));
                lista.add(categoria);

            }
        }catch (Exception e){
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        String sql = "SELECT * FROM categorias WHERE cat_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, n);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                categoria.setCategoria_iden(rs.getInt("cat_iden"));
                categoria.setCategoria_nome(rs.getString("cat_nome"));


            }
        }catch (Exception e){

        }
        return categoria;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        String sql = "SELECT * FROM categorias WHERE cat_nome =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                categoria.setCategoria_iden(rs.getInt("cat_iden"));
                categoria.setCategoria_nome(rs.getString("cat_nome"));


            }
        }catch (Exception e){

        }
        return categoria;
    }
}
