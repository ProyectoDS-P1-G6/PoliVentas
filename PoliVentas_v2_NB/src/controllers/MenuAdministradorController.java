package controllers;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Vendedor;
import services.AdministradorServiceDB;
import utils.StageDecoratorX;
import views.MenuAdministrador;
import views.MenuComprador;
import views.MenuVendedor;

public class MenuAdministradorController{
    private Administrador administrador;
    private MenuAdministrador view;

    private AdministradorServiceDB db;

    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.view = view;
        
        db = new AdministradorServiceDB();
        
        view.buscarAction((EventHandler<ActionEvent>) new buscarOperation() );
        view.productosAction((EventHandler<ActionEvent>) new admProductOperation());
        view.usersAction((EventHandler<ActionEvent>) new admUserOperation());
        view.compraAction((EventHandler<ActionEvent>) new admCompraOperation());
                
    }
    
    class buscarOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR BUSQUEDA");
             view.changeViewAdmin("BUSCAR");
            }
        }   
    class admUserOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRACION DE USUARIOS");
             view.changeViewAdmin("USERS");
            }
        }   
    class admProductOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR PRODUCTOS");
             view.changeViewAdmin("PRODUCTO");
            }
        }   
    class admCompraOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR COMPRAS");
             view.changeViewAdmin("COMPRA");
            }
        }   

}
