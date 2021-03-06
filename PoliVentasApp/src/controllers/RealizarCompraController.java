/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Articulo;
import models.Pedido;
import models.pago.MetodoPago;
import models.pago.PagoEfectivo;
import models.pago.PagoVirtual;
import services.CompradorServiceDB;
import services.LoginServiceDB;
import utils.NotificarVenta;
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
    
    Articulo articulo;
    @FXML
    JFXButton comprar;
    
    CompradorServiceDB db;

    private NotificarVenta nv;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectorPago = new ToggleGroup();
        pago_efectivo.setToggleGroup(selectorPago);
        pago_efectivo.setSelected(true);
        pago_virtual.setToggleGroup(selectorPago);
        cantidad_slider.setValue(0);
        cantidad_slider.valueProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    cantidad.setText(String.valueOf(new_val.intValue()));
                });
        nv = new NotificarVenta();
   }
   
   @FXML
   void realizarCompra(ActionEvent e){
        MetodoPago metodoPago;
        Alert transactionMessage = new Alert(Alert.AlertType.CONFIRMATION);
        
        Pedido pedido = new  Pedido();
        pedido.setArticulo(articulo);
        pedido.setComprador(LoginServiceDB.getActualLogin().getUsuario());
        pedido.setCantidad(Integer.parseInt(cantidad.getText()));
        double tot = articulo.getPrecio().getAmount().doubleValue()*pedido.getCantidad();
        
        pedido.setTotal((Double)tot);
        pedido.setFecha(Calendar.getInstance().getTime());
        
        if(pago_efectivo.isSelected()){
            metodoPago = new PagoEfectivo(db);
            transactionMessage.setContentText("Por favor deposite el dinero en la ranura..."); 
        } 
        else{
            metodoPago = new PagoVirtual(db);
            transactionMessage.setContentText(String.format("Confirmar datos de compra:\n%s",pedido)); 
        }
        
        Optional<ButtonType> result = transactionMessage.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            metodoPago.transferir(pedido);
            ((Stage)nombre.getScene().getWindow()).close();
        }
        String msj =  pedido.getArticulo().getNombre() + " por el comprador "+ pedido.getComprador().getNombres()+" "+ pedido.getComprador().getApellidos()+ "\n"+"El total del pedido es "+ Double.toString(tot);
        nv.SendMail(pedido.getArticulo().getVendedor().getContactInfo().getEmail().getAddress(),msj);

   }
    
    public void setArticulo(Articulo a){
        this.articulo = a;
        imagen.setImage(a.getIcon());
        nombre.setText(a.getNombre());
        precio.setText(a.getPrecio().toString());
        descripcion.setText(a.getDescripción());
        vendedor.setText(String.format("%s %s",a.getVendedor().getNombres(), a.getVendedor().getApellidos()));
    }
    
    
    public void setDBService(CompradorServiceDB db){
        this.db = db;
    };

    public CompradorServiceDB getDb() {
        return db;
    }
    
    public Articulo getArticulo(){
    return this.articulo;
    }
}
