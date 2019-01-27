package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
