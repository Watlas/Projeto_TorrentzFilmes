package br.com.watlas.app.Principal;

import br.com.watlas.bll.CupomBll;
import br.com.watlas.bll.UsuarioBll;
import br.com.watlas.modal.Cupom;
import br.com.watlas.modal.Usuario;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlerCadastroDeUsuario implements Initializable {
    public TextField txtNome;
    public TextField txtEmail;
    public TextField txtCpf;
    public PasswordField txtSenha;
    public PasswordField txtSenhaConfirmar;
    public TextField txtCupom;

    //Concretas
    Usuario usuario;
    Cupom cupom;
    //Bll
    UsuarioBll usuarioBll;
    CupomBll cupomBll;
    //util
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cupom = new Cupom();
            cupomBll = new CupomBll();
            usuario = new Usuario();
            usuarioBll = new UsuarioBll();
        } catch (Exception e) {

        }
    }

    public void vaiCriarUsuario(MouseEvent mouseEvent) {
        try {
            if (txtNome.getText().contains(" ")){
                throw new Exception("O nome nao pode conter espaços");
            }
            if (txtSenha.getText().equals(txtSenhaConfirmar)) {
                erroGeral("SENHAS NAO CONFEREM", "AS SENHAS DEVEM SER IGUAIS");
            } else {
                usuario.setNome(txtNome.getText());
                usuario.setCpf(txtCpf.getText());
                usuario.setSenha(txtSenha.getText());
                usuario.setEmail(txtEmail.getText());
                if(txtCupom.getText().equals("")){
                    cupom = (Cupom) cupomBll.getByNome("nenhum");
                }else{
                    cupom = (Cupom) cupomBll.getByNome(txtCupom.getText());
                }
                usuario.setCupom(cupom);
                usuarioBll.Add(usuario);
                limparCampos();
                Mainapp.mudarTela("loginUsuario");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            erroGeral("ERRO AO CRIAR CONTA", e.getMessage());

        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSenha.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenhaConfirmar.setText("");
    }

    public void vaiVoltar(MouseEvent mouseEvent) throws Exception {
        Mainapp.mudarTela("loginUsuario");
    }

    public void erroGeral(String cabecario, String erro) {
        dialogoErro.setTitle("ERRO");
        dialogoErro.setHeaderText(cabecario);
        dialogoErro.setContentText(erro);
        dialogoErro.showAndWait();
    }
}
