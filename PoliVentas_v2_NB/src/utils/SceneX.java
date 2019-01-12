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

public class SceneX {
    double x;
    double y;

    private Scene scene;
    private Pane content;
    private VBox all;

    public SceneX(Stage stage) {

        all = new VBox(5);
        content = new Pane();
        scene = new Scene(all);
        stage.setScene(scene);
        scene.getStylesheets().add("assets/paneX.css");
        stage.initStyle(StageStyle.UNDECORATED);

        Button close = new Button();
        Button minimize = new Button();

        close.getStyleClass().add("button_close");
        minimize.getStyleClass().add("button_minimize");

        HBox options = new HBox(1);
        options.getChildren().addAll(minimize, close);
        options.setAlignment(Pos.TOP_RIGHT);

        all.getChildren().add(options);
        all.getStyleClass().add("root");
        all.getChildren().add(content);


        close.setOnMouseClicked((event)->{
            stage.close();
        });
        minimize.setOnMouseClicked((event -> {
            stage.setIconified(true);
        }));


        this.all.setOnMousePressed( event -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });

        this.all.setOnMouseDragged( event -> {
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

    public Scene getScene() {
        return scene;
    }
}
