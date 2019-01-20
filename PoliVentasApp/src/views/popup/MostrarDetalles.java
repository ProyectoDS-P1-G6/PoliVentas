package views.popup;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import views.items.Item;

public class MostrarDetalles extends Stage {
    VBox root;
    public MostrarDetalles(Item item) {

        super();
        root = new VBox(5);
        JFXButton comprar = new JFXButton("Comprar");

        comprar.setStyle("-jfx-button-type: RAISED;" +
                         "-fx-background-color: rgb(77,102,204);" +
                "");
        root.getChildren().addAll(item, comprar);

        setScene(new Scene(root));
    }
}
