/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.items;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.entities.Usuario;

/**
 *
 * @author Usuario
 */
public class UserItem extends UserBottomView{
    
    JFXButton editar;
    JFXButton eliminar;
    JFXButton changeRole;

    public UserItem(Usuario user) {
        super();
        foto = new ImageView(new Image("file:src/assets/user.png"));
        nombre.setText(user.getNombres()+" "+user.getApellidos());

        rol.setText(user.getRol().toString());

        informacion.getChildren().addAll(nombre,rol);
       
        editar = new JFXButton("Editar");
        eliminar = new JFXButton("Eliminar");
        changeRole = new JFXButton("Cambiar ROL");
        editar.setPrefWidth(opciones.getPrefWidth());
        eliminar.setPrefWidth(opciones.getPrefWidth());
        changeRole.setPrefWidth(opciones.getPrefWidth());
        opciones.getChildren().addAll(editar,eliminar,changeRole);

        
        cuerpo.getChildren().addAll(foto,informacion,opciones);

    }
}
