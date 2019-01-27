/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.popup;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import views.items.ArticuloItem;

/**
 *
 * @author carlasanchez
 */
public class ModificarProducto extends Stage{

    protected final AnchorPane root;
    protected final VBox vBoxContainer;
    protected final Label titulo;
    protected final GridPane gridPane;
    protected final Label nombre_producto;
    protected final Label precio;
    protected final Label descripcion;
    protected final Label categoria;
    protected final Label tiempo_entrega;
    protected final TextField nombre_field;
    protected final TextField precio_field;
    protected final TextField descripcion_field;
    protected final TextField categoria_field;
    protected final TextField tiempo_field;
    protected final HBox hBox;
    protected final Button cancelarbutton;
    protected final Button guardarbutton;

    public ModificarProducto( ArticuloItem item) {
        root = new AnchorPane();
        vBoxContainer = new VBox();
        titulo = new Label();
        gridPane = new GridPane();
        nombre_producto = new Label();
        precio = new Label();
        descripcion = new Label();
        categoria = new Label();
        tiempo_entrega = new Label();
        nombre_field = new TextField();
        precio_field = new TextField();
        descripcion_field = new TextField();
        categoria_field = new TextField();
        tiempo_field = new TextField();
        hBox = new HBox();
        cancelarbutton = new Button();
        guardarbutton = new Button();
        root.setPrefHeight(365.0);
        root.setPrefWidth(500.0);
        root.getStylesheets().add("assets/Vendedor_Items.css");
        
        vBoxContainer.setPrefHeight(292.0);
        vBoxContainer.setPrefWidth(500.0);

        titulo.setPrefHeight(39.0);
        titulo.setPrefWidth(614.0);
        titulo.getStyleClass().add("title");
        titulo.setText("Modificar producto");
        VBox.setMargin(titulo, new Insets(10.0, 0.0, 0.0, 0.0));

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setHgap(5.0);
        gridPane.setPrefHeight(224.0);
        gridPane.setPrefWidth(480.0);
        gridPane.setVgap(5.0);
        
        nombre_producto.getStyleClass().add("nombre_label");
        nombre_producto.setText("Nombre del producto:");

        GridPane.setRowIndex(precio, 1);
        precio.getStyleClass().add("nombre_label");
        precio.setText("Precio:");

        GridPane.setRowIndex(descripcion, 2);
        descripcion.getStyleClass().add("nombre_label");
        descripcion.setText("Descripción:");

        GridPane.setRowIndex(categoria, 3);
        categoria.getStyleClass().add("nombre_label");
        categoria.setText("Categoría:");

        GridPane.setRowIndex(tiempo_entrega, 4);
        tiempo_entrega.getStyleClass().add("nombre_label");
        tiempo_entrega.setText("Tiempo máximo de entrega:");

        GridPane.setColumnIndex(nombre_field, 1);

        GridPane.setColumnIndex(precio_field, 1);
        GridPane.setRowIndex(precio_field, 1);

        GridPane.setColumnIndex(descripcion_field, 1);
        GridPane.setRowIndex(descripcion_field, 2);
        descripcion_field.setAlignment(javafx.geometry.Pos.CENTER);
        descripcion_field.setPrefHeight(40.0);

        GridPane.setColumnIndex(categoria_field, 1);
        GridPane.setRowIndex(categoria_field, 3);

        GridPane.setColumnIndex(tiempo_field, 1);
        GridPane.setRowIndex(tiempo_field, 4);
        gridPane.setPadding(new Insets(5.0));
        VBox.setMargin(gridPane, new Insets(0.0, 10.0, 0.0, 15.0));

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setLayoutY(281.0);
        hBox.setPrefHeight(64.0);
        hBox.setPrefWidth(500.0);
        hBox.setSpacing(100.0);

        cancelarbutton.setMnemonicParsing(false);
        cancelarbutton.setOnAction(this::cancelarAction);
        cancelarbutton.setPrefHeight(40.0);
        cancelarbutton.setPrefWidth(90.0);
        cancelarbutton.getStyleClass().add("button_style");
        cancelarbutton.setText("Cancelar");

        guardarbutton.setAlignment(javafx.geometry.Pos.CENTER);
        guardarbutton.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        guardarbutton.setMnemonicParsing(false);
        guardarbutton.setOnAction(this::guardarAction);
        guardarbutton.setPrefHeight(40.0);
        guardarbutton.setPrefWidth(90.0);
        guardarbutton.getStyleClass().add("button_style");
        guardarbutton.setText("Guardar");

        vBoxContainer.getChildren().add(titulo);
        
        gridPane.getChildren().add(nombre_producto);
        gridPane.getChildren().add(precio);
        gridPane.getChildren().add(descripcion);
        gridPane.getChildren().add(categoria);
        gridPane.getChildren().add(tiempo_entrega);
        gridPane.getChildren().add(nombre_field);
        gridPane.getChildren().add(precio_field);
        gridPane.getChildren().add(descripcion_field);
        gridPane.getChildren().add(categoria_field);
        gridPane.getChildren().add(tiempo_field);
        vBoxContainer.getChildren().add(gridPane);
        root.getChildren().add(vBoxContainer);
        hBox.getChildren().add(cancelarbutton);
        hBox.getChildren().add(guardarbutton);
        root.getChildren().add(hBox);

    }

    void cancelarAction(ActionEvent actionEvent){
    }

    void guardarAction(ActionEvent actionEvent){}
}
