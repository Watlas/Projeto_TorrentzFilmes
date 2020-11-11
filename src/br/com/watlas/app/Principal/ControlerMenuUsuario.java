package br.com.watlas.app.Principal;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerMenuUsuario implements Initializable {


    public Label labelNomeDousuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            labelNomeDousuario.setText(ControlerLoginUsuario.usuario.getNome());
        }catch (Exception w){

        }
    }


    public void vaiPlanos(ActionEvent actionEvent)throws Exception {
        Mainapp.mudarTela("planoUsuario");
    }

    public void vaiFilmes(ActionEvent actionEvent)throws Exception  {
        Mainapp.mudarTela("filmesUsuario");
    }

    public void vaiVoltar(ActionEvent actionEvent)throws Exception  {
        Mainapp.mudarTela("loginUsuario");
    }
}
