package utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageDecoratorX {
    double x;
    double y;

    private VBox all;

    public StageDecoratorX(Stage stage) {

        all = new VBox(5);
        stage.initStyle(StageStyle.UNDECORATED);

        Button close = new Button();
        Button minimize = new Button();

        close.getStyleClass().add("button_close");
        minimize.getStyleClass().add("button_minimize");

        HBox options = new HBox(0);
        options.getChildren().addAll(minimize, close);
        options.setAlignment(Pos.TOP_RIGHT);

        all.getChildren().add(options);
        all.getStyleClass().add("root");
        all.getChildren().add(stage.getScene().getRoot());
        all.getStylesheets().add("assets/paneX.css");

        stage.getScene().setRoot(all);


        close.setOnMouseClicked((event)->{
            stage.close();
        });
        minimize.setOnMouseClicked((event -> {
            stage.setIconified(true);
        }));


        all.setOnMousePressed( event -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });

        all.setOnMouseDragged( event -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });

    }

}
