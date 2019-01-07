package utils;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaneX extends AnchorPane {
    double x;
    double y;

    Pane content;
    VBox all;

    public PaneX(Stage stage, double width, double heigth) {

        getStylesheets().add("assets/paneX.css");
        stage.initStyle(StageStyle.UNDECORATED);

        Button close = new Button();
        Button minimize = new Button();

        close.getStyleClass().add("button_close");
        minimize.getStyleClass().add("button_minimize");

        HBox options = new HBox(1);
        options.getChildren().addAll(minimize, close);
        options.setAlignment(Pos.TOP_RIGHT);

        content= new Pane();
        all = new VBox(5);
        all.getChildren().add(options);
        all.getStyleClass().add("root");
        getChildren().add(all);


        close.setOnMouseClicked((event)->{
            stage.close();
        });
        minimize.setOnMouseClicked((event -> {
            stage.setIconified(true);
        }));


        this.setOnMousePressed( event -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });

        this.setOnMouseDragged( event -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });

    }

    public Pane getContent(){
        return content;
    }

    public void setContent(Pane content) {
        this.content = content;
        all.getChildren().add(this.content);
    }
}
