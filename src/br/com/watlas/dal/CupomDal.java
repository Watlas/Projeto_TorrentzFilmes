package br.com.watlas.dal;

import br.com.watlas.modal.Cupom;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CupomDal implements ICRUD_GENERIC {
    private Connection conexao;
    Cupom cupom = new Cupom();


    public CupomDal() throws Exception{
        this.conexao = ConexaoDal.getConexao();
    }


    @Override
    public void Add(Object objeto) throws Exception {
        cupom = (Cupom) objeto;
        String sql = "INSERT INTO cupons(cup_data_geracao,cup_porcentagem,cup_nome)" +
                " VALUES (?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, cupom.getCupom_DataGeracao());
            ps.setObject(2, cupom.getCupom_porcentagem());
            ps.setObject(3, cupom.getNome());
            ps.executeUpdate();

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM cupons WHERE cup_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, n);
            ps.executeUpdate();

        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public void Update(Object objeto) throws Exception {
        cupom = (Cupom) objeto;
        String sql = "UPDATE cupons SET cup_data_geracao=?,cup_porcentagem=?,cup_nome=?" +
                "WHERE cup_iden=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, cupom.getCupom_DataGeracao());
            ps.setObject(2, cupom.getCupom_porcentagem());
            ps.setObject(3, cupom.getNome());
            ps.setObject(4, cupom.getCupom_iden());
            ps.executeUpdate();

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {
        String sql = "SELECT * FROM cupons";
        List<Cupom> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                cupom = new Cupom();
                cupom.setCupom_DataGeracao(rs.getDate("cup_data_geracao"));
                cupom.setCupom_porcentagem(rs.getBigDecimal("cup_porcentagem"));
                cupom.setNome(rs.getString("cup_nome"));
                cupom.setCupom_iden(rs.getInt("cup_iden"));
                lista.add(cupom);


            }
        }catch (Exception e){
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        String sql = "SELECT * FROM cupons WHERE cup_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                cupom = new Cupom();
                cupom.setCupom_iden(rs.getInt("cup_iden"));
                cupom.setNome(rs.getString("cup_nome"));
                cupom.setCupom_DataGeracao(rs.getDate("cup_data_geracao"));
                cupom.setCupom_porcentagem(rs.getBigDecimal("cup_porcentagem"));

            }
        }catch (Exception e){
            throw e;
        }
        return cupom;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        String sql = "SELECT * FROM cupons WHERE cup_nome =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                cupom = new Cupom();
                cupom.setCupom_iden(rs.getInt("cup_iden"));
                cupom.setNome(rs.getString("cup_nome"));
                cupom.setCupom_DataGeracao(rs.getDate("cup_data_geracao"));
                cupom.setCupom_porcentagem(rs.getBigDecimal("cup_porcentagem"));

            }
        }catch (Exception e){
            throw e;
        }
        return cupom;
    }
}
