/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import models.entities.Usuario;
import services.VendedorServiceDB;

/**
 * FXML Controller class
 *
 * @author carlasanchez
 */
public class AgregarProductoController implements Initializable {

    @FXML
    private VBox vBoxContainer;

    @FXML
    private Label titulo;

    @FXML
    private Label nombreProducto;

    @FXML
    private Label precio;

    @FXML
    private Label descripcion;

    @FXML
    private Label categoria;

    @FXML
    private Label tiempoEntrega;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField precioField;

    @FXML
    private TextField descripcionField;

    @FXML
    private TextField categoriaField;

    @FXML
    private TextField tiempoField;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button guardarButton;
    @FXML
    private ComboBox<String> combobox;
    Articulo articulo;
    private VendedorServiceDB db;
    private Usuario vendedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();

        stage.close();
    }

    public void setVendedorCon(VendedorServiceDB DB,Usuario vendedor) {
        this.db = DB;
        this.vendedor=vendedor;
        List<String> categorias =db.getCategorias();
        for( String cat :categorias){
        combobox.getItems().add(cat);
        }
        combobox.getSelectionModel().selectFirst();

    }

    @FXML
    void guardarAction(ActionEvent event) {
        
        db.agregarProducto(nombreField.getText(),(combobox.getSelectionModel().getSelectedIndex()+1),descripcionField.getText(),
                Double.parseDouble(precioField.getText()),Integer.parseInt(tiempoField.getText()),vendedor.getCedula());

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "El articulo agregó con exito.",
                ButtonType.OK);

        alert.setTitle("Modificación de producto");
        alert.show();
        
        Stage stage = (Stage) guardarButton.getScene().getWindow();

        stage.close();
    }


}
