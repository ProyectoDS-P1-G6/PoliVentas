package views.popup;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Articulo;

public class ProductDetail extends Stage {

    public ProductDetail(Articulo articulo) {
        super();

        Pane root = new Pane();
        setScene(new Scene(root));
    }
}
