package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.Returnable;
import utils.StageDecoratorX;
import views.Inicio;
import views.LoginView;

public class InicioController implements Returnable{
    
	private Inicio inicio;
	
	public InicioController(Inicio view){
		this.inicio = view;
        
		inicio.addLoginAction((ActionEvent event) -> {
            LoginView login = new LoginView();
            new StageDecoratorX(login);
            LoginController controller = new LoginController(new Usuario(), login);
            controller.setPreviusWindow(this);
            inicio.hide();
            login.setOnCloseRequest((e)->{
                inicio.show();
            });
            login.show();
        });	
        
        
		inicio.addregisterinAction((ActionEvent event) -> {
            Stage stage = new Stage();
            
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/views/RegistroForm.fxml"));
                stage.setScene(new Scene(loader.load()));
                loader.<RegistroFormController>getController().setPreviusWindow(InicioController.this);
                new StageDecoratorX(stage);
            } catch (IOException ex) {
                Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            stage.show();
            inicio.close();
        });
	}

    @Override
    public void setPreviusWindow(Returnable previous) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showWindow() {
        this.inicio.show();
    }
}
