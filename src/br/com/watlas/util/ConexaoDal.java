/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watlas.util;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * @author watla
 */
public class ConexaoDal {

    private static Connection connection = null;
    private static ConexaoDal conexao = null;

    private ConexaoDal() throws Exception {

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://192.168.0.111:5432/Projeto_TorrentzFilmes";
        String user = "postgres";
        String password = "171717";

        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);

    }

    public static Connection getConexao() throws Exception {
        try {
            if (conexao == null) {
                conexao = new ConexaoDal();
            }
            return connection;
        } catch (Exception e) {
            throw e;
        }
    }



}
