package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AuthInfo;
import models.entities.Administrador;
import models.entities.Usuario;
import services.DBConnection;
import services.LoginServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.*;

public class LoginController implements Returnable{

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

    @Override
    public void setPreviousWindow(Returnable previous) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showWindow() {
        this.view.show();
    }

    class LoginAction implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent){
            try {
                System.out.println("iniciar sesión....");
                System.out.printf("user: %s password: %s\n", view.getUsuarioInput(), view.getContrasenaInput() );
                authInfo = ls.authUser(view.getUsuarioInput(), view.getContrasenaInput());
                
                if(!authInfo.isLoggeoExitoso()){
                    view.setStatusMessage("** Datos incorrectos.");
                    return;
                }
                view.close();
                
                Stage stage = new Stage();
                FXMLLoader loader;
                switch(authInfo.getUsuario().getRol()){
                    
                    case ADMIN:
                        MenuAdministrador menuAdministrador = new MenuAdministrador();
                        new StageDecoratorX(menuAdministrador);
                        new MenuAdministradorController((Administrador) authInfo.getUsuario(), menuAdministrador);
                        menuAdministrador.show();
                        break;
                        
                    case VENDEDOR:

                        loader = new FXMLLoader(getClass().getResource("/views/MenuVendedor.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        loader.<MenuVendedorController>getController().setPreviousWindow(LoginController.this);
                        new StageDecoratorX(stage);
                        stage.show();
                        break;
                        
                    case COMPRADOR:
                        loader = new FXMLLoader(getClass().getResource("/views/MenuComprador.fxml"));
                        stage.setScene(new Scene(loader.load()));
                        loader.<MenuCompradorController>getController().setPreviousWindow(LoginController.this);
                        new StageDecoratorX(stage);
                        stage.show();
                        break;
                        
                        
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class SignUpAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent actionEvent) {
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
        }
    }

    public LoginView getView() {
        return view;
    }
    
    

}
