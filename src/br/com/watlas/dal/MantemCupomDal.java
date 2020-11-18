package br.com.watlas.dal;

import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Cupom;
import br.com.watlas.modal.MantenCupom;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MantemCupomDal implements ICRUD_GENERIC {

    private Connection conexao;
    MantenCupom mantenCupom = new MantenCupom();


    public MantemCupomDal() throws Exception {
        this.conexao = ConexaoDal.getConexao();
    }


    @Override
    public void add(Object objeto) throws Exception {
        mantenCupom = (MantenCupom) objeto;
        String sql = "INSERT INTO mantem_cupum (mantem_cupom_adm_iden, mantem_cupom_cup_iden)" +
                "VALUES (?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, mantenCupom.getMantemcup_adm_iden().getAdm_iden());
            ps.setInt(2, mantenCupom.getMantemcup_cup_iden().getCupom_iden());
            ps.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void delete(int n) throws Exception {
        String sql = "DELETE FROM mantem_cupum WHERE mantem_cupom_iden=?";
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
       String sql = "UPDATE mantem_cupum SET mantem_cupom_adm_iden=?, mantem_cupom_cup_iden=?" +
               " WHERE mantem_cupom_iden=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, mantenCupom.getMantemcup_adm_iden().getAdm_iden());
            ps.setInt(2, mantenCupom.getMantemcup_cup_iden().getCupom_iden());
            ps.setInt(3, mantenCupom.getMantemCupom_iden());
            ps.executeUpdate();

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {
        AdministradorDal administradordal = new AdministradorDal();
        CupomDal cupomdal = new CupomDal();
        Administrador administrador;
        Cupom cupom;
        String sql = "SELECT * FROM mantem_cupum";
        List<MantenCupom> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                mantenCupom = new MantenCupom();

                mantenCupom.setMantemCupom_iden(rs.getInt("mantem_cupom_iden"));

                //COMP. ADM+MANTEM_CUPOM
                int idadm = rs.getInt("mantem_cupom_adm_iden");
                administrador = (Administrador) administradordal.getById(idadm);
                mantenCupom.setMantemcup_adm_iden(administrador);

                //COMP. ADM+MANTEM_CUPOM
                int idcup = rs.getInt("mantem_cupom_cup_iden");
                cupom =(Cupom) cupomdal.getById(idcup);
                mantenCupom.setMantemcup_cup_iden(cupom);

                lista.add(mantenCupom);
            }
        }catch (Exception e){

        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        AdministradorDal administradordal = new AdministradorDal();
        CupomDal cupomdal = new CupomDal();
        String sql = "SELECT * FROM mantem_cupum WHERE mantem_cupom_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                mantenCupom = new MantenCupom();
                mantenCupom.setMantemCupom_iden(rs.getInt("mantem_cupom_iden"));

                //COMP. ADM+MANTEM_CUPOM
                int idadm = rs.getInt("mantem_cupom_adm_iden");
                Administrador administrador = (Administrador) administradordal.getById(idadm);
                mantenCupom.setMantemcup_adm_iden(administrador);

                //COMP. ADM+MANTEM_CUPOM
                int idcup = rs.getInt("mantem_cupom_cup_iden");
                Cupom cupom =(Cupom) cupomdal.getById(idcup);
                mantenCupom.setMantemcup_cup_iden(cupom);
            }
        }catch (Exception e){

        }
        return mantenCupom;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        return null;
    }
}
