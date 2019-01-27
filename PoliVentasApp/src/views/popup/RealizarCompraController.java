/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.popup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import views.items.ArticuloItem;

/**
 * FXML Controller class
 *
 * @author miguelps
 */
public class RealizarCompraController implements Initializable {

    @FXML
    ImageView imagen;
    @FXML
    Label nombre;
    @FXML
    JFXTextArea descripcion;
    @FXML
    Label precio;
    @FXML
    Label vendedor;
            
    ToggleGroup selectorPago; 
    @FXML
    JFXRadioButton pago_efectivo;
    @FXML
    JFXRadioButton pago_virtual;
    
    @FXML
    JFXSlider cantidad_slider;
    @FXML
    Label cantidad;
    
    ArticuloItem item;
    @FXML
    JFXButton comprar;

    
    public RealizarCompraController(){  
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectorPago = new ToggleGroup();
        pago_efectivo.setToggleGroup(selectorPago);
        pago_virtual.setToggleGroup(selectorPago);
        
        cantidad_slider.valueProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    cantidad.setText(String.valueOf(new_val.intValue()));
                });
        
   }
    
    public void setItem(ArticuloItem item){
        this.item = item;
        imagen.setImage(item.getIcon());
        nombre.setText(item.getNombre_producto());
        precio.setText(item.getPrecio());
        vendedor.setText(item.getNombre_vendedor());
    }
    
}
