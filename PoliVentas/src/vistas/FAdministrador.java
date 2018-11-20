/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Usuario
 */
public class FAdministrador {
    protected BorderPane panelAdmin;
    protected VBox panelLateralmenu; 
    protected VBox panelCentralMuestra;
    protected GridPane contenedor;
    protected Label lbuscar;
    protected TextField tfBuscar;
    protected Button btnAdmUsuarios, btnBuscar, btnCompras,btnAdmaProductos, btnCerrarSesion;
    protected CheckBox cbSensilla, cbAvanzada, cbPendienteCarga, cbAnulados, cbExitosas;

    public FAdministrador() {
        iniciarFRegistro();
    }

    public void iniciarComponentes() {
        this.lbuscar = new Label("Buscar: ");
                
        this.tfBuscar = new TextField();
        
        
        this.btnAdmUsuarios = new Button();
        this.btnAdmUsuarios.setText("Administrar Usuarios");
        this.btnAdmaProductos = new Button();
        this.btnAdmaProductos.setText("Administrar Productos");
        this.btnBuscar = new Button();
        this.btnBuscar.setText("Buscar");
        this.btnCerrarSesion = new Button();
        this.btnCerrarSesion.setText("Cerrar Sesión");
        this.btnCompras = new Button();
        this.btnCompras.setText("Compras");
        
        this.cbAnulados = new CheckBox();
        this.cbAnulados.setText("Anuladas: ");
        this.cbAvanzada = new CheckBox();
        this.cbAvanzada.setText("Avanzada: ");
        this.cbExitosas = new CheckBox();
        this.cbExitosas.setText("Exitosa: ");
        this.cbPendienteCarga = new CheckBox();
        this.cbPendienteCarga.setText("Pendiente de Carga: ");
        this.cbSensilla = new CheckBox();
        this.cbSensilla.setText("Sensilla: ");
        
    }

    public final void iniciarFRegistro() {
        iniciarComponentes();
        //Creamos los paneles de la vista Administrador
        this.panelLateralmenu = new VBox();
        this.panelCentralMuestra = new VBox();
        this.panelAdmin = new BorderPane();
        
        this.panelLateralmenu.getChildren().addAll(btnAdmUsuarios,btnAdmaProductos,btnBuscar,btnCerrarSesion,btnCompras);
        this.panelCentralMuestra.getChildren().addAll(lbuscar,tfBuscar,cbAnulados,cbAvanzada,cbExitosas,cbPendienteCarga,cbSensilla);
        
        this.panelAdmin.setCenter(panelCentralMuestra);
        this.panelAdmin.setLeft(panelLateralmenu);
        
       
        
                
    }

    public BorderPane getPanelAdmin() {
        return panelAdmin;
    }

    
    
}
