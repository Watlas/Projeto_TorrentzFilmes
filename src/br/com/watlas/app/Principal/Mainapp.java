package br.com.watlas.app.Principal;

import br.com.watlas.modal.Plano;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mainapp extends Application {

    private static Stage stage;

    private static Scene LoginAdm;
    private static Scene CriarAdm;
    private static Scene menuSelecao;
    private static Scene gerenciadorFilmes;
    private static Scene gerenciarCategorias;
    private static Scene gerenciarCupons;
    private static Scene gerenciarPlanos;
    private static Scene gerenciarUsuario_ADM;
    private static Scene loginUsuario;
    private static Scene cadastroDeusuario;
    private static Scene filmesUsuario;
    private static Scene menuUsuario;
    private static Scene planoUsuario;
    private static Scene principal;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("TORRENTZ FILMES");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("fxmls/login.fxml"));
        LoginAdm = new Scene(fxmlLogin, 238, 146);

        Parent fxmlcadastrarAdm = FXMLLoader.load(getClass().getResource("fxmls/GerenciarAdministradores.fxml"));
        CriarAdm = new Scene(fxmlcadastrarAdm);

        Parent fxmlTelaDeSelecao = FXMLLoader.load(getClass().getResource("fxmls/TelaDeSelecao_adm.fxml"));
        menuSelecao = new Scene(fxmlTelaDeSelecao);

        Parent fxmlgerenciadorFilmes = FXMLLoader.load(getClass().getResource("fxmls/GerenciadorDeFilmes.fxml"));
        gerenciadorFilmes = new Scene(fxmlgerenciadorFilmes);

        Parent fxmltGerenciarCategoria = FXMLLoader.load(getClass().getResource("fxmls/GerenciarCategorias.fxml"));
        gerenciarCategorias = new Scene(fxmltGerenciarCategoria);

        Parent fxmlGerenciarCupons = FXMLLoader.load(getClass().getResource("fxmls/GerenciadorDeCupom.fxml"));
        gerenciarCupons = new Scene(fxmlGerenciarCupons);

        Parent fxmlGerenciarPlanos = FXMLLoader.load(getClass().getResource("fxmls/GerenciadorDePlanos.fxml"));
        gerenciarPlanos = new Scene(fxmlGerenciarPlanos);

        Parent fxmlGerenciarUsuarioADM = FXMLLoader.load(getClass().getResource("fxmls/GerenciadorDeUsuarios.fxml"));
        gerenciarUsuario_ADM = new Scene(fxmlGerenciarUsuarioADM);

        Parent fxmlCadastroUsuario = FXMLLoader.load(getClass().getResource("fxmls/ControlerCadUsuario.fxml"));
        cadastroDeusuario = new Scene(fxmlCadastroUsuario);

        Parent fxmlLoginUsuario = FXMLLoader.load(getClass().getResource("fxmls/ControlerLoginUsuario.fxml"));
        loginUsuario = new Scene(fxmlLoginUsuario);

        Parent fxmlFilmesUsuario = FXMLLoader.load(getClass().getResource("fxmls/FilmesUsuario.fxml"));
        filmesUsuario = new Scene(fxmlFilmesUsuario);

        Parent fxmlMenuUsuario = FXMLLoader.load(getClass().getResource("fxmls/MenuUsuario.fxml"));
        menuUsuario = new Scene(fxmlMenuUsuario);

        Parent fxmlPlanoUsuario = FXMLLoader.load(getClass().getResource("fxmls/GerenciarPlanoUsuario.fxml"));
        planoUsuario = new Scene(fxmlPlanoUsuario);

        Parent fxmlPricipal = FXMLLoader.load(getClass().getResource("fxmls/TelaPrincipal.fxml"));
        principal = new Scene(fxmlPricipal);

        primaryStage.setScene(principal);
        primaryStage.show();

    }

    public static void mudarTela(String scr) throws Exception {

        switch (scr) {
            case "main":
                stage.setScene(LoginAdm);
                break;
            case "cadastraradm":
                stage.setScene(CriarAdm);
                break;
            case "teladeselecao":
                stage.setScene(menuSelecao);
                break;
            case "gerenciafilmes":
                stage.setScene(gerenciadorFilmes);
                break;
            case "gerenciarcategoria":
                stage.setScene(gerenciarCategorias);
                break;
            case "gerenciadorCupons":
                stage.setScene(gerenciarCupons);
                break;
            case "gerenciarPlanos":
                stage.setScene(gerenciarPlanos);
                break;
            case "gerenciarUsuario":
                stage.setScene(gerenciarUsuario_ADM);
                break;
            case "loginUsuario":
                stage.setScene(loginUsuario);
                break;
            case "cadastroDeUsuario":
                stage.setScene(cadastroDeusuario);
                break;
            case "filmesUsuario":
                Parent fxmlFilmesUsuario = FXMLLoader.load(Mainapp.class.getResource("fxmls/FilmesUsuario.fxml"));
                filmesUsuario = new Scene(fxmlFilmesUsuario);

                stage.setScene(filmesUsuario);
                break;
            case "menuUsuario":
                stage.setScene(menuUsuario);
                break;
            case "planoUsuario":
                Parent fxmlPlanoUsuario = FXMLLoader.load(Mainapp.class.getResource("fxmls/GerenciarPlanoUsuario.fxml"));
                planoUsuario = new Scene(fxmlPlanoUsuario);
                stage.setScene(planoUsuario);
                break;
            case "Pricipal":
                stage.setScene(principal);
                break;


        }
    }
}
