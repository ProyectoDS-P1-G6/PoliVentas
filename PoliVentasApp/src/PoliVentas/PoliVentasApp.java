package PoliVentas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.InicioController;
import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.StageDecoratorX;
import views.Inicio;
import views.LoginView;

/**
 *
 * @author Miguel PS
 */
public class PoliVentasApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Inicio inicioView = new Inicio();
        new StageDecoratorX(inicioView); // decorator
        new InicioController(inicioView);
        inicioView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
