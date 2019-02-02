/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author carlasanchez
 */
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
import services.VendedorServiceDB;

public class ModificarProductoController implements Initializable {

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
    VendedorServiceDB db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        Stage stage = (Stage) cancelarbutton.getScene().getWindow();

        stage.close();
    }

    public void setArticulo(Articulo articulo, VendedorServiceDB db) {
        this.articulo = articulo;
        this.db = db;
        List<String> categorias = db.getCategorias();
        for (String cat : categorias) {
            combobox.getItems().add(cat);
        }
        nombre_field.setText(articulo.getNombre());
        precio_field.setText(articulo.getPrecio().getAmount().toString());
        tiempo_field.setText(Integer.toString(articulo.getTiempo_max_entrega()));
        descripcion_field.setText(articulo.getDescripción());
        
    }

    @FXML
    void guardarAction(ActionEvent event) {
        Articulo art = new Articulo();
        art.setId(articulo.getId());
        art.setNombre(nombre_field.getText());
        art.setCategoria(combobox.getValue());
        art.setDescripción(descripcion_field.getText());
        art.setPrecio(Double.parseDouble(precio_field.getText()));
        art.setTiempo_max_entrega(Integer.parseInt(tiempo_field.getText()));
        art.setIcon(articulo.getIcon().toString());
        art.setVendedor(articulo.getVendedor());
        art.setNumero_busquedas(articulo.getNumero_busquedas());
        db.modificarProducto(art);

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "El articulo se modificó con exito.",
                ButtonType.OK);

        alert.setTitle("Modificación de producto");
        alert.show();
        Stage stage = (Stage) guardarbutton.getScene().getWindow();

        stage.close();
        
    }
}
