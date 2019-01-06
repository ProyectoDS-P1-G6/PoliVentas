package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.AuthInfo;
import models.entities.Usuario;
import services.LoginService;
import views.LoginView;
import views.RegistroForm;

public class LoginController {

    private Usuario usuario;
    private LoginView loginView;

    /*
     * Informacion de autenticacion del usuario.
     * Estado del login.
     */
    AuthInfo authInfo;

    public LoginController(Usuario usuario, LoginView loginView) {
        this.usuario = usuario;
        this.loginView = loginView;

        loginView.addLoginAction(new LoginAction());
        loginView.addLogUpAction(new SignUpAction());
    }

    class LoginAction implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("iniciar sesión....");
            System.out.printf("user: %s password: %s\n",loginView.getUsuarioInput(), loginView.getContrasenaInput() );
            LoginService ls = new LoginService();
            authInfo = ls.authUser(loginView.getUsuarioInput(), loginView.getContrasenaInput());
        }
    }

    class SignUpAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent actionEvent) {
            System.out.println("registrarse....");
            RegistroForm form = new RegistroForm();
            form.show();
        }
    }

}
