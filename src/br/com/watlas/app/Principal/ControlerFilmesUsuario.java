package br.com.watlas.app.Principal;

import br.com.watlas.bll.FilmeBll;
import br.com.watlas.bll.VizualizaBll;
import br.com.watlas.modal.Filme;
import br.com.watlas.modal.Visualiza;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControlerFilmesUsuario implements Initializable {
    public TableView<Filme> tableFilmes;
    public TableColumn<Filme, String> columNome;
    public TableColumn<Filme, String> columAno;
    public TableColumn<Filme, String> ColumCategoria;
    public TableColumn<Filme, String> columID;
    public Button btnAssistir;
    public Button bntVoltar;
    public ImageView imageCapaFilme;
    public MediaView mediaExibirFilme;
    public TextArea txtSintopse;
    public Button btnPausar;

    //Concretas
    Filme filme;
    Visualiza visualiza;
    //Bll
    VizualizaBll vizualizaBll;
    FilmeBll filmeBll;
    //lists
    ObservableList<Filme> listaFilmesObersable;
    List<Filme> listaFilmes;
    //uteis
    Media media;
    MediaPlayer mediaPlayer;
    int id;
    int help = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            filme = new Filme();
            filmeBll = new FilmeBll();
            visualiza = new Visualiza();
            vizualizaBll = new VizualizaBll();
            atualizarGrid();
        } catch (Exception e) {

        }
    }

    public void vaisetaDados(MouseEvent mouseEvent) throws Exception {
        id = tableFilmes.getSelectionModel().getSelectedItem().getFilme_iden();
        filme = (Filme) filmeBll.getById(id);
        imageCapaFilme.setImage(new Image("file:///" + filme.getFilme_capa()));
        txtSintopse.setText(filme.getFilme_sintopse());

        String path;
        File file = new File(filme.getFilme_caminho());
        path = file.toURI().toString();

        media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaExibirFilme.setMediaPlayer(mediaPlayer);
    }

    public void vaiAssistir(ActionEvent actionEvent) throws Exception {
        filme = (Filme) filmeBll.getById(id);
        String path;
        File file = new File(filme.getFilme_caminho());
        path = file.toURI().toString();
        imageCapaFilme.setImage(new Image("file:///" + filme.getFilme_capa()));
        txtSintopse.setText(filme.getFilme_sintopse());


        media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaExibirFilme.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();


    }

    public void vaivoltar(ActionEvent actionEvent) {
    }

    private void atualizarGrid() throws Exception {

        listaFilmes = filmeBll.getAll();
        columAno.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_ano() + ""));
        columNome.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_titulo()));
        columID.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getFilme_iden() + ""));
        ColumCategoria.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getFilme_cat_iden().getCategoria_nome()));
        listaFilmesObersable = FXCollections.observableArrayList(listaFilmes);
        tableFilmes.setItems(listaFilmesObersable);

    }


    public void vaiPausar(ActionEvent actionEvent) {
        if (help == 1) {
            mediaPlayer.play();
            btnPausar.setText("VOLTAR");
            help--;
        } else if (help == 0) {
            mediaPlayer.pause();
            btnPausar.setText("PAUSAR");
            help++;
        }
    }

    public void vaiParar(ActionEvent actionEvent) {

        mediaPlayer.stop();

    }
}
