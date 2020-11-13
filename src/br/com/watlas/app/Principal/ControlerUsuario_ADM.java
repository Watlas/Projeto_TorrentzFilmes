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

public class ControlerUsuario_ADM implements Initializable {
    public TextField txtNome;
    public TextField txtEmail;
    public TextField txtCpf;
    public PasswordField txtSenha;
    public PasswordField txtSenhaConfirmar;
    public TableView<Usuario> tableUsuario;
    public TableColumn<Usuario, String> columId;
    public TableColumn<Usuario, String> columNome;
    public TableColumn<Usuario, String> columEmail;
    public TableColumn<Usuario, String> columCpf;
    public TableColumn<Usuario, String> columCupom;
    public ComboBox comboCupom;
    public TextField txtPesquisa;


    //BLL
    CupomBll cupomBll;
    UsuarioBll usuarioBll;
    //Concretas
    Cupom cupom;
    Usuario usuario;
    //UTEIS
    private int id;
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
            usuario.setNome(txtNome.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setSenha(txtSenha.getText());
            usuario.setCpf(txtCpf.getText());
            if(comboCupom.getValue() == null){
                erroGeral("PREENCHA O CAMPO CUPOM", "Escolha algum cupom para esse\nUsuario!");
            }else{
                cupom = (Cupom) cupomBll.getByNome(comboCupom.getValue().toString());
                usuario.setCupom(cupom);
                if (!txtSenha.getText().equals(txtSenhaConfirmar.getText())) {
                    erroGeral("ERRO AO INCLUIR", "Senhas \nnao conferem");
                } else {
                    usuarioBll.Add(usuario);
                    dialogoInfo.setTitle("INFORMAÇÃO");
                    dialogoInfo.setHeaderText("USUARIO ADICIONADO");
                    dialogoInfo.setContentText("Usuario " + usuario.getNome() + " foi adicionado!");
                    dialogoInfo.showAndWait();


                    atualizarGrid();
                    limparCampos();
                }


            }


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO INCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiExcluir(ActionEvent actionEvent) {
        try {
            usuarioBll.Delete(id);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("USUARIO APAGADO");
            dialogoInfo.setContentText("Usuario " + usuario.getNome() + " foi excluido!");
            dialogoInfo.showAndWait();

            atualizarGrid();
            limparCampos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiEditar(ActionEvent actionEvent) {
        try {
            usuario = new Usuario();
            usuario.setUsuario_iden(id);
            usuario.setNome(txtNome.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setSenha(txtSenha.getText());
            usuario.setCpf(txtCpf.getText());
            if(comboCupom.getValue() == null){
                erroGeral("PREENCHA O CAMPO CUPOM", "Escolha algum cupom para esse\nUsuario!");
            }else{
                cupom = (Cupom) cupomBll.getByNome(comboCupom.getValue().toString());
                usuario.setCupom(cupom);
                if (!txtSenha.getText().equals(txtSenhaConfirmar.getText())) {
                    erroGeral("ERRO AO ALTERAR", "Senhas \nnao conferem");
                } else {
                    usuarioBll.Update(usuario);
                    dialogoInfo.setTitle("INFORMAÇÃO");
                    dialogoInfo.setHeaderText("USUARIO ALTERADO");
                    dialogoInfo.setContentText("Usuario " + usuario.getNome() + " foi alterado!");
                    dialogoInfo.showAndWait();


                    atualizarGrid();
                    limparCampos();
                }


            }


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EDITAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiVoltar(ActionEvent actionEvent) {
        try {
            Mainapp.mudarTela("teladeselecao");

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO VOLTAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiPesquisarNome(KeyEvent keyEvent) {
        try {
            atualizarGridPesquisa();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO PESQUISAR ESSE NOME");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiSetarDadosNoTxt(MouseEvent mouseEvent) {
        try {
            usuario = (Usuario) usuarioBll.getById(id);
            id = tableUsuario.getSelectionModel().getSelectedItem().getUsuario_iden();
            txtNome.setText(tableUsuario.getSelectionModel().getSelectedItem().getNome());
            txtEmail.setText(tableUsuario.getSelectionModel().getSelectedItem().getEmail());
            txtCpf.setText(tableUsuario.getSelectionModel().getSelectedItem().getCpf());
            txtSenha.setText("***********");
            txtSenhaConfirmar.setText("***********");
            comboCupom.setValue(usuario.getCupom().getNome());

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO SETAR DADOS NO TXT");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    private void atualizarGrid() {

        try {
            List<Usuario> usuarioList = usuarioBll.getAll();

            ObservableList<Usuario> usuarioObservableList;

            columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getNome()));
            columCpf.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCpf()));
            columEmail.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getEmail()));
            columCupom.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCupom().getNome()));
            columId.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getUsuario_iden() + ""));


            usuarioObservableList = FXCollections.observableArrayList(usuarioList);
            tableUsuario.setItems(usuarioObservableList);


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO ATUALIZAR GRID");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }

    }

    private void atualizarGridPesquisa() {

        if (!txtPesquisa.getText().isEmpty()) {
            try {
                List<Usuario> usuarioList = usuarioBll.getByNomePesquisa(txtPesquisa.getText());

                ObservableList<Usuario> usuarioObservableList;

                columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getNome()));
                columCpf.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCpf()));
                columEmail.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getEmail()));
                columCupom.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCupom().getNome()));
                columId.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getUsuario_iden() + ""));


                usuarioObservableList = FXCollections.observableArrayList(usuarioList);
                tableUsuario.setItems(usuarioObservableList);


            } catch (Exception e) {
                dialogoErro.setTitle("ERRO");
                dialogoErro.setHeaderText("ERRO AO ATUALIZAR GRID");
                dialogoErro.setContentText(e.getMessage());
                dialogoErro.showAndWait();

            }
        }


    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtSenhaConfirmar.setText("");
        comboCupom.setValue("");
    }

    private void popularCombox() throws Exception {
        List<Cupom> cupomList = cupomBll.getAll();
        comboCupom.getItems().clear();
        for (Cupom cup : cupomList) {
            comboCupom.getItems().add(cup);
        }
    }

    public void vaiAtualizarCombo(MouseEvent mouseEvent) throws Exception {
        comboCupom.getItems().clear();

        popularCombox();
    }

    public void erroGeral(String headTxt, String msg) {
        dialogoErro.setTitle("ERRO");
        dialogoErro.setHeaderText(headTxt);
        dialogoErro.setContentText(msg);
        dialogoErro.showAndWait();
    }


}
