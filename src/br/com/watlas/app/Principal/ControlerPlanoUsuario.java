package br.com.watlas.app.Principal;

import br.com.watlas.bll.ContrataBll;
import br.com.watlas.bll.PlanoBll;
import br.com.watlas.modal.Contrata;

import br.com.watlas.modal.Plano;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerPlanoUsuario implements Initializable {
    public DatePicker txtDateInicio;
    public DatePicker txtDateFim;
    public ComboBox comboxPlanos;
    public TextArea txtareaPlanodados;
    public TableView<Plano> tablePlanos;
    public TableColumn<Plano, String> columNome;
    public TableColumn<Plano, String> columAcessSimu;
    public TableColumn<Plano, String> columPreco;
    public TableColumn<Plano, String> columId;

    //table control
    public TableView<Contrata> tablePlanoControl;
    public TableColumn<Contrata, String> columControlId;
    public TableColumn<Contrata, String> columControlNome;
    public TableColumn<Contrata, String> columControlAcess;
    public TableColumn<Contrata, String> columControlnicio;
    public TableColumn<Contrata, String> columControlFIm;
    public TableColumn<Contrata, String> columControlStatus;
    PlanoBll planoBll;
    Contrata contrata;
    ContrataBll contrataBll;
    Plano plano;
    int id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            plano = new Plano();
            contrata = new Contrata();
            contrataBll = new ContrataBll();
            planoBll = new PlanoBll();
            atualizarGrid();
            atualizarGridControl();
        } catch (Exception e) {

        }
    }

    public void vaiContratarPlano(ActionEvent actionEvent) {

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = txtDateInicio.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        ZoneId defaultZoneId1 = ZoneId.systemDefault();
        LocalDate localDate1 = txtDateFim.getValue();
        Date date1 = Date.from(localDate1.atStartOfDay(defaultZoneId1).toInstant());
        try {
            contrata.setContrato_dataInicio(new java.sql.Date(date.getTime()));
            contrata.setContrato_dataFim(new java.sql.Date(date1.getTime()));
            contrata.setCon_usuario_iden(ControlerLoginUsuario.usuario);

            Plano plano = (Plano) planoBll.getById(id);
            contrata.setCon_plano_iden(plano);
            contrata.setContrato_status(true);
            contrataBll.Add(contrata);
            atualizarGrid();
            atualizarGridControl();
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CONTRATO FEITO");
            dialogoInfo.setContentText("pode ir la assistir filme agora meu mano");
            dialogoInfo.showAndWait();
            Mainapp.mudarTela("menuUsuario");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }


    public void vaiVoltar(ActionEvent actionEvent) throws Exception {
        Mainapp.mudarTela("menuUsuario");
    }


    public void vaiPegarDadosTable(MouseEvent mouseEvent) {
        id = tablePlanos.getSelectionModel().getSelectedItem().getPlano_iden();
    }
    private void atualizarGrid() throws Exception {
        List<Plano> listplano =  planoBll.getAll();
        ObservableList<Plano> planoObservableList;

        columAcessSimu.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_iden()+""));
        columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getPlano_nome()));
        columAcessSimu.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_acessSimutaneo()+""));
        columPreco.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_preco()+""));
        planoObservableList = FXCollections.observableArrayList(listplano);
        tablePlanos.setItems(planoObservableList);

    }

    private void atualizarGridControl() throws Exception{
        List<Contrata> contrataList = contrataBll.getAllusu(ControlerLoginUsuario.usuario.getUsuario_iden());
        ObservableList<Contrata> contrataObservableList;

        columControlId.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCon_usuario_iden().getNome()));
        columControlNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCon_plano_iden().getPlano_nome()+""));
        columControlAcess.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCon_plano_iden().getPlano_acessSimutaneo()+""));
        columControlnicio.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getContrato_dataInicio()+""));
        columControlFIm.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getContrato_dataFim()+""));
        columControlStatus.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getContrato_status()+""));
        contrataObservableList = FXCollections.observableList(contrataList);
        tablePlanoControl.setItems(contrataObservableList);
    }

    public void vaiPegarDadosGrid(MouseEvent mouseEvent) {
    }

    public void vaiControlExcluir(ActionEvent actionEvent) {
    }
}
