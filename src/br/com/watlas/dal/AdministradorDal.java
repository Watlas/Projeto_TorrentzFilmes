package br.com.watlas.dal;

import br.com.watlas.modal.Administrador;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdministradorDal<T> implements ICRUD_GENERIC<T> {

    private Connection conexao;
    Administrador administrador = new Administrador();

    public AdministradorDal() throws Exception {
        conexao = ConexaoDal.getConexao();
    }

    @Override
    public void Add(T objeto) throws Exception {
        administrador = (Administrador) objeto;
        String sql = "INSERT INTO administrador(adm_iden, adm_nome, adm_email, adm_senha)" +
                " VALUES (DEFAULT, ?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, administrador.getAdm_nome());
            preparedStatement.setObject(2, administrador.getAdm_email());
            preparedStatement.setObject(3, administrador.getSenha());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM administrador WHERE adm_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void Update(T objeto) throws Exception {
        administrador = (Administrador) objeto;
        String sql = "UPDATE administrador SET adm_nome=?, adm_email=?, adm_senha=? WHERE adm_iden =? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, administrador.getAdm_nome());
            preparedStatement.setObject(2, administrador.getAdm_email());
            preparedStatement.setObject(3, administrador.getSenha());
            preparedStatement.setObject(4, administrador.getAdm_iden());

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List getAll() throws Exception {
        String sql = "SELECT * FROM administrador";
        List<Administrador> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                administrador = new Administrador();
                administrador.setAdm_iden((rs.getInt("adm_iden")));
                administrador.setAdm_email(rs.getString("adm_email"));
                administrador.setAdm_nome(rs.getString("adm_nome"));
                administrador.setSenha(rs.getString("adm_senha"));
                lista.add(administrador);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        String sql = "SELECT * FROM administrador WHERE adm_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                administrador.setAdm_nome(rs.getString("adm_nome"));
                administrador.setAdm_email(rs.getString("adm_email"));
                administrador.setAdm_iden(rs.getInt("adm_iden"));
                administrador.setSenha(rs.getString("adm_senha"));
            }

        } catch (Exception e) {
            throw e;
        }

        return administrador;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        String sql = "SELECT * FROM administrador WHERE adm_nome=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                administrador.setAdm_nome(rs.getString("adm_nome"));
                administrador.setAdm_email(rs.getString("adm_email"));
                administrador.setAdm_iden(rs.getInt("adm_iden"));
                administrador.setSenha(rs.getString("adm_senha"));

            }

        } catch (Exception e) {
            throw e;
        }

        return administrador;
    }


}
