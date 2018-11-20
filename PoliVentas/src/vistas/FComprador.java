/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Usuario
 */
public class FComprador {
     protected BorderPane panelAdmin;
    protected VBox panelLateralmenu; 
    protected VBox panelCentralGeneral;
    protected VBox panelAuxCPendientes, panelAuxHistorial, panelAuxFavoritos, panelAuxNewArticles;
    protected GridPane contenedor;
    protected Label lbuscar;
    protected TextField tfBuscar;
    protected Button btnMisPedidos, btnBuscar, btnFavoritos,btnComprasPendientes, btnHistorial, btnNuevosArticulos, btnCerrarSesion;

    public FComprador() {
        iniciarFComprador();
    }

    public void iniciarComponentes() {
        this.lbuscar = new Label("Buscar: ");
                
        this.tfBuscar = new TextField();
        
        
        this.btnComprasPendientes = new Button();
        this.btnComprasPendientes.setText("Compras Pendientes");
        this.btnFavoritos = new Button();
        this.btnFavoritos.setText("Mas Buscados");
        this.btnBuscar = new Button();
        this.btnBuscar.setText("Buscar");
        this.btnCerrarSesion = new Button();
        this.btnCerrarSesion.setText("Cerrar Sesión");
        this.btnMisPedidos = new Button();
        this.btnMisPedidos.setText("Nuevos Articulos");
        this.btnNuevosArticulos = new Button();
        this.btnNuevosArticulos.setText("Historial");
       
    }

    public final void iniciarFComprador() {
        iniciarComponentes();
        //Creamos los paneles de la vista Administrador
        this.panelLateralmenu = new VBox();
        this.panelCentralGeneral = new VBox();
        this.panelAdmin = new BorderPane();
        
        this.panelLateralmenu.getChildren().addAll(btnBuscar,btnCerrarSesion,btnMisPedidos,btnFavoritos,btnNuevosArticulos);
               
        this.panelAdmin.setLeft(panelLateralmenu);
        
       
        
                
    }

    public BorderPane getPanelAdmin() {
        return panelAdmin;
    }
}
