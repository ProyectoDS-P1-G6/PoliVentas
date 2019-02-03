/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.items;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.entities.Usuario;

/**
 *
 * @author Usuario
 */
public class UserItem extends UserBottomView{
    Usuario user;
    JFXButton editar;
    JFXButton eliminar;
    JFXButton changeRole;
    public UserItem(){}
    public UserItem(Usuario usuario) {
        super();
        this.user=usuario;
        foto = new ImageView(new Image("file:src/assets/iconUsermale.png"));
        foto.setFitHeight(50); foto.setFitWidth(50);
        
        nombre.setText(user.getNombres()+" "+user.getApellidos());

        rol.setText(user.getRol().toString());

        informacion.getChildren().addAll(nombre,rol);
       
        editar = new JFXButton("Editar");
        editar.setStyle("-fx-background-color: #02B58F");
        editar.getStyleClass().add("colorlabel");
        eliminar = new JFXButton("Eliminar");
        eliminar.setStyle("-fx-background-color: #b52902");
        eliminar.getStyleClass().add("colorlabel");
        changeRole = new JFXButton("Cambiar ROL");
        changeRole.setStyle("-fx-background-color: #e5ce14");
        changeRole.getStyleClass().add("colorlabel");
        editar.setPrefWidth(opciones.getPrefWidth());
        eliminar.setPrefWidth(opciones.getPrefWidth());
        changeRole.setPrefWidth(opciones.getPrefWidth());
        opciones.getChildren().addAll(editar,eliminar,changeRole);

        
        cuerpo.getChildren().addAll(foto,informacion,opciones);

    }
    public Usuario obtenerUser(){
        return this.user;
    }
    public void btnChangeRolAction(EventHandler<ActionEvent> eventHandler){
        changeRole.setOnAction(eventHandler);
    }
    
    public void btnEliminarAction(EventHandler<ActionEvent> eventHandler){
        eliminar.setOnAction(eventHandler);
        
    }
    public void btnEditarAction(EventHandler<ActionEvent> eventHandler){
        editar.setOnAction(eventHandler);
    }
   
}
