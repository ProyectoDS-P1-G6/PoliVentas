/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliventas;

import controllers.GestionAccesos;
import controllers.GestionCompras;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import vistas.FAdministrador;
import vistas.FGUIview;
import vistas.FLogin;
import vistas.FRegistro;
import vistas.FVendedor;

/**
 *
 * @author Usuario
 */
public class PoliVentas extends Application{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    private GestionAccesos accesoLogin;
    private GestionCompras compras;
    public static FGUIview ventanaPrincipal;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ventanaPrincipal= new FGUIview(); 
        initInterface();
        Scene scene = new Scene(ventanaPrincipal.getVentana(), 800, 600);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
    }
    public static void main(String[] args) {
        launch(args);
        
    }
    public void initInterface(){
        this.accesoLogin = new GestionAccesos();
    }
}
