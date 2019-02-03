package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import models.entities.Comprador;
import models.entities.Usuario;
import models.entities.Vendedor;
import services.LoginServiceDB;
import utils.Returnable;


public class RegistroFormController implements Initializable, Returnable{

    Returnable previousWindow;
    
    @FXML JFXComboBox<Label> tipoUsuario;
    
    @FXML JFXTextField nombres;
    @FXML JFXTextField cedula;
    @FXML JFXTextField apellidos;
    @FXML JFXTextField email;
    @FXML JFXTextField nMatricula;
    @FXML JFXTextField telefono;
    @FXML JFXTextField direccion;
    
    @FXML JFXRadioButton siUsaWhatsapp;
    @FXML JFXRadioButton noUsaWhatsapp;
    
    LoginServiceDB db;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup usaWhatsappSelector = new ToggleGroup();
        siUsaWhatsapp.setToggleGroup(usaWhatsappSelector);
        noUsaWhatsapp.setToggleGroup(usaWhatsappSelector);
        noUsaWhatsapp.setSelected(true);
        db = new LoginServiceDB();
    }

    
    
    
    @FXML
    void registrar(ActionEvent event){
        
        Usuario usuario;
        tipoUsuario.setStyle("-fx-border-color: white;");
        if(!tipoUsuario.getSelectionModel().isEmpty()){
            if(tipoUsuario.getValue().getText().equals("Comprador")){
                usuario = new Comprador();
            }
            else{
                usuario = new Vendedor();
            }
        }
        else{
            tipoUsuario.setStyle("-fx-border-color: red;");
            return;
        }
            
        
        usuario.setNombres(nombres.getText());
        usuario.setApellidos(apellidos.getText());
        try {
            cedula.setStyle("-fx-border-color: white;");
            usuario.setCedula(Integer.parseInt(cedula.getText()));
        }
        catch (NumberFormatException e) {
            cedula.setStyle("-fx-border-color: red;");
            return;
        }
        
        try {
            email.setStyle("-fx-border-color: white;");
            telefono.setStyle("-fx-border-color: white;");
            usuario.setContactInfo(email.getText(),Integer.parseInt( telefono.getText()), siUsaWhatsapp.isSelected());
           
        } catch (AddressException ex) {
            email.setStyle("-fx-border-color: red;");
            return;
        } catch(NumberFormatException e){
            telefono.setStyle("-fx-border-color: red;");
            return;
        }
        
        usuario.setDireccion(direccion.getText());
        
        try {
            nMatricula.setStyle("-fx-border-color: white;");
            usuario.setMatricula(Integer.parseInt(nMatricula.getText()));
        }
        catch (NumberFormatException e) {
            nMatricula.setStyle("-fx-border-color: red;");
            return;
        }
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("se regitraran los siguentes datos\n"+ usuario);
        
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK){
            Alert msg;
            if(!db.registerUser(usuario)){
                msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setContentText("No se pudo registrar\nPor favor revise su matricula y cedula.");
                msg.show();
                return;
            }
            msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setContentText("Registro exitoso.");
            msg.showAndWait();
            previousWindow.showWindow();
            
        }  
        
    }
    
    
    @FXML
    void limpiar(ActionEvent event){
        nombres.clear();
        cedula.clear();
        apellidos.clear();
        email.clear();
        nMatricula.clear();
        telefono.clear();
        direccion.clear();
    }
    
    
    
    @Override
    public void showWindow() {
        ((Stage)nombres.getScene().getWindow()).show();
    }
    
    
    @Override
    public void setPreviusWindow(Returnable previous) {
        this.previousWindow = previous;
    }
    

}
