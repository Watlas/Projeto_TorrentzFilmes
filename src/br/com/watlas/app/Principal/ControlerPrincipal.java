package br.com.watlas.app.Principal;

import javafx.event.ActionEvent;

public class ControlerPrincipal {


    public void vaiLoginAdm(ActionEvent actionEvent) throws Exception{
        Mainapp.mudarTela("main");
    }

    public void vaiLoginUsuario(ActionEvent actionEvent) throws Exception{
        Mainapp.mudarTela("loginUsuario");
    }
}
