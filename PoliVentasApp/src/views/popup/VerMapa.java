package views.popup;

import java.lang.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VerMapa extends Stage {

    private AnchorPane root;
    protected final VBox vBox;
    protected final Label label;
    protected final ImageView imageView;

    public VerMapa() {
        root = new AnchorPane();
        vBox = new VBox();
        label = new Label();
        imageView = new ImageView();
        
        
        root.setPrefHeight(400.0);
        root.setPrefWidth(500.0);
        
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setLayoutX(24.0);
        vBox.setLayoutY(28.0);
        vBox.setPrefHeight(400.0);
        vBox.setPrefWidth(600.0);

        label.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutY(30.0);
        label.setPrefHeight(71.0);
        label.setPrefWidth(500.0);
        label.setText("Mapa de entrega");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#4868d0"));
        label.setFont(new Font("Tahoma", 36.0));

        imageView.setFitHeight(350.0);
        imageView.setFitWidth(450.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(("file:src/assets/mapa.jpg")));

        vBox.getChildren().add(label);
        vBox.getChildren().add(imageView);
        root.getChildren().add(vBox);
        setScene(new Scene(root));
        

    }
}
