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
import models.entities.Vendedor;
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
    private Label nombre_producto;

    @FXML
    private Label precio;

    @FXML
    private Label descripcion;

    @FXML
    private Label categoria;

    @FXML
    private Label tiempo_entrega;

    @FXML
    private TextField nombre_field;

    @FXML
    private TextField precio_field;

    @FXML
    private TextField descripcion_field;

    @FXML
    private TextField categoria_field;

    @FXML
    private TextField tiempo_field;

    @FXML
    private Button cancelarbutton;

    @FXML
    private Button guardarbutton;
    @FXML
    private ComboBox<String> combobox;
    Articulo articulo;
    private VendedorServiceDB db;
    private Vendedor vendedor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void cancelarAction(ActionEvent event) {
Stage stage = (Stage) cancelarbutton.getScene().getWindow();

        stage.close();
    }

    public void setVendedorCon(VendedorServiceDB DB,Vendedor vendedor) {
        this.db = DB;
        this.vendedor=vendedor;
        List<String> categorias =db.getCategorias();
        for( String cat :categorias){
            combobox.getItems().add(cat);
        }
    }

    @FXML
    void guardarAction(ActionEvent event) {
        System.out.println(combobox.getValue() );
        db.agregarProducto(nombre_field.getText(),(combobox.getSelectionModel().getSelectedIndex()+1),descripcion_field.getText(),
                Double.parseDouble(precio_field.getText()),Integer.parseInt(tiempo_field.getText()),vendedor.getCedula());

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "El articulo agregó con exito.",
                ButtonType.OK);

        alert.setTitle("Modificación de producto");
        alert.show();
        
        Stage stage = (Stage) guardarbutton.getScene().getWindow();

        stage.close();
    }


}
