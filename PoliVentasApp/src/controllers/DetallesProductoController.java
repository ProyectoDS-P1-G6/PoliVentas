/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FXML Controller class
 *
 * @author carlasanchez
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Articulo;
import services.VendedorServiceDB;
import utils.StageDecoratorX;

public class DetallesProductoController {

    @FXML
    private Label titulo;

    @FXML
    private ImageView icon;

    @FXML
    private Label nombre;

    @FXML
    private Label categoria;

    @FXML
    private Label precio;

    @FXML
    private Label descripcion;

    @FXML
    private Label tiempo;

    @FXML
    private Button modificar_button;

    @FXML
    private Button eliminar_button;

    @FXML
    private TextArea descripcion_box;
    @FXML
    private HBox hbox;
    @FXML
    private ScrollBar scroll;
    private VendedorServiceDB db;

    Articulo articulo;

    @FXML
    void eliminarAction(ActionEvent event) {
        System.out.println("eliminar");
    }

    @FXML
    void modificarAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/ModificarProducto.fxml"));
        Stage modificar = new Stage();
        try {
            modificar.setScene(new Scene(loader.load()));
            new StageDecoratorX(modificar);
            modificar.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModificarProductoController controller = loader.getController();
        controller.setArticulo(this.articulo, getDb());
        
    }

    public void setArticulo(Articulo articulo, VendedorServiceDB db) {
        this.articulo = articulo;
        this.db = db;
        icon.setImage(articulo.getIcon());        
        nombre.setText("Nombre: " + articulo.getNombre());
        precio.setText("Precio: " + articulo.getPrecio().toString());
        tiempo.setText("Tiempo máximo de entrega: " + articulo.getTiempo_max_entrega());
        descripcion.setText("Descripcion: " + articulo.getDescripción());
        categoria.setText("Categoria: " + articulo.getCategoria());

    }

    public VendedorServiceDB getDb() {
        return db;
    }
}
