package controllers;

import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import models.*;
import models.entities.Vendedor;
import services.VendedorServiceDB;
import utils.StageDecoratorX;
import views.MenuVendedor;
import views.items.*;
import views.popup.*;

public class MenuVendedorController {
    LoginController login;
    private Vendedor vendedor;
    private MenuVendedor menuVendedor;

    private VendedorServiceDB db;

    public MenuVendedorController(Vendedor vendedor, MenuVendedor menuVendedor) {
        this.vendedor = vendedor;
        this.menuVendedor = menuVendedor;

        db = new VendedorServiceDB(vendedor);
        
        this.menuVendedor.setOnLogout(new LogOutAction());
        this.menuVendedor.setOnInputChanged(new OnSearchInputChanged());
        
        actualizarVentas();
        addMisProductos();
        

    }

    void actualizarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(vendedor);
        
        for(Pedido p: pedidos){
            PendienteItem item = new PendienteItem(p);
            item.setOnMouseClicked(new MenuVendedorController.OnPedidoSelected(item));
            menuVendedor.addPedidoItem(item);
        }
    }
    
    void addArticulosMasBuscados(){
        List<Articulo> articulos = db.getArticulosMasBuscados();
        
        for(Articulo a: articulos){
            SearchItem item = new SearchItem(a);
            item.setOnMouseClicked(new OnSearchItemSelected(item));
            menuVendedor.addMasBuscadosItem(item);
        }
    }

    private void actualizarVentas() {
        List<Venta> ventas = db.getVentas(vendedor);

        for (Venta p : ventas) {
            VendidoItem item = new VendidoItem(p);
            item.setOnMouseClicked(new OnVentaSelected(item));
            menuVendedor.addVentaPendienteItem(item);
        }
        
    }

    private void addMisProductos() {
        List<Articulo> misArticulos = db.getMisArticulos(vendedor);
        
        for(Articulo a: misArticulos){
            ProductoItem item = new ProductoItem(a);
            item.setOnMouseClicked(new OnMiProductoSelected(item));
            menuVendedor.addProductoItem(item);
        }

    }
    
    
    
    class LogOutAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
        
                    Optional<ButtonType> result = logoutAlert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        menuVendedor.close();
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
    
    class OnMiProductoSelected implements EventHandler<MouseEvent> {
        ProductoItem item;

        public OnMiProductoSelected(ProductoItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            ProductosView pv = new ProductosView(item);
            new StageDecoratorX(pv);
            pv.show();         
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
            menuVendedor.cleanPedidosPendientes();
            actualizarPedidos();
            
            new StageDecoratorX(md);
            md.show();

        }
    }
    
    class OnVentaSelected implements  EventHandler<MouseEvent>{

        VendidoItem item;

        public OnVentaSelected(VendidoItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            VentaDetalles vd = new VentaDetalles(item);
            menuVendedor.cleanPedidosPendientes();
            actualizarVentas();
            
            new StageDecoratorX(vd);
            vd.show();

        }
    }
    
    class OnSearchInputChanged implements ChangeListener<String>{

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
           if(menuVendedor.getTextInput().length() < 3)
               return;
           menuVendedor.cleanSearchResultItem();
           List<Articulo> articulos = db.buscarArticulo(menuVendedor.getTextInput());
           for(Articulo articulo: articulos){
               SearchItem item = new SearchItem(articulo);
               item.setOnMouseClicked(new OnSearchItemSelected(item));
               menuVendedor.addSearchResultItem(item);
           }
        }
        
    }
    
    
    public void setLoginController(LoginController login) {
        this.login = login;
    }
    
}
