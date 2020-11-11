package br.com.watlas.app.Principal;

import br.com.watlas.bll.CupomBll;
import br.com.watlas.bll.MantemCupomBll;
import br.com.watlas.bll.UsuarioBll;
import br.com.watlas.dal.AdministradorDal;
import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Cupom;
import br.com.watlas.modal.MantenCupom;
import br.com.watlas.modal.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerCupom implements Initializable {


    public TextField txtNomeCup;
    public TextField txtDescontoCup;
    public TableView<Cupom> tableCup;
    public TableColumn<Cupom, String> columID;
    public TableColumn<Cupom, String> columDataCup;
    public TableColumn<Cupom, String> columDescontoCup;
    public TableColumn<Cupom, String> columNomeCupom;
    //TABLE DE DADOS
    public TableView<MantenCupom> tableDadosCupxUsu;
    public TableColumn<MantenCupom, String> columDadosID;
    public TableColumn<MantenCupom, String> columDadosCupom;
    public TableColumn<MantenCupom, String> columDadosDataCriacao;
    public TableColumn<MantenCupom, String> columDadosCriador;

    //CONCRETAS
    private Cupom cupom;
    private MantenCupom mantenCupom;
    //BLL
    UsuarioBll usuarioBll;
    private MantemCupomBll mantemCupomBll;
    private CupomBll cupomBll;
    //UTEIS
    private ObservableList<Cupom> cupomObservableList;
    private List<Cupom> cupomList;
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    private int id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            usuarioBll = new UsuarioBll();
            cupom = new Cupom();
            cupomBll = new CupomBll();
            mantenCupom = new MantenCupom();
            mantemCupomBll = new MantemCupomBll();
            atualizarGridDados();
            atualizarGridCup();
        } catch (Exception e) {

        }
    }

    public void vaiIncluirCup(ActionEvent actionEvent) {
        try {

            cupom.setNome(txtNomeCup.getText().toLowerCase());
            cupom.setCupom_porcentagem(Double.parseDouble(txtDescontoCup.getText()));
            cupom.setCupom_DataGeracao(new java.sql.Date(new java.util.Date().getTime()));
            cupomBll.Add(cupom);

            cupom = (Cupom) cupomBll.getByNome(txtNomeCup.getText());

            //SETANDO NA TABLE DE MANTEM_CUPOM
            mantenCupom.setMantemcup_cup_iden(cupom);
            mantenCupom.setMantemcup_adm_iden(ControlerLogin.administrador);
            mantemCupomBll.Add(mantenCupom);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CUPOM FOI ADICIONADO");
            dialogoInfo.setContentText("categoria '" + cupom.getNome() + "' foi adicionado!");
            dialogoInfo.showAndWait();
            atualizarGridCup();
            limparCampos();


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO INCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
            System.out.println(e.getMessage());

        }
    }


    public void vaiExcluirCup(ActionEvent actionEvent) {
        try {
            cupom = (Cupom) cupomBll.getById(id);
            cupomBll.Delete(id);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CUPOM APAGADO");
            dialogoInfo.setContentText("categoria '" + cupom.getNome() + "' foi APAGADO!");
            dialogoInfo.showAndWait();
            atualizarGridCup();
            limparCampos();

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }


    public void vaiEditarCup(ActionEvent actionEvent) {
        try {
            cupom.setCupom_iden(id);
            cupom.setNome(txtNomeCup.getText());
            cupom.setCupom_porcentagem(Double.parseDouble(txtDescontoCup.getText()));
            cupom.setCupom_DataGeracao(new java.sql.Date(new java.util.Date().getTime()));
            cupomBll.Update(cupom);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CUPOM EDITADO");
            dialogoInfo.setContentText("categoria '" + cupom.getNome() + "' foi editado");
            dialogoInfo.showAndWait();
            atualizarGridCup();
            limparCampos();

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EDITAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiVotarCup(ActionEvent actionEvent) throws Exception {
        Mainapp.mudarTela("teladeselecao");
    }

    private void atualizarGridCup() throws Exception {
        cupomList = cupomBll.getAll();
        columID.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getCupom_iden() + ""));
        columNomeCupom.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getNome() + ""));
        columDataCup.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCupom_DataGeracao() + ""));
        columDescontoCup.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCupom_porcentagem() + ""));
        cupomObservableList = FXCollections.observableArrayList(cupomList);
        tableCup.setItems(cupomObservableList);
        atualizarGridDados();


    }

    private void atualizarGridDados() throws Exception {
        //LIST
        List<MantenCupom> mantenCupomList = mantemCupomBll.getAll();
        ObservableList<MantenCupom> mantenCupomObservableList;

        columDadosID.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getMantemCupom_iden()+""));
        columDadosCriador.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getMantemcup_adm_iden().getAdm_nome()));
        columDadosCupom.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getMantemcup_cup_iden().getNome()));
        columDadosDataCriacao.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getMantemcup_cup_iden().getCupom_DataGeracao()+""));
        mantenCupomObservableList = FXCollections.observableList(mantenCupomList);
        tableDadosCupxUsu.setItems(mantenCupomObservableList);
    }


    public void VaiSetarDadosNoTxt(MouseEvent mouseEvent) {
        try {
            id = tableCup.getSelectionModel().getSelectedItem().getCupom_iden();
            txtNomeCup.setText(tableCup.getSelectionModel().getSelectedItem().getNome());
            txtDescontoCup.setText(tableCup.getSelectionModel().getSelectedItem().getCupom_porcentagem() + "");

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void limparCampos() {
        txtNomeCup.setText("");
        txtDescontoCup.setText("");
    }

    public void VaiSentarDadosTxtField(MouseEvent mouseEvent) {
    }
}

