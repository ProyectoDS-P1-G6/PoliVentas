/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.CreationalUsers.CompradorModel;

/**
 *
 * @author Usuario
 */
public class FVendedor extends FComprador{
     protected BorderPane panelAdmin;
    protected VBox panelLateralmenu; 
    protected VBox panelCentralGeneral;
    protected VBox panelAuxCPendientes, panelAuxResumenVentas, panelMisProductos;
    protected GridPane contenedor;
    protected Label lbuscar;
    protected TextField tfBuscar;
    protected Button btnVentasPendientes, btnResumenVentas, btnMisProductos,btnCerrarSesion;
    FComprador comprardor = new FComprador();

    public FVendedor() {
        iniciarFComprador();
    }

    public void iniciarComponentes() {
        this.lbuscar = new Label("Buscar: ");
                
        this.tfBuscar = new TextField();
        
        
        this.btnVentasPendientes = new Button();
        this.btnVentasPendientes.setText("Compras Pendientes");
        this.btnResumenVentas = new Button();
        this.btnResumenVentas.setText("Resumen de Ventas");
        this.btnMisProductos = new Button();
        this.btnMisProductos.setText("Mis Productos");
        this.btnCerrarSesion = new Button();
        this.btnCerrarSesion.setText("Cerrar Sesión");
        this.btnMisPedidos = new Button();
        this.btnMisPedidos.setText("Nuevos Articulos");
        this.btnNuevosArticulos = new Button();
        this.btnNuevosArticulos.setText("Historial");
        ;
        
    }

    public final void iniciarFVendedor() {
        iniciarComponentes();
        //Creamos los paneles de la vista Administrador
        this.panelLateralmenu = new VBox();
        this.panelCentralGeneral = new VBox();
        this.panelAdmin = new BorderPane();
        
        this.panelLateralmenu.getChildren().addAll(btnVentasPendientes,btnCerrarSesion,btnResumenVentas,btnMisProductos,btnNuevosArticulos);
        this.panelAdmin.setLeft(panelLateralmenu);
        
       
        
                
    }

    public BorderPane getPanelAdmin() {
        return panelAdmin;
    }
}
