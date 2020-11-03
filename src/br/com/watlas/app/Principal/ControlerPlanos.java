package br.com.watlas.app.Principal;

import br.com.watlas.bll.FilmeBll;
import br.com.watlas.bll.PlanoBll;
import br.com.watlas.modal.Filme;
import br.com.watlas.modal.Plano;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerPlanos implements Initializable {
    public TextField txtNome;
    public TextField txtPreco;
    public TextField txtAcessoSimutaneo;
    public TableView<Plano> tablePlanos;
    public TableColumn<Plano, String> columId;
    public TableColumn<Plano, String> columNome;
    public TableColumn<Plano, String> columPreco;
    public TableColumn<Plano, String> columAcessoSimutaneo;
    public TextField txtPesquisa;

    //UTEIS
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    private ObservableList<Plano> listaFilmesObersable;
    private int id;
    //BLL
    PlanoBll planoBll;
    //Concreta
    Plano plano;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            planoBll = new PlanoBll();
            plano = new Plano();
            atualizarGrid();
        }catch (Exception e){

        }
    }

    public void vaiIncluir(ActionEvent actionEvent) {
        try {
            plano.setPlano_nome(txtNome.getText());
            plano.setPlano_preco(Double.parseDouble(txtPreco.getText()));
            plano.setPlano_acessSimutaneo(Integer.parseInt(txtAcessoSimutaneo.getText()));

            planoBll.Add(plano);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("Plano adicionado");
            dialogoInfo.setContentText("Filme '"+plano.getPlano_nome() + "' foi editado!");
            dialogoInfo.showAndWait();

            atualizarGrid();
            limparCampos();
        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSSIVEL ADICIONAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }


    public void vaiExcluir(ActionEvent actionEvent) {
        try {
            plano = (Plano) planoBll.getById(id);
            planoBll.Delete(id);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("Plano apagado");
            dialogoInfo.setContentText("Filme '"+plano.getPlano_nome() + "' foi apagado!");
            dialogoInfo.showAndWait();


            atualizarGrid();
            limparCampos();

        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSSIVEL EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiEditar(ActionEvent actionEvent) {
        try {
            plano.setPlano_iden(id);
            plano.setPlano_nome(txtNome.getText());
            plano.setPlano_preco(Double.parseDouble(txtPreco.getText()));
            plano.setPlano_acessSimutaneo(Integer.parseInt(txtAcessoSimutaneo.getText()));

            planoBll.Add(plano);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("Plano adicionado");
            dialogoInfo.setContentText("Filme '"+plano.getPlano_nome() + "' foi editado!");
            dialogoInfo.showAndWait();

            atualizarGrid();
            limparCampos();

        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSSIVEL EDITAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void vaiVoltar(ActionEvent actionEvent) {
        try {
            Mainapp.mudarTela("teladeselecao");
        }catch (Exception e){

        }
    }

    public void vaiPesquisarNome(KeyEvent keyEvent) {
        try {

            atualizarGridPesquisa();
        }catch (Exception e){

        }
    }

    public void vaiSetarDadosTableOnTxt(MouseEvent mouseEvent) {
        try {
            id = tablePlanos.getSelectionModel().getSelectedItem().getPlano_iden();
            txtNome.setText(tablePlanos.getSelectionModel().getSelectedItem().getPlano_nome());
            txtPreco.setText(tablePlanos.getSelectionModel().getSelectedItem().getPlano_preco()+"");
            txtAcessoSimutaneo.setText(tablePlanos.getSelectionModel().getSelectedItem().getPlano_acessSimutaneo()+"");

        }catch (Exception e){

        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        txtAcessoSimutaneo.setText("");
    }

    private void atualizarGrid() throws Exception {
        List<Plano> listplano =  planoBll.getAll();

        columId.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_iden()+""));
        columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getPlano_nome()));
        columAcessoSimutaneo.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_acessSimutaneo()+""));
        columPreco.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_preco()+""));
        listaFilmesObersable = FXCollections.observableArrayList(listplano);
        tablePlanos.setItems(listaFilmesObersable);

    }
    private void atualizarGridPesquisa() throws Exception {
        List<Plano> listplano =  planoBll.getAllPesquisa(txtPesquisa.getText());

        columId.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_iden()+""));
        columNome.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getPlano_nome()));
        columAcessoSimutaneo.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_acessSimutaneo()+""));
        columPreco.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getPlano_preco()+""));
        listaFilmesObersable = FXCollections.observableArrayList(listplano);
        tablePlanos.setItems(listaFilmesObersable);
    }

}
