package views.items;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.Articulo;

public class Item extends StackPane {
    HBox content;
    ImageView icon;
    VBox description;

    Label nombre;
    Label vendedor;
    Label precio;

    public Item() {
        super();

        description = new VBox();
        content = new HBox(5);

        nombre = new Label();
        vendedor = new Label();
        precio = new Label();
        nombre.getStyleClass().add("nombre-label");
        vendedor.getStyleClass().add("vendedor-label");
        precio.getStyleClass().add("precio-label");
        setAlignment(precio, Pos.BASELINE_RIGHT);
        content.setEffect(new DropShadow(11, Color.rgb(235, 240, 255)));
        content.setOnMouseEntered(event -> content.setEffect(new DropShadow(16, Color.rgb(221, 221, 221))));
        content.setOnMouseExited(t -> content.setEffect(new DropShadow(11, Color.rgb(235, 240, 255))));
        content.getStyleClass().add("content");

        setMargin(content,new Insets(5));
        getChildren().add(content);
        content.getStylesheets().add("assets/item-articulo.css");
    }
}
