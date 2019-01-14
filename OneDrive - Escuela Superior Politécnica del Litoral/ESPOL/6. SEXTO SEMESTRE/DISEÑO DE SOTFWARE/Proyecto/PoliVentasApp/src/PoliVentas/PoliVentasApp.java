package PoliVentas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.StageDecoratorX;
import views.LoginView;

/**
 *
 * @author Miguel PS
 */
public class PoliVentasApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginView loginView = new LoginView();
        new StageDecoratorX(loginView); // decorator
        new LoginController(new Usuario(), loginView);
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
