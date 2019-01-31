package controllers;

import com.jfoenix.controls.JFXChipView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import utils.Returnable;


public class RegistroFormController implements Initializable, Returnable{

    Returnable Login;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setPreviousWindow(Returnable previous) {
        this.Login = previous;
    }

    @Override
    public void showWindow() {
        
    }

}
