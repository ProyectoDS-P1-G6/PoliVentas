package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.entities.Usuario;
import utils.StageDecoratorX;
import views.Inicio;
import views.LoginView;

public class InicioController{
    
	private Inicio inicio;
	
	public InicioController(Inicio view){
		this.inicio = view;
		inicio.addLoginAction(new SignUpAction());	
		inicio.addregisterinAction(new registerAction());
	}
	
	class SignUpAction implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
                    System.out.println("login...");
                    LoginView login = new LoginView();
                    new StageDecoratorX(login);
                    new LoginController(new Usuario(), login);
                    inicio.hide();
                    login.show();
			
		}
		
    }
	class registerAction implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
                    Stage stage = new Stage();
                    
                    try {
                        FXMLLoader loader;
                        loader = new FXMLLoader(getClass().getResource("/views/RegistroForm.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        new StageDecoratorX(stage);
                    } catch (IOException ex) {
                        Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                    stage.show();
                    inicio.close();

			
		}
	}
}
