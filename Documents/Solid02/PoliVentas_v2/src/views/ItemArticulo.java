package views;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Articulo;


public class ItemArticulo extends FlowPane {

    public ItemArticulo(Articulo articulo){
        super();

        HBox content = new HBox(5);

        ImageView icon = new ImageView(new Image("file:assets/ord.png"));
        VBox description = new VBox();

        description.getChildren().add(new Label("nombrexxxx: "));
        description.getChildren().add(new Label("Descripción corta"));

        content.getChildren().addAll(icon, description);

        getChildren().add(content);
    }
}
