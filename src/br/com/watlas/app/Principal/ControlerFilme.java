package br.com.watlas.app.Principal;

import br.com.watlas.bll.CategoriaBll;
import br.com.watlas.bll.FilmeBll;
import br.com.watlas.bll.MantemFilmeBll;
import br.com.watlas.modal.Administrador;
import br.com.watlas.modal.Categoria;
import br.com.watlas.modal.Filme;

import br.com.watlas.modal.MantemFilme;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerFilme implements Initializable {

    //OBJETOS UTEIS

    JFileChooser chooser = new JFileChooser("C:\\Users\\watla\\Videos\\Valorant");
    File f, destino;
    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    private int id;
    //CAMPOS DA TELA
    public TextField txtPesquisa;
    public TextField txtFilTitulo;
    public TextField txtFilAno;
    public TextField txtfilSintopse;
    public TextField txtFilCaminho;
    public TextField txtFilCapa;
    public ComboBox comboCategoria;
    public ImageView panelImagem;
    public TableView<Filme> tableFilmes;
    public TableColumn<Filme, String> tableFilme_titulo;
    public TableColumn<Filme, String> tableFilme_Id;
    public TableColumn<Filme, String> tableFilme_Categoria;
    //OBJETOS CLASSE CONCRETA
    private Categoria categoria;
    private Filme filme;
    private MantemFilme mantemFilme;
    private List<Filme> listaFilmes;
    private ObservableList<Filme> listaFilmesObersable;
    public static Administrador administrador;
    //OBJETO BLL
    private FilmeBll filmeBll;
    private CategoriaBll categoriaBll;
    private MantemFilmeBll mantemFilmeBll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            categoriaBll = new CategoriaBll();
            filmeBll = new FilmeBll();
            filme = new Filme();
            categoria = new Categoria();
            atualizarGridFilmes();
            popularCombox();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }

    }

    public void setAdministrador(Administrador administrador){
        this.administrador = administrador;
    }


    public void bntactionAdicionar(ActionEvent actionEvent) {
        try {

            filme.setFilme_titulo(txtFilTitulo.getText());
            filme.setFilme_sintopse(txtfilSintopse.getText());
            filme.setFilme_ano(Integer.parseInt(txtFilAno.getText()));
            filme.setFilme_caminho(txtFilCaminho.getText());
            filme.setFilme_capa(txtFilCapa.getText());
            //COMPOSIÇÃO
            categoria = (Categoria) categoriaBll.getByNome(String.valueOf(comboCategoria.valueProperty().get()));
            filme.setFilme_cat_iden(categoria);
            filmeBll.Add(filme);

            //PEGANDO O FILME LOGO DEPOIS DE ADICIONADO PARA PEGAR O ID QUE FOI GERADO
            filme = (Filme) filmeBll.getByNome(txtFilTitulo.getText());

            //SETANDO TABLE MANTEM_FILME
            mantemFilme.setMantemFilme_adm_iden(administrador);
            mantemFilme.setMantemFilme_fil_iden(filme);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("FILME ADICIONADO");
            dialogoInfo.setContentText("Filme "+filme.getFilme_titulo() + " foi adicionado!");
            dialogoInfo.showAndWait();

            atualizarGridFilmes();
            limparCampos();


        } catch (Exception e) {

            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL ADICIONAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void bntactionExcluir(ActionEvent actionEvent) {
        try {
            filmeBll.Delete(id);
            filme = (Filme) filmeBll.getById(id);

            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("FILME ADICIONADO");
            dialogoInfo.setContentText("Filme "+filme.getFilme_titulo() + " foi apagado!");
            dialogoInfo.showAndWait();
            limparCampos();
            atualizarGridFilmes();

        }catch (Exception e){
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }


    }

    public void bntactionEditar(ActionEvent actionEvent)
    {
        try {
            filme.setFilme_iden(id);
            filme.setFilme_titulo(txtFilTitulo.getText());
            filme.setFilme_sintopse(txtfilSintopse.getText());
            filme.setFilme_ano(Integer.parseInt(txtFilAno.getText()));
            filme.setFilme_caminho(txtFilCaminho.getText());
            filme.setFilme_capa(txtFilCapa.getText());
            //COMPOSIÇÃO
            categoria = (Categoria) categoriaBll.getByNome(String.valueOf(comboCategoria.valueProperty().get()));
            filme.setFilme_cat_iden(categoria);
            filmeBll.Update(filme);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("FILME EDITADO");
            dialogoInfo.setContentText("Filme '"+filme.getFilme_titulo() + "' foi editado!");
            dialogoInfo.showAndWait();

            atualizarGridFilmes();
            limparCampos();


        } catch (Exception e) {

            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL ADICIONAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void bntactionVoltar(ActionEvent actionEvent) throws Exception {
        Mainapp.mudarTela("teladeselecao");
    }


    public void bntAbrirChooserCaminho(ActionEvent actionEvent) {
        try {
            String titulo = "Selecione o filme";
            FileFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
            chooser.addChoosableFileFilter(filter);
            chooser.setDialogTitle(titulo);
            chooser.showOpenDialog(null);
            f = chooser.getSelectedFile();
            String caminho = f.getAbsolutePath();
            txtFilCaminho.setText(caminho);
            txtFilCaminho.setEditable(false);

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }


    public void bntAbrirChooserCapa(ActionEvent actionEvent) {
        try {
            String titulo = "Selecione a capa do filme";
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");

            chooser.setDialogTitle(titulo);
            chooser.setFileFilter(filtro);
            chooser.showOpenDialog(null);
            f = chooser.getSelectedFile();
            String foto = f.getAbsolutePath();
            txtFilCapa.setText(foto);
            txtFilCapa.setEditable(false);
            panelImagem.setImage(new Image("file:///" + f.getAbsolutePath()));
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void txtPesquisarNomeFilme(KeyEvent keyEvent) {
        try {
            atualizarGridPesquisa();
        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void PegarDaTableFilme(MouseEvent mouseEvent) throws Exception {
        try {
            id = tableFilmes.getSelectionModel().getSelectedItem().getFilme_iden();
            filme = (Filme) filmeBll.getById(id);
            txtFilCapa.setText(filme.getFilme_capa());
            txtFilCaminho.setText(filme.getFilme_caminho());
            txtfilSintopse.setText(filme.getFilme_sintopse());
            txtFilAno.setText(filme.getFilme_ano() + "");
            txtFilTitulo.setText(filme.getFilme_titulo());
            comboCategoria.setValue(filme.getFilme_cat_iden().getCategoria_nome());
            panelImagem.setImage(new Image("file:///" + filme.getFilme_capa()));

            //getValue devolve o selecionado


        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }

    }

    public void popularCombox() throws Exception {
        List<Categoria> categoriaList = categoriaBll.getAll();
        comboCategoria.getItems().removeAll();
        for (Categoria cat : categoriaList) {
            comboCategoria.getItems().add(cat.getCategoria_nome());
        }
    }


    public void atualizarGridFilmes() throws Exception {
        filmeBll = new FilmeBll();
        listaFilmes = filmeBll.getAll();
        tableFilme_titulo.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_titulo()));
        tableFilme_Id.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getFilme_iden() + ""));
        tableFilme_Categoria.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_cat_iden().getCategoria_nome()));
        listaFilmesObersable = FXCollections.observableArrayList(listaFilmes);
        tableFilmes.setItems(listaFilmesObersable);
    }
    public void atualizarGridPesquisa() throws Exception {
        filmeBll = new FilmeBll();
        listaFilmes = filmeBll.getAllPesquisa(txtPesquisa.getText());
        tableFilme_titulo.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_titulo()));
        tableFilme_Id.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getFilme_iden() + ""));
        tableFilme_Categoria.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_cat_iden().getCategoria_nome()));
        listaFilmesObersable = FXCollections.observableArrayList(listaFilmes);
        tableFilmes.setItems(listaFilmesObersable);
    }


    public void vaiAbrircadastroCat(ActionEvent actionEvent) throws Exception {
        Mainapp.mudarTela("gerenciarcategoria");
    }
    private void limparCampos(){
        txtFilTitulo.setText("");
        txtFilCaminho.setText("");
        txtFilAno.setText("");
        txtfilSintopse.setText("");
        txtFilCapa.setText("");

    }
}
