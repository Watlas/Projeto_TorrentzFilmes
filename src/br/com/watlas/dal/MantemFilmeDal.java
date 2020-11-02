package br.com.watlas.dal;

import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Filme;
import br.com.watlas.modal.MantemFilme;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MantemFilmeDal implements ICRUD_GENERIC {

    private Connection conexao;
    MantemFilme mantemFilme = new MantemFilme();

    public MantemFilmeDal() throws Exception {
        this.conexao = ConexaoDal.getConexao();
    }


    @Override
    public void Add(Object objeto) throws Exception {
        mantemFilme = (MantemFilme) objeto;
        String sql = "INSERT INTO mantem_filme (mantem_filme_adm_iden, mantem_filme_fil_iden)" +
                "VALUES (?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, mantemFilme.getMantemFilme_adm_iden().getAdm_iden());
            ps.setInt(2, mantemFilme.getMantemFilme_fil_iden().getFilme_iden());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM mantem_filme WHERE mantem_filme_iden =?";
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
        String sql = "UPDATE mantem_filme SET mantem_filme_adm_iden=?, mantem_filme_fil_iden=?" +
                "WHERE mantem_filme_iden =? ";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, mantemFilme.getMantemFilme_adm_iden().getAdm_iden());
            ps.setInt(2, mantemFilme.getMantemFilme_fil_iden().getFilme_iden());
            ps.setInt(3, mantemFilme.getMantemFilme_iden());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {
        FilmeDal filmeDal = new FilmeDal();
        AdministradorDal administradorDal = new AdministradorDal();

        String sql = "SELECT * FROM mantem_filme";
        List<MantemFilme> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                mantemFilme = new MantemFilme();

                //COMP. FILME+MANTEM_FILME
                int idfil = rs.getInt("vizu_Filmes_iden");
                Filme filme = (Filme) filmeDal.getById(idfil);
                mantemFilme.setMantemFilme_fil_iden(filme);

                //COMP. ADM+MANTEM_FILME
                int idadm = rs.getInt("mantem_filme_adm_iden");
                Administrador administrador = (Administrador) administradorDal.getById(idadm);
                mantemFilme.setMantemFilme_adm_iden(administrador);
                lista.add(mantemFilme);

            }
        } catch (Exception e) {

            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        FilmeDal filmeDal = new FilmeDal();
        AdministradorDal administradorDal = new AdministradorDal();
        String sql = "SELECT * FROM mantem_filme WHERE mantem_filme_iden = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                mantemFilme = new MantemFilme();

                //COMP. FILME+MANTEM_FILME
                int idfil = rs.getInt("vizu_Filmes_iden");
                Filme filme = (Filme) filmeDal.getById(idfil);
                mantemFilme.setMantemFilme_fil_iden(filme);

                //COMP. ADM+MANTEM_FILME
                int idadm = rs.getInt("mantem_filme_adm_iden");
                Administrador administrador = (Administrador) administradorDal.getById(idadm);
                mantemFilme.setMantemFilme_adm_iden(administrador);
            }
        }catch (Exception e){
            throw e;
        }
        return mantemFilme;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        return null;
    }
}
