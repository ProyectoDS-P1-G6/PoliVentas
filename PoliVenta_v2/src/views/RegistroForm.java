package views;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistroForm extends Stage {

    Scene scene;
    Pane root;

    public RegistroForm(){
        super();
        scene= new Scene(root);
        setScene(scene);
    }
}
