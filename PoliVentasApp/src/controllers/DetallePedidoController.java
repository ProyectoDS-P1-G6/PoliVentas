/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Estado;
import models.Pedido;

/**
 * FXML Controller class
 *
 * @author miguelps
 */
public class DetallePedidoController implements Initializable {

    @FXML
    ImageView image;
    @FXML
    Label nombre;
    @FXML
    Label vendedor;
    @FXML
    Label estado;
    @FXML
    Label total;
    
    @FXML
    Label cantidad;
    @FXML
    Label categoria;
    @FXML
    Label descripcion;
    @FXML
    Label precioUnitario;
    @FXML
    Label fechaPedido;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    
    @FXML
    private void anular(ActionEvent e){
        String answer = "";
        ButtonType buttonYes = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType buttonNO = new ButtonType("No", ButtonBar.ButtonData.NO);
        

        Alert alert = new Alert(Alert.AlertType.WARNING,
                 "¿Esta seguro que desea anular la venta?",
                buttonYes,
                buttonNO);

        alert.setTitle("Confirmar anulación de venta");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(buttonYes).equals("Si")) {
            answer = "Y";
        } else if (result.orElse(buttonNO).equals("No")) {
            answer = "N";
        }
    }
    
    public void setPedido(Pedido p){
        
        image.setImage(p.getArticulo().getIcon());
        nombre.setText(p.getArticulo().getNombre());
        vendedor.setText(p.getArticulo().getVendedor().getNombres());
        
        Estado e = p.getEstado();
        estado.setText(e.toString());
        switch (e){
            case ANULADO:
                estado.setStyle("-fx-text-fill: red;");break;
            case ENVIADO:
                estado.setStyle("-fx-text-fill: blue;");break;
            case ENTREGADO:
                estado.setStyle("-fx-text-fill: black;");break;
            case PENDIENTE:
                estado.setStyle("-fx-text-fill: green;");break;
        }
        
        total.setText(p.getArticulo().getPrecio().multipliedBy(p.getCantidad()).toString());
        cantidad.setText(p.getCantidad().toString());
        categoria.setText(p.getArticulo().getCategoria());
        descripcion.setText(p.getArticulo().getDescripción());
        precioUnitario.setText(p.getArticulo().getPrecio().toString());
        fechaPedido.setText(p.getFecha().toString());
    }
    
}
