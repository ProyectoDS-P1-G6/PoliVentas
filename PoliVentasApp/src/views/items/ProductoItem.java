/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.items;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import static javafx.scene.layout.StackPane.setAlignment;
import static javafx.scene.layout.StackPane.setMargin;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.Articulo;

/**
 *
 * @author carlasanchez
 */
public class ProductoItem extends SearchItem {

    HBox content;
    ImageView icon;
    VBox description;

    Label nombre_producto;
    Label nombre_vendedor;
    Label precio;
    Label categoria;
    Label descripcion;
    Label tiempo;

    public ProductoItem(Articulo articulo) {
        super(articulo);

        description = new VBox();
        content = new HBox(5);

        nombre_producto = new Label();
        nombre_vendedor = new Label();
        precio = new Label();
        categoria = new Label();
        descripcion = new Label();
        tiempo = new Label();
        nombre_producto.getStyleClass().add("nombre-label");
        nombre_vendedor.getStyleClass().add("text-style");
        precio.getStyleClass().add("precio-label");
        categoria.getStyleClass().add("text-style");
        descripcion.getStyleClass().add("text-style");
        tiempo.getStyleClass().add("text-style");
        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() + " " + articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());
        categoria.setText(articulo.getCategoria());
        descripcion.setText(articulo.getDescripción());
        tiempo.setText(Integer.toString(articulo.getTiempo_max_entrega()));

        setAlignment(precio, Pos.BASELINE_RIGHT);
        content.setEffect(new DropShadow(11, Color.rgb(235, 240, 255)));
        content.setOnMouseEntered(event -> content.setEffect(new DropShadow(16, Color.rgb(221, 221, 221))));
        content.setOnMouseExited(t -> content.setEffect(new DropShadow(11, Color.rgb(235, 240, 255))));
        content.getStyleClass().add("content");
        description.getChildren().addAll(nombre_producto, nombre_vendedor, precio,categoria,tiempo);
        content.getChildren().addAll(new ImageView(new Image("file:src/assets/env1.png")), description);

        setMargin(content, new Insets(5));
        getChildren().add(content);
        content.getStylesheets().add("assets/Vendedor_Items.css");
    }
}
