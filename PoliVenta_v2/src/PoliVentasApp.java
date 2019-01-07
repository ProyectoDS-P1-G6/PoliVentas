/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.SceneX;
import views.LoginView;

/**
 *
 * @author Miguel PS
 */
public class PoliVentasApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginView loginView = new LoginView();

        SceneX view = new SceneX(primaryStage); // decorator
        view.setContent(loginView.getRoot());
        view.getScene().getStylesheets().add("assets/loginView.css");

        new LoginController(new Usuario(), loginView);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
