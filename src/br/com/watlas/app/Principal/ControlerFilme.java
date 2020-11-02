package br.com.watlas.app.Principal;

import br.com.watlas.bll.FilmeBll;
import br.com.watlas.modal.Filme;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerFilme implements Initializable {
    //OBJETOS UTEIS
    JFileChooser chooser = new JFileChooser();
    File f, destino;
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    //CAMPOS DA TELA
    public TextField txtFilTitulo;
    public TextField txtFilAno;
    public TextField txtfilSintopse;
    public TextField txtFilCaminho;
    public TextField txtFilCapa;
    public ComboBox comboCategoria;
    public ImageView panelImagem;
    public TableView<Filme> tableFilmes;
    public TableColumn<Filme, String> tableFilme_titulo;
    public TableColumn<Filme, String>  tableFilme_Id;
    public TableColumn<Filme, String>  tableFilme_Categoria;
    //OBJETOS CLASSE CONCRETA
    private List<Filme> listaFilmes;
    private ObservableList<Filme> listaFilmesObersable;
    //OBJETO BLL
    private FilmeBll filmeBll = null;

    public void atualizarGrid()throws Exception{
        filmeBll = new FilmeBll();
        listaFilmes = filmeBll.getAll();
        tableFilme_titulo.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_titulo()));
        tableFilme_Id.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getFilme_iden()+""));
        tableFilme_Categoria.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_cat_iden().getCategoria_nome()));
        listaFilmesObersable = FXCollections.observableArrayList(listaFilmes);
        tableFilmes.setItems(listaFilmesObersable);
    }


    public void bntactionAdicionar(ActionEvent actionEvent) {
        try {


        }catch (Exception e){

            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL ADICIONAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void bntactionExcluir(ActionEvent actionEvent) {
    }

    public void bntactionEditar(ActionEvent actionEvent) {
    }

    public void bntactionVoltar(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            atualizarGrid();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }

    }

    public void bntAbrirChooserCaminho(ActionEvent actionEvent) {
        String titulo = "Selecione um FILME";
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("mp4");
        chooser.setDialogTitle(titulo);
        chooser.setFileFilter(filtro);
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        String foto = f.getAbsolutePath();
        txtFilCaminho.setText(foto);
        txtFilCaminho.setEditable(false);


    }

    public void bntAbrirChooserCapa(ActionEvent actionEvent) {
        try {
            String titulo = "Selecione um FILME";
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");

            chooser.setDialogTitle(titulo);
            chooser.setFileFilter(filtro);
            chooser.showOpenDialog(null);
            f = chooser.getSelectedFile();
            String foto = f.getAbsolutePath();
            txtFilCapa.setText(foto);
            txtFilCapa.setEditable(false);
            panelImagem.setImage(new Image("file:///"+f.getAbsolutePath()));
        }catch (Exception e){

        }
    }
}
