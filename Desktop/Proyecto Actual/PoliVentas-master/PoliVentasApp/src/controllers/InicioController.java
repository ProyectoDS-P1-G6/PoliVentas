package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.entities.Usuario;
import utils.StageDecoratorX;
import views.Inicio;
import views.LoginView;
import views.RegistroForm;

public class InicioController {
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
                    inicio.close();
                    login.show();
			
		}
		
    }
	class registerAction implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
                    System.out.println("register...");
                    RegistroForm registro = new RegistroForm();
                    new StageDecoratorX(registro);
                    inicio.close();
                    registro.show();
			
		}
	}
}
