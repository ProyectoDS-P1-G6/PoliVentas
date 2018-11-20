/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliventas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import vistas.FAdministrador;
import vistas.FGUIview;
import vistas.FLogin;
import vistas.FRegistro;

/**
 *
 * @author Usuario
 */
public class PoliVentas extends Application{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       //FRegistro registro = new FRegistro();
        FAdministrador admin = new FAdministrador();
        FLogin loginsa = new FLogin();
                        
        Scene scene = new Scene(admin.getPanelAdmin(), 500, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
    }
    public static void main(String[] args) {
        launch(args);
        
    }
}
