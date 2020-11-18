package br.com.watlas.app.Principal;

import br.com.watlas.bll.CategoriaBll;
import br.com.watlas.bll.FilmeBll;
import br.com.watlas.bll.MantemFilmeBll;
import br.com.watlas.modal.*;

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
    public Button btnCadCategoria;

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
            mantemFilme = new MantemFilme();
            mantemFilmeBll = new MantemFilmeBll();
            atualizarGridFilmes();
            popularCombox();

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();
        }

    }




    public void bntactionAdicionar(ActionEvent actionEvent) {
        filme = new Filme();
        categoria = new Categoria();
        try {

            filme.setFilme_titulo(txtFilTitulo.getText());
            filme.setFilme_sintopse(txtfilSintopse.getText());
            if(txtFilAno.getText().isEmpty()){
                throw new Exception("INFORME O ANO DO FILME");
            }
            filme.setFilme_ano(Integer.parseInt(txtFilAno.getText()));
            filme.setFilme_caminho(txtFilCaminho.getText());
            filme.setFilme_capa(txtFilCapa.getText());
            //COMPOSIÇÃO
            if(comboCategoria.getValue() == null){
                erroGeral("PREENCHA O CAMPO CATEGORIA", "TA FALTANDO PREENCHER O CAMPO DE FILME\n MEU CONSAGRADO!");
            }else{
                categoria = (Categoria) categoriaBll.getByNome(comboCategoria.getValue().toString());
                filme.setFilme_cat_iden(categoria);
                filmeBll.add(filme);

                //PEGANDO O FILME LOGO DEPOIS DE ADICIONADO PARA PEGAR O ID QUE FOI GERADO
                filme = (Filme) filmeBll.getByNome(txtFilTitulo.getText());

                //SETANDO TABLE MANTEM_FILME
                mantemFilme.setMantemFilme_adm_iden(ControlerLoginAdministrador.administrador);
                mantemFilme.setMantemFilme_fil_iden(filme);
                mantemFilmeBll.add(mantemFilme);

                dialogoInfo.setTitle("INFORMAÇÃO");
                dialogoInfo.setHeaderText("FILME ADICIONADO");
                dialogoInfo.setContentText("Filme "+filme.getFilme_titulo() + " foi adicionado!");
                dialogoInfo.showAndWait();

                atualizarGridFilmes();
                limparCampos();


            }

        } catch (Exception e) {

            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL ADICIONAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }
    }

    public void bntactionExcluir(ActionEvent actionEvent) {

        try {

            filmeBll.delete(id);


            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("FILME ADICIONADO");
            dialogoInfo.setContentText("Filme foi apagado!");
            dialogoInfo.showAndWait();
            limparCampos();
            atualizarGridFilmes();

        }catch (Exception e){
            System.out.println(e.getMessage());
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("NAO FOI POSIVEL EXCLUIR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }


    }

    public void bntactionEditar(ActionEvent actionEvent)
    {
        filme = new Filme();
        try {
            filme.setFilme_iden(id);
            filme.setFilme_titulo(txtFilTitulo.getText());
            filme.setFilme_sintopse(txtfilSintopse.getText());
            filme.setFilme_ano(Integer.parseInt(txtFilAno.getText()));
            filme.setFilme_caminho(txtFilCaminho.getText());
            filme.setFilme_capa(txtFilCapa.getText());
            //COMPOSIÇÃO

            if(comboCategoria.getValue() == null){
                erroGeral("PREENCHA O CAMPO CATEGORIA", "TA FALTANDO PREENCHER O CAMPO DE FILME\n MEU CONSAGRADO!");
            }else{
                categoria = (Categoria) categoriaBll.getByNome(comboCategoria.getValue().toString());
                filme.setFilme_cat_iden(categoria);
                filmeBll.Update(filme);

                dialogoInfo.setTitle("INFORMAÇÃO");
                dialogoInfo.setHeaderText("FILME EDITADO");
                dialogoInfo.setContentText("Filme "+filme.getFilme_titulo() + " foi EDITADO!");
                dialogoInfo.showAndWait();

                atualizarGridFilmes();
                limparCampos();


            }

        } catch (Exception e) {
            erroGeral("ERRO AO EDITAR", e.getMessage());

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

        } catch (Exception e) {
            dialogoErro.setTitle("ERRO");
            dialogoErro.setHeaderText("INICIALIZAR");
            dialogoErro.setContentText(e.getMessage());
            dialogoErro.showAndWait();

        }

    }

    public void popularCombox() throws Exception {
        List<Categoria> categoriaList = categoriaBll.getAll();
        comboCategoria.getItems().clear();
        for (Categoria cat : categoriaList) {
            comboCategoria.getItems().add(cat);
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


    public void vaiAtualizarCombo(MouseEvent mouseEvent)throws Exception {
        popularCombox();

    }
    private void erroGeral(String titulo, String corpoErro){
        dialogoErro.setTitle("ERRO");
        dialogoErro.setHeaderText(titulo);
        dialogoErro.setContentText(corpoErro);
        dialogoErro.showAndWait();
    }


}
