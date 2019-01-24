package controllers;


import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.Pedido;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Usuario;
import services.AdministradorServiceDB;
import services.CompradorServiceDB;
import views.MenuAdministrador;
import views.items.PendienteItem;

public final class MenuAdministradorController{
    LoginController login;
    private Administrador administrador;
    private MenuAdministrador menuAdministrador;

    private AdministradorServiceDB db;
    private CompradorServiceDB db2;

    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.menuAdministrador = view;
        db = new AdministradorServiceDB(administrador);
        
        view.buscarAction((EventHandler<ActionEvent>) new buscarOperation() );
        view.productosAction((EventHandler<ActionEvent>) new admProductOperation());
        view.usersAction((EventHandler<ActionEvent>) new admUserOperation());
        view.compraAction((EventHandler<ActionEvent>) new admCompraOperation());
            
        cargarPedidos();
    }
    void cargarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(administrador);
        
        for(Pedido p: pedidos){
            System.out.println(p);
            PendienteItem item = new PendienteItem(p);
            switch(p.getEstado()){
                case PENDIENTE:
                    menuAdministrador.chargerPedidosPendientes(item);
                    break;
                case ENTREGADO:
                    menuAdministrador.chargerPedidosExitosos(item);
                    break;
                case ANULADO:
                    menuAdministrador.chargerPedidosAnulados(item);
            }
            //item.setOnMouseClicked(new MenuAdministradorController.OnPedidoSelected(item));
        }
    }
    class buscarOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR BUSQUEDA");
             menuAdministrador.changeViewAdmin("BUSCAR");
            }
        }   
    class admUserOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRACION DE USUARIOS");
             menuAdministrador.changeViewAdmin("USERS");
            }
        }   
    class admProductOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR PRODUCTOS");
             menuAdministrador.changeViewAdmin("PRODUCTO");
            }
        }   
    class admCompraOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR COMPRAS");
             menuAdministrador.changeViewAdmin("COMPRA");
            }
        } 
    
    public void setLoginController(LoginController login) {
        this.login = login;
    }
    
}