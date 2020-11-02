package br.com.watlas.app.Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mainapp extends Application {

    private static Stage stage;

    private  static Scene LoginAdm;
    private static Scene CriarAdm;
    private static  Scene menuSelecao;
    private static Scene gerenciadorFilmes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("LOGIN");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("fxmls/login.fxml"));
        LoginAdm = new Scene(fxmlLogin, 238, 146);

        Parent fxmlcadastrarAdm =  FXMLLoader.load(getClass().getResource("fxmls/cadastrarAdministradorApp.fxml"));
        CriarAdm = new Scene(fxmlcadastrarAdm);

        Parent fxmlTelaDeSelecao = FXMLLoader.load(getClass().getResource("fxmls/TelaDeSelecao_adm.fxml"));
        menuSelecao = new Scene(fxmlTelaDeSelecao);

        Parent fxmlgerenciadorFilmes = FXMLLoader.load(getClass().getResource("fxmls/GerenciadorDeFilmes.fxml"));
        gerenciadorFilmes = new Scene(fxmlgerenciadorFilmes);

        primaryStage.setScene(LoginAdm);
        primaryStage.show();

    }
    public static void mudarTela(String scr) throws Exception {
        switch (scr){
            case "main":
                stage.setScene(LoginAdm);
                break;
            case "cadastraradm":
                stage.setScene(CriarAdm);
                break;
            case "teladeselecao":
                stage.setScene(menuSelecao);
                break;
            case"gerenciafilmes":
                stage.setScene(gerenciadorFilmes);

                break;

        }
    }
}
