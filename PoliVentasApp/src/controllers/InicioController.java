package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Articulo;
import models.entities.Usuario;
import services.CompradorServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.Inicio;
import views.LoginView;
import views.items.ArticuloItem;

public class InicioController implements Returnable{
    
    private Inicio inicioView;

    public InicioController(Inicio view){
        this.inicioView = view;

        inicioView.addLoginAction((ActionEvent event) -> {
            LoginView login = new LoginView();
            new StageDecoratorX(login);
            LoginController controller = new LoginController(new Usuario(), login);
            controller.setPreviusWindow(this);
            inicioView.hide();
            login.setOnCloseRequest(e->{
                inicioView.show();
            });
            login.show();
        });	


        inicioView.addregisterinAction((ActionEvent event) -> {
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
            inicioView.close();
        });
    }

    void populateList(){
        CompradorServiceDB db = new CompradorServiceDB();
        for(Articulo a: db.getArticulosMasBuscados()){
            inicioView.addMasBuscado(new ArticuloItem(a));
        }
        
        int i = 0;
        for(Articulo a: db.getArticulosMasBuscados()){
            if(i%2 == 0){
                inicioView.addNuevoArticulo(new ArticuloItem(a));
            }
            i++;
        }
    }
    @Override
    public void setPreviusWindow(Returnable previous) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showWindow() {
        this.inicioView.show();
    }
}
