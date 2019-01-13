package views.items;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.Articulo;

public class SearchItem extends Item{


    public SearchItem(Articulo articulo) {
        super();

        nombre.setText(articulo.getNombre());
        vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());

        description.getChildren().addAll(nombre,vendedor,precio);

        content.getChildren().addAll(new ImageView(new Image("file:src/assets/env1.png")), description);

    }
}
