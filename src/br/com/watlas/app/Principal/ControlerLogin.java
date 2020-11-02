package br.com.watlas.app.Principal;

import br.com.watlas.app.Principal.Mainapp;
import br.com.watlas.dal.AdministradorDal;
import br.com.watlas.modal.Administrador;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControlerLogin {
    //textField's da tela de CADASTRO
    public TextField txtCadNome;
    public TextField txtCadEmail;
    public PasswordField txtCadSenha2;
    public PasswordField txtCadSenha;
    //textField's da tela de LOGIN de ADM
    public TextField txtloginNome;
    public PasswordField txtLoginSenha;
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    //OBJETO DE CLASSES CONCRETAS
    private Administrador administrador = null;
    private AdministradorDal administradorDal = null;


    public void fazerLogin(ActionEvent actionEvent) {

        try {
            administrador = new Administrador();
            administradorDal =  new AdministradorDal();
            //
            administrador = (Administrador) administradorDal.getByNome(txtloginNome.getText());
            if(administrador.getSenha().equals(txtLoginSenha.getText())){
                Mainapp.mudarTela("teladeselecao");
            }else{

                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("Não foi possivel fazer o login");
                dialogoErro.setContentText("Senha incorreta");
                dialogoErro.showAndWait();
            }


        } catch (Exception e) {
            dialogoErro.setTitle("Diálogo de Error");
            dialogoErro.setHeaderText("Esse é o cabeçalho...");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }
    }

    public void CasdastroAdm(ActionEvent actionEvent) {
        try {
            Mainapp.mudarTela("cadastraradm");
        } catch (Exception e) {

            dialogoErro.setTitle("Diálogo de Error");
            dialogoErro.setHeaderText("Esse é o cabeçalho...");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }

    }

    public void CriarConta(ActionEvent actionEvent) {

        try {
            administrador = new Administrador();
            administradorDal = new AdministradorDal();
            //setando Objetos
            administrador.setAdm_nome(txtCadNome.getText());
            administrador.setSenha(txtCadSenha.getText());
            administrador.setAdm_email(txtCadEmail.getText());
            if (validaSenha() == false) {
            } else {

                administradorDal.Add(administrador);
            }


        } catch (Exception e) {
            dialogoErro.setTitle("Diálogo de Error");
            dialogoErro.setHeaderText("Esse é o cabeçalho...");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }
    }

    public void VoltarTelaLogin(ActionEvent actionEvent) throws Exception {
        Mainapp.mudarTela("main");
    }

    public boolean validaSenha() {
        if (!txtCadSenha.getText().equals(txtCadSenha2.getText())) {
            dialogoErro.setTitle("Diálogo de Error");
            dialogoErro.setHeaderText("Esse é o cabeçalho...");
            dialogoErro.setContentText("AS SENHA SÃO DEFERENTES");
            dialogoErro.showAndWait();
            return false;
        }
        return true;
    }

    public void entrarGerenciadofilme(ActionEvent actionEvent) {
        try {
            Mainapp.mudarTela("gerenciafilmes");

        }catch (Exception e){

        }
    }

    public void entrarEmgerenciarplanos(ActionEvent actionEvent) {
    }

    public void entrarEmGerenciadorCupom(ActionEvent actionEvent) {
    }

    public void entrarGerenciarUsuario(ActionEvent actionEvent) {
    }
}
