package br.com.watlas.app.Principal;

import br.com.watlas.bll.CategoriaBll;
import br.com.watlas.modal.Categoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControleCategoria implements Initializable {

    public TextField txtNomeCat;
    public TextField txtPesquisaCat;
    public Button bntIncluirCat;
    public Button bntExcluirCat;
    public Button btnAlterarCat;
    public Button btnVoltarCat;
    public TableView<Categoria> tableCategorias;
    public TableColumn<Categoria, String> columIdCat;
    public TableColumn<Categoria, String> columNomeCat;
    //UTEIS

    private List<Categoria> categoriaList;
    private ObservableList<Categoria> listaCategoriaObersable;
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    private int id;
    //BLL
    private CategoriaBll categoriaBll;
    //CONCRETAS
    private Categoria categoria;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            categoriaBll = new CategoriaBll();
            categoria = new Categoria();
            atualizarGrid();
        } catch (Exception e) {

        }
    }

    public void vaiIncluirCat(ActionEvent actionEvent) {
        try {
            categoria.setCategoria_nome(txtNomeCat.getText());
            categoriaBll.Add(categoria);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CATEGORIA FOI ADICIONADA");
            dialogoInfo.setContentText("categoria '"+categoria.getCategoria_nome() + "' foi adicionada!");
            dialogoInfo.showAndWait();
            atualizarGrid();
            txtPesquisaCat.setText("");
        }catch (Exception e){
            System.out.println(e.getMessage());
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO INCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }

    }

    public void vaiExcluirCat(ActionEvent actionEvent) {
        try {
            categoria = (Categoria) categoriaBll.getById(id);
            categoriaBll.Delete(id);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CATEGORIA EXCLUIDA");
            dialogoInfo.setContentText("categoria '"+categoria.getCategoria_nome() + "' foi excluída!");
            dialogoInfo.showAndWait();
            atualizarGrid();
        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO EXLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
            System.out.println(e.getMessage());
        }
    }

    public void VaiAlterarCat(ActionEvent actionEvent) {
        try {
            categoria.setCategoria_iden(id);
            categoria.setCategoria_nome(txtNomeCat.getText());
            categoriaBll.Update(categoria);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("CATEGORIA FOI EDITADA");
            dialogoInfo.setContentText("categoria '"+categoria.getCategoria_nome() + "' foi editada!");
            dialogoInfo.showAndWait();
            atualizarGrid();
        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("ERRO AO ALTERAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }
    }

    public void vaiVoltarTela(ActionEvent actionEvent) throws Exception {

        Mainapp.mudarTela("gerenciafilmes");

    }

    public void vaiPesquisa(KeyEvent keyEvent)throws Exception {
        pesquisarGrid();
    }


    public void atualizarGrid() throws Exception {

        categoriaList = categoriaBll.getAll();
        columIdCat.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getCategoria_iden() + ""));
        columNomeCat.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCategoria_nome() + ""));
        listaCategoriaObersable = FXCollections.observableArrayList(categoriaList);
        tableCategorias.setItems(listaCategoriaObersable);
        limparCampos();

    }

    public void pesquisarGrid() throws Exception{
        categoriaList = categoriaBll.getAllPesquisa(txtPesquisaCat.getText());
        columIdCat.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getCategoria_iden() + ""));
        columNomeCat.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getCategoria_nome() + ""));
        listaCategoriaObersable = FXCollections.observableArrayList(categoriaList);
        tableCategorias.setItems(listaCategoriaObersable);

    }



    public void vaiSetarDadosTable(MouseEvent mouseEvent) {
        try {
            id = tableCategorias.getSelectionModel().getSelectedItem().getCategoria_iden();
            txtNomeCat.setText(tableCategorias.getSelectionModel().getSelectedItem().getCategoria_nome());


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }
    private void limparCampos(){
        txtNomeCat.setText("");
    }
}
