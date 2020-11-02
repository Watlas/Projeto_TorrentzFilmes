package br.com.watlas.dal;

import br.com.watlas.modal.Cupom;
import br.com.watlas.modal.Usuario;
import br.com.watlas.util.ConexaoDal;
import br.com.watlas.util.ICRUD_GENERIC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDal implements ICRUD_GENERIC {

    private Connection conexao;
    Usuario usuario = new Usuario();

    public UsuarioDal() throws Exception {
        this.conexao = ConexaoDal.getConexao();
    }

    @Override
    public void Add(Object objeto) throws Exception {
        usuario = (Usuario) objeto;
        String sql = "INSERT INTO usuario(usu_nome, usu_email ,usu_cpf, usu_senha, usu_cup_iden)" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, usuario.getNome());
            ps.setObject(2, usuario.getEmail());
            ps.setObject(3, usuario.getCpf());
            ps.setObject(4, usuario.getSenha());
            ps.setObject(5, usuario.getCupom().getCupom_iden());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void Delete(int n) throws Exception {
        String sql = "DELETE FROM usuario WHERE usu_iden =?";
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
        usuario = (Usuario) objeto;
        String sql = "UPDATE usuario SET usu_nome=?, usu_email=? ,usu_cpf=?, usu_senha=?, usu_cup_iden=?" +
                " WHERE usu_iden =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setObject(1, usuario.getNome());
            ps.setObject(2, usuario.getEmail());
            ps.setObject(3, usuario.getCpf());
            ps.setObject(4, usuario.getSenha());
            ps.setObject(5, usuario.getCupom().getCupom_iden());
            ps.setObject(6, usuario.getUsuario_iden());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List getAll() throws Exception {
        CupomDal dal = new CupomDal();
        String sql = "SELECT * FROM usuario";
        List<Usuario> lista = new ArrayList<>();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setUsuario_iden(rs.getInt("usu_iden"));
                usuario.setCpf(rs.getString("usu_cpf"));
                usuario.setEmail(rs.getString("usu_email"));
                usuario.setSenha(rs.getString("usu_senha"));
                usuario.setNome(rs.getString("usu_nome"));

                //COMP.
                int id = rs.getInt("usu_cup_iden");
                Cupom cupom = (Cupom) dal.getById(id);
                usuario.setCupom(cupom);

                lista.add(usuario);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object getById(int n) throws Exception {
        CupomDal dal = new CupomDal();

        String sql = "SELECT * FROM usuario WHERE usu_iden = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,n);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario_iden(rs.getInt("usu_iden"));
                usuario.setCpf(rs.getString("usu_cpf"));
                usuario.setEmail(rs.getString("usu_email"));
                usuario.setSenha(rs.getString("usu_senha"));
                usuario.setNome(rs.getString("usu_nome"));

                //COMP.
                int id = rs.getInt("usu_cup_iden");
                Cupom cupom = (Cupom) dal.getById(id);
                usuario.setCupom(cupom);
            }
        }catch (Exception e){
            throw e;
        }
        return usuario;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        CupomDal dal = new CupomDal();

        String sql = "SELECT * FROM usuario WHERE usu_nome = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setObject(1,nome);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario_iden(rs.getInt("usu_iden"));
                usuario.setCpf(rs.getString("usu_cpf"));
                usuario.setEmail(rs.getString("usu_email"));
                usuario.setSenha(rs.getString("usu_senha"));
                usuario.setNome(rs.getString("usu_nome"));

                //COMP.
                int id = rs.getInt("usu_cup_iden");
                Cupom cupom = (Cupom) dal.getById(id);
                usuario.setCupom(cupom);
            }
        }catch (Exception e){
            throw e;
        }
        return usuario;
    }

    public List getAllId(int id) throws Exception {
        CupomDal dal = new CupomDal();
        String sql = "SELECT * FROM usuario WHERE usu_iden = ?";
        List<Usuario> lista = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setUsuario_iden(rs.getInt("usu_iden"));
                usuario.setCpf(rs.getString("usu_cpf"));
                usuario.setEmail(rs.getString("usu_email"));
                usuario.setSenha(rs.getString("usu_senha"));
                usuario.setNome(rs.getString("usu_nome"));

                //COMP.
                int ids = rs.getInt("usu_cup_iden");
                Cupom cupom = (Cupom) dal.getById(ids);
                usuario.setCupom(cupom);

                lista.add(usuario);

            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }


}
