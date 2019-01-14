/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.items;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.entities.Usuario;

/**
 *
 * @author Usuario
 */
public class UserBottomView extends StackPane{
    HBox cuerpo;
    ImageView foto;
    VBox informacion;
    Label nombre;
    Label rol;
    VBox opciones;
    
    public UserBottomView(){
        super();
        cuerpo = new HBox();
        informacion = new VBox(3);
        opciones = new VBox(3);
        
        nombre = new Label();
        rol = new Label();
        
        foto =  new ImageView();
        foto.setFitWidth(60);
        foto.setFitHeight(60);
        
        
        informacion.setAlignment(Pos.CENTER_LEFT);
        informacion.setPrefSize(400, 90);
        
        opciones.setAlignment(Pos.CENTER);
        opciones.setPrefWidth(80);
        
        
        cuerpo.setPrefSize(500, 100);
        cuerpo.setAlignment(Pos.CENTER);
        getChildren().add(cuerpo);
      
        
    }
                    
//    public void createButtonUser(Usuario user){
//        nombre.setText(user.getNombres()+" "+user.getApellidos());
//        switch(user.getRol()){
//            case ADMIN:
//                rol.setText("Administrador");
//                break;
//            case COMPRADOR:
//                rol.setText("Comprador");
//                break;
//            case VENDEDOR:
//                rol.setText("Vendedor");
//                break;
//        }
//        foto = new ImageView(new Image("file:src/assets/env1.png"));
//    }
        
}
