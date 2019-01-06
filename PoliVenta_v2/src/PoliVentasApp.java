/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.Constants;
import utils.PaneX;
import views.LoginView;

/**
 *
 * @author Miguel PS
 */
public class PoliVentasApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        PaneX view = new PaneX(primaryStage,300, 300); // decorator

        LoginView loginView = new LoginView();
        view.setContent(loginView.getRoot());

        new LoginController(new Usuario(), loginView);

        Scene scene = new Scene(view);
        scene.getStylesheets().add("assets/loginView.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
