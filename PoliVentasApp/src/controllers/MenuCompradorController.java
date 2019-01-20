package controllers;

import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Articulo;
import models.Pedido;
import models.entities.Comprador;
import services.CompradorServiceDB;
import utils.StageDecoratorX;
import views.MenuComprador;
import views.items.PendienteItem;
import views.items.SearchItem;
import views.popup.MostrarDetalles;
import views.popup.RealizarCompra;


public class MenuCompradorController {
    LoginController login;
    private Comprador comprador;
    private MenuComprador menuComprador;

    private CompradorServiceDB db;

    public MenuCompradorController(Comprador comprador, MenuComprador menuComprador) {
        this.comprador = comprador;
        this.menuComprador = menuComprador;
        db = new CompradorServiceDB(comprador);
        
        this.menuComprador.setOnLogout(new LogOutAction());
        this.menuComprador.setOnInputChanged(new OnSearchInputChanged());
        
        actualizarPedidos();
        
    }
    
    
    void actualizarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(comprador);
        
        int i = 0;
        for(Pedido p: pedidos){
            System.out.println(i++ );
            System.out.println(" pedido: "+p+"\n");
            PendienteItem item = new PendienteItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(item));
            menuComprador.addPedidoItem(item);
        }
    }
    
    
    
    class LogOutAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
        
                    Optional<ButtonType> result = logoutAlert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        menuComprador.close();
                        login.getView().show();
                    }
        }
    }

    
    class OnSearchItemSelected implements EventHandler<MouseEvent> {
        SearchItem item;

        public OnSearchItemSelected(SearchItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            RealizarCompra rc = new RealizarCompra(item);
            new StageDecoratorX(rc);
            rc.show();
        }
    }

    class OnPedidoSelected implements  EventHandler<MouseEvent>{

        PendienteItem item;

        public OnPedidoSelected(PendienteItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            MostrarDetalles md = new MostrarDetalles(item);
            menuComprador.cleanPedidosPendientes();
            actualizarPedidos();
            
            new StageDecoratorX(md);
            md.show();

        }
    }
    
    class OnSearchInputChanged implements ChangeListener<String>{

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
           if(menuComprador.getTextInput().length() < 3)
               return;
           menuComprador.cleanSearchResultItem();
           List<Articulo> articulos = db.buscarArticulo(menuComprador.getTextInput());
           for(Articulo articulo: articulos){
               SearchItem item = new SearchItem(articulo);
               item.setOnMouseClicked(new OnSearchItemSelected(item));
               menuComprador.addSearchResultItem(item);
           }
        }
        
    }
    
    
    public void setLoginController(LoginController login) {
        this.login = login;
    }
    
    
}
