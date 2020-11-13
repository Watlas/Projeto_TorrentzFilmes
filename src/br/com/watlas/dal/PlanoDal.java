package br.com.watlas.dal;


import br.com.watlas.modal.Plano;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanoDal implements ICRUD_GENERIC {
    private Connection conexao;
    Plano plano = new Plano();

    public PlanoDal() throws Exception {
        conexao = ConexaoDal.getConexao();
    }

    @Override
    public void Add(Object objeto) throws Exception {
        plano = (Plano) objeto;
        String sql = "INSERT INTO planos(pla_preco, pla_acesso_simutaneo, pla_nome)" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, plano.getPlano_preco());
            ps.setObject(2, plano.getPlano_acessSimutaneo());
            ps.setObject(3, plano.getPlano_nome());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM planos WHERE pla_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Update(Object objeto) throws Exception {
        plano = (Plano) objeto;
        String sql = "UPDATE planos SET pla_preco=?, pla_acesso_simutaneo=?, pla_nome=?" +
                "WHERE pla_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, plano.getPlano_preco());
            ps.setObject(2, plano.getPlano_acessSimutaneo());
            ps.setObject(3, plano.getPlano_nome());
            ps.setObject(4, plano.getPlano_iden());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List getAll() throws Exception {
        String sql = "SELECT * FROM planos";
        List<Plano> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                plano = new Plano();
                plano.setPlano_acessSimutaneo(rs.getInt("pla_acesso_simutaneo"));
                plano.setPlano_iden(rs.getInt("pla_iden"));
                plano.setPlano_nome(rs.getString("pla_nome"));
                plano.setPlano_preco(rs.getDouble("pla_preco"));
                lista.add(plano);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        String sql = "SELECT * FROM planos WHERE pla_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1, n);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                plano = new Plano();
                plano.setPlano_acessSimutaneo(rs.getInt("pla_acesso_simutaneo"));
                plano.setPlano_iden(rs.getInt("pla_iden"));
                plano.setPlano_nome(rs.getString("pla_nome"));
                plano.setPlano_preco(rs.getDouble("pla_preco"));
            }
        } catch (Exception e) {
            throw e;
        }


        return plano;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        String sql = "SELECT * FROM planos WHERE pla_nome=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                plano = new Plano();
                plano.setPlano_acessSimutaneo(rs.getInt("pla_acesso_simutaneo"));
                plano.setPlano_iden(rs.getInt("pla_iden"));
                plano.setPlano_nome(rs.getString("pla_nome"));
                plano.setPlano_preco(rs.getDouble("pla_preco"));
            }
        } catch (Exception e) {
            throw e;
        }


        return plano;
    }
    public List getAllId(int iden) throws Exception {
        String sql = "SELECT * FROM planos WHERE pla_iden=?";
        List<Plano> lista = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1,iden);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                plano = new Plano();
                plano.setPlano_acessSimutaneo(rs.getInt("pla_acesso_simutaneo"));
                plano.setPlano_iden(rs.getInt("pla_iden"));
                plano.setPlano_nome(rs.getString("pla_nome"));
                plano.setPlano_preco(rs.getDouble("pla_preco"));
                lista.add(plano);

            }
        } catch (Exception e) {
            throw e;

        }
        return lista;
    }
    public List getAllPesquisa(String nome) throws Exception {
        String sql = "SELECT * FROM planos WHERE pla_nome LIKE '%"+nome+"%'";
        List<Plano> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                plano = new Plano();
                plano.setPlano_acessSimutaneo(rs.getInt("pla_acesso_simutaneo"));
                plano.setPlano_iden(rs.getInt("pla_iden"));
                plano.setPlano_nome(rs.getString("pla_nome"));
                plano.setPlano_preco(rs.getDouble("pla_preco"));
                lista.add(plano);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
