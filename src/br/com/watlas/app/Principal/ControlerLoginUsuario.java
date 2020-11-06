package br.com.watlas.app.Principal;

import br.com.watlas.bll.UsuarioBll;
import br.com.watlas.modal.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerLoginUsuario implements Initializable {
    public TextField txtUsuarioLogin;
    public PasswordField txtSenhaLogin;
    public CheckBox checkBoxSenha;
    public TextField txtsenhavisible;

    //Concretos
    static Usuario usuario;
    //DLL
    UsuarioBll usuarioBll;
    //util
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            usuario = new Usuario();
            usuarioBll = new UsuarioBll();
        } catch (Exception e) {

        }
    }

    public void vaiEntrar(MouseEvent mouseEvent) throws Exception {
        try {
            usuario = (Usuario) usuarioBll.getByNome(txtUsuarioLogin.getText());
            if (usuario.getSenha().equals(txtSenhaLogin.getText())) {
                Mainapp.mudarTela("filmesUsuario");
            } else {
                erroGeral("SENHA INCORRETA", "A senha está incorreta!");
            }


        } catch (Exception e) {
            erroGeral("ERRO AO FAZER LOGIN", e.getMessage());

        }


    }

    public void vaiVoltar(MouseEvent mouseEvent) throws Exception {

        Mainapp.mudarTela("gerenciarPlanos");
    }


    public void vaiCadastrarUsuario(MouseEvent mouseEvent) throws Exception {
        Mainapp.mudarTela("cadastroDeUsuario");
    }

    public void erroGeral(String cabecario, String erro) {

        dialogoErro.setTitle("ERRO");
        dialogoErro.setHeaderText(cabecario);
        dialogoErro.setContentText(erro);
        dialogoErro.showAndWait();
    }


    public void vaiExibirSenha(ActionEvent actionEvent) {

        if (checkBoxSenha.isSelected()) {
            txtsenhavisible.setText(txtSenhaLogin.getText());
            txtSenhaLogin.managedProperty().bind(checkBoxSenha.selectedProperty().not());
            txtSenhaLogin.visibleProperty().bind(checkBoxSenha.selectedProperty().not());

        } else {
            txtSenhaLogin.setText(txtsenhavisible.getText());
            txtsenhavisible.managedProperty().bind(checkBoxSenha.selectedProperty());
            txtsenhavisible.visibleProperty().bind(checkBoxSenha.selectedProperty());

        }

    }

}
