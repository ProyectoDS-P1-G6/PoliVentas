package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Usuario;
import models.entities.Vendedor;
import services.DBConnection;
import services.LoginServiceDB;
import utils.StageDecoratorX;
import views.*;

public class LoginController {

    private Usuario usuario;
    private LoginView view;

    /*
     * Informacion de autenticacion del usuario.
     * Estado del login.
     */
    AuthInfo authInfo;
    LoginServiceDB ls;

    public LoginController(Usuario usuario, LoginView view) {
        this.usuario = usuario;
        this.view = view;

        ls = new LoginServiceDB();

        view.addLoginAction(new LoginAction());
        view.addLogUpAction(new SignUpAction());

        view.setOnCloseRequest(
                windowEvent -> { DBConnection.shutdownConnection();
                System.out.println("Cerrada la conección");}
                );
    }

    class LoginAction implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("iniciar sesión....");
            System.out.printf("user: %s password: %s\n", view.getUsuarioInput(), view.getContrasenaInput() );
            authInfo = ls.authUser(view.getUsuarioInput(), view.getContrasenaInput());

            if(!authInfo.isLoggeoExitoso()){
                view.setStatusMessage("** Datos incorrectos.");
                return;
            }
            switch(authInfo.getUsuario().getRol()){

                case ADMIN:
                    MenuAdministrador menuAdministrador = new MenuAdministrador();
                    new StageDecoratorX(menuAdministrador);
                    new MenuAdministradorController((Administrador) authInfo.getUsuario(), menuAdministrador);
                    menuAdministrador.show();
                    break;

                case VENDEDOR:
                    MenuVendedor menuVendedor = new MenuVendedor();
                    new StageDecoratorX(menuVendedor);
                    new MenuVendedorController((Vendedor) authInfo.getUsuario(), menuVendedor);
                    menuVendedor.show();
                    break;

                case COMPRADOR:
                    MenuComprador menuComprador = new MenuComprador();
                    new StageDecoratorX(menuComprador);
                    new MenuCompradorController((Comprador) authInfo.getUsuario(), menuComprador);
                    menuComprador.show();
                    break;


            }
        }
    }

    class SignUpAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent actionEvent) {
            System.out.println("registrarse....");
            RegistroForm form = new RegistroForm();
            new StageDecoratorX(form);
            form.show();
        }
    }

}
