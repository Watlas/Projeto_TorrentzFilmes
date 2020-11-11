package br.com.watlas.app.Principal;

import br.com.watlas.bll.ContrataBll;
import br.com.watlas.bll.FilmeBll;
import br.com.watlas.bll.PlanoBll;
import br.com.watlas.bll.VizualizaBll;
import br.com.watlas.modal.Contrata;
import br.com.watlas.modal.Filme;
import br.com.watlas.modal.Plano;
import br.com.watlas.modal.Visualiza;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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

    //CONTROL TAB2
    public TableView<Visualiza> tableFilmeControl;
    public TableColumn<Visualiza, String> columControlIden;
    public TableColumn<Visualiza, String> columControlFilme;
    public TableColumn<Visualiza, String> columControlAno;
    public TableColumn<Visualiza, String> columControlDataVisu;
    public TableColumn<Visualiza, String> columControlCompleto;
    public TableColumn<Visualiza, String> columControlUsuario;
    public ImageView imagemControlCapa;
    public HBox hbox;

    //Concretas
    Filme filme;
    Visualiza visualiza;
    //Bll
    VizualizaBll vizualizaBll;
    FilmeBll filmeBll;
    //lists
    ObservableList<Filme> listaFilmesObersable;
    List<Filme> listaFilmes;
    ObservableList<Visualiza> visualizaObservableList;
    List<Visualiza> visualizaList;
    //uteis
    Media media;
    MediaPlayer mediaPlayer;
    int id;
    int help = 0;
    private boolean atEndOfMedia = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            filme = new Filme();
            filmeBll = new FilmeBll();
            visualiza = new Visualiza();
            vizualizaBll = new VizualizaBll();
            atualizaGridControl();
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
        if(verificarPlano() == false){
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("INFORMAÇÃO");
            dialogoInfo.setHeaderText("VOCE NAO POSSUI UM PLANO");
            dialogoInfo.setContentText("para assistir uma filme, adiquira um plano.");
            dialogoInfo.showAndWait();
            return;

        }else {
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
        MediaPlayer.Status status = mediaPlayer.getStatus();


    }

    public void vaivoltar(ActionEvent actionEvent) throws Exception {
        registrarVisualizacao(false);
        Mainapp.mudarTela("menuUsuario");
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

    public void atualizaGridControl() throws Exception {
        visualizaList = vizualizaBll.getAllUsu(ControlerLoginUsuario.usuario.getUsuario_iden());

        columControlIden.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getVisu_iden() + ""));
        columControlAno.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getVisu_filme_iden().getFilme_ano() + ""));
        columControlCompleto.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getVisu_completo() + ""));
        columControlFilme.setCellValueFactory(objeto -> new SimpleStringProperty(objeto.getValue().getVisu_filme_iden().getFilme_titulo()));
        columControlUsuario.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getVisu_usuario_iden().getNome()));
        columControlDataVisu.setCellValueFactory(obj -> new SimpleStringProperty(obj.getValue().getVisu_dataVisualizacao() + ""));
        visualizaObservableList = FXCollections.observableArrayList(visualizaList);
        tableFilmeControl.setItems(visualizaObservableList);
    }


    public void vaiPausar(ActionEvent actionEvent) throws Exception {
        registrarVisualizacao(false);
        if (help == 1) {
            mediaPlayer.play();
            help--;
        } else if (help == 0) {
            mediaPlayer.pause();
            help++;
        }
    }

    public void vaiParar(ActionEvent actionEvent) throws Exception {


        registrarVisualizacao(true);


    }

    private void registrarVisualizacao(Boolean parar) throws Exception {
        visualiza = new Visualiza();
        Visualiza v = new Visualiza();
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                if (parar == true) {
                    mediaPlayer.stop();
                } else {
                    mediaPlayer.pause();

                }

                    if (mediaPlayer.getCurrentTime().toSeconds() < mediaPlayer.getTotalDuration().toSeconds()) {
                        visualiza.setVisu_completo(false);
                        visualiza.setVisu_dataVisualizacao((new java.sql.Date(new java.util.Date().getTime())));
                        visualiza.setVisu_usuario_iden(ControlerLoginUsuario.usuario);
                        visualiza.setVisu_filme_iden(filme);
                            vizualizaBll.Add(visualiza);
                        //Somente um filme false use + fil
                        //usar o where fil + usu + status
                    } else {
                        visualiza.setVisu_completo(true);
                        visualiza.setVisu_dataVisualizacao((new java.sql.Date(new java.util.Date().getTime())));
                        visualiza.setVisu_usuario_iden(ControlerLoginUsuario.usuario);
                        visualiza.setVisu_filme_iden(filme);
                        vizualizaBll.Add(visualiza);
                    }
                }
            }
        }



    public void vaiAtualizarGridControl(ActionEvent actionEvent) throws Exception {
        registrarVisualizacao(true);
        atualizaGridControl();
    }
    private Boolean verificarPlano() throws Exception {
        Contrata contrata = new Contrata();
        ContrataBll contrataBll = new ContrataBll();

        contrata = (Contrata) contrataBll.getByIdusu(ControlerLoginUsuario.usuario.getUsuario_iden());
        if(contrata.getContrato_dataInicio() == null){
           return false;
        }else{
            return true;
        }

    }


}
