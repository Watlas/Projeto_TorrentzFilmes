package br.com.watlas.app.Principal;

import br.com.watlas.bll.CupomBll;
import br.com.watlas.bll.UsuarioBll;
import br.com.watlas.modal.Cupom;
import br.com.watlas.modal.Filme;
import br.com.watlas.modal.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerUsuario_ADM<T> implements Initializable {
    public TextField txtNome;
    public TextField txtEmail;
    public TextField txtCpf;
    public PasswordField txtSenha;
    public PasswordField txtSenhaConfirmar;
    public TableView<T> tableUsuario;
    public TableColumn<Usuario, String> columId;
    public TableColumn<Usuario, String> columNome;
    public TableColumn<Usuario, String> columEmail;
    public TableColumn<Usuario, String> columCpf;
    public TableColumn<Cupom, String> columCupom;
    public ComboBox comboCupom;


    //BLL
    CupomBll cupomBll;
    UsuarioBll usuarioBll;
    //Concretas
    Cupom cupom;
    Usuario usuario;
    //UTEIS
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cupomBll = new CupomBll();
            usuarioBll = new UsuarioBll();
            cupom = new Cupom();
            usuario = new Usuario();
            popularCombox();
            atualizarGrid();
        } catch (Exception e) {

        }
    }


    public void vaiIncluir(ActionEvent actionEvent) {
        try {



            atualizarGrid();
            limparCampos();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO INCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiExcluir(ActionEvent actionEvent) {
        try {

            atualizarGrid();
            limparCampos();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiEditar(ActionEvent actionEvent) {
        try {

            atualizarGrid();
            limparCampos();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EDITAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiVoltar(ActionEvent actionEvent) {
        try {

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO VOLTAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiPesquisarNome(KeyEvent keyEvent) {
        try {

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO PESQUISAR ESSE NOME");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiSetarDadosNoTxt(MouseEvent mouseEvent) {
        try {

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO SETAR DADOS NO TXT");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    private void atualizarGrid() {

        try {
            List<Cupom> listaCupom = cupomBll.getAll();
            ObservableList<Usuario> usuarioObservableList;
            ObservableList<Cupom> cupomObservableList;
            List<Usuario> usuarioList = usuarioBll.getAll();

            columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getNome()));
            columCpf.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCpf()));
            columEmail.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getEmail()));
            columCupom.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getNome()));
        //    columId.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getUsuario_iden()+""));

           // columCupom.
            usuarioObservableList =  FXCollections.observableArrayList(usuarioList);
            cupomObservableList =  FXCollections.observableArrayList(listaCupom);
            tableUsuario.setItems((ObservableList<T>) usuarioObservableList);
            tableUsuario.setItems((ObservableList<T>) cupomObservableList);

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO ATUALIZAR GRID");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }

    }

    private void atualizarGridPesquisa() {
        try {

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO ATUALIZAR GRID");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }


    }
    private void limparCampos(){
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtSenhaConfirmar.setText("");
    }
    private void popularCombox()throws Exception{
        List<Cupom> cupomList = cupomBll.getAll();
        for (Cupom cup : cupomList) {
            comboCupom.getItems().add(cup.getNome());
        }
    }

}
