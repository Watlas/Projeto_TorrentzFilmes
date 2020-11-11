package br.com.watlas.dal;

import br.com.watlas.modal.Contrata;
import br.com.watlas.modal.Plano;
import br.com.watlas.modal.Usuario;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContrataDal<T> implements ICRUD_GENERIC<T> {
    private Connection conexao;
    Contrata contrata = new Contrata();

    public ContrataDal() throws Exception {
        conexao = ConexaoDal.getConexao();
    }

    @Override
    public void Add(T objeto) throws Exception {
        contrata = (Contrata) objeto;
        String sql = "INSERT INTO contrata(con_iden,con_inicio," +
                "con_fim, con_status, con_pla_iden, con_usu_iden)" +
                "VALUES (DEFAULT,?,?,?,?,?)";
        try {
             PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, contrata.getContrato_dataInicio());
            ps.setObject(2, contrata.getContrato_dataFim());
            ps.setObject(3, contrata.getContrato_status());
            ps.setObject(4, contrata.getCon_plano_iden().getPlano_iden());
            ps.setObject(5, contrata.getCon_usuario_iden().getUsuario_iden());
            ps.executeUpdate();


        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM contrata WHERE con_iden=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, n);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void Update(T objeto) throws Exception {
        contrata = (Contrata) objeto;
        String sql = "UPDATE contrata SET con_inicio=?," +
                "con_fim=?, con_status=?, con_pla_iden=?, con_usu_iden=?" +
                "WHERE con_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, contrata.getContrato_dataInicio());
            ps.setObject(2, contrata.getContrato_dataFim());
            ps.setObject(3, contrata.getContrato_status());
            ps.setObject(4, contrata.getCon_plano_iden().getPlano_iden());
            ps.setObject(5, contrata.getCon_usuario_iden().getUsuario_iden());
            ps.setObject(6, contrata.getContrato_iden());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public List getAll() throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        PlanoDal planoDal = new PlanoDal();
        String sql = "SELECT * FROM contrata";
        List<Contrata> lista = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                contrata = new Contrata();
                contrata.setContrato_iden(rs.getInt("con_iden"));
                contrata.setContrato_dataInicio(rs.getDate("con_inicio"));
                contrata.setContrato_dataFim(rs.getDate("con_fim"));
                contrata.setContrato_status(rs.getBoolean("con_status"));

                //COMP. USU+CONTRATA
                int idusu = rs.getInt("con_usu_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                contrata.setCon_usuario_iden(usuarioList);

                //COMP. PLA+CONTRATA
                int udpla = rs.getInt("con_pla_iden");
                Plano planoList = (Plano) planoDal.getById(udpla);
                contrata.setCon_plano_iden(planoList);


                lista.add(contrata);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        PlanoDal planoDal = new PlanoDal();
        String sql = "SELECT * FROM contrata WHERE con_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                contrata.setContrato_iden(rs.getInt("con_iden"));
                contrata.setContrato_dataInicio(rs.getDate("con_inicio"));
                contrata.setContrato_dataFim(rs.getDate("con_fim"));

                //COMP. USU+CONTRATA
                int idusu = rs.getInt("con_usu_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                contrata.setCon_usuario_iden(usuarioList);

                //COMP. PLA+CONTRATA
                int udpla = rs.getInt("con_pla_iden");
                Plano planoList = (Plano) planoDal.getById(udpla);
                contrata.setCon_plano_iden(planoList);


            }
        }catch (Exception e){
            throw e;
        }
        return contrata;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        return null;
    }

    public Object getByIdUsu(int n) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        PlanoDal planoDal = new PlanoDal();
        String sql = "SELECT * FROM contrata WHERE con_usu_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                contrata.setContrato_iden(rs.getInt("con_iden"));
                contrata.setContrato_dataInicio(rs.getDate("con_inicio"));
                contrata.setContrato_dataFim(rs.getDate("con_fim"));

                //COMP. USU+CONTRATA
                int idusu = rs.getInt("con_usu_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                contrata.setCon_usuario_iden(usuarioList);

                //COMP. PLA+CONTRATA
                int udpla = rs.getInt("con_pla_iden");
                Plano planoList = (Plano) planoDal.getById(udpla);
                contrata.setCon_plano_iden(planoList);


            }
        }catch (Exception e){
            throw e;
        }
        return contrata;
    }

    public List getAllusu(int n) throws Exception {
        UsuarioDal usuarioDal = new UsuarioDal();
        PlanoDal planoDal = new PlanoDal();
        String sql = "SELECT * FROM contrata WHERE con_usu_iden ="+n;
        List<Contrata> lista = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                contrata = new Contrata();
                contrata.setContrato_iden(rs.getInt("con_iden"));
                contrata.setContrato_dataInicio(rs.getDate("con_inicio"));
                contrata.setContrato_dataFim(rs.getDate("con_fim"));
                contrata.setContrato_status(rs.getBoolean("con_status"));

                //COMP. USU+CONTRATA
                int idusu = rs.getInt("con_usu_iden");
                Usuario usuarioList = (Usuario) usuarioDal.getById(idusu);
                contrata.setCon_usuario_iden(usuarioList);

                //COMP. PLA+CONTRATA
                int udpla = rs.getInt("con_pla_iden");
                Plano planoList = (Plano) planoDal.getById(udpla);
                contrata.setCon_plano_iden(planoList);


                lista.add(contrata);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
