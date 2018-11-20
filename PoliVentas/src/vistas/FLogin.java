/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Usuario
 */
public class FLogin {
    protected BorderPane panelLogin;
    protected Pane ventana;
    protected GridPane contenedor;
    protected Label lusuario, lcontrasena, llogin;
    protected TextField tfusuario;
    protected PasswordField pfcontrasena;
    protected Button btniniciar, btnregistro;
    

    public FLogin() {
        iniciarFLogin();
    }

    public void iniciarComponentes() {
        this.lusuario = new Label("Usuario: ");
        this.lcontrasena = new Label("Contraseña: ");
        this.llogin = new Label("Login");
        
        
        this.pfcontrasena = new PasswordField();
        this.tfusuario = new TextField();
                
        this.btniniciar = new Button();
        this.btniniciar.setText("Iniciar");
        this.btnregistro = new Button();
        this.btnregistro.setText("Registrarse");
        
    }

    public final void iniciarFLogin() {
        iniciarComponentes();
        this.panelLogin = new BorderPane();
        
        this.contenedor = new GridPane();
        GridPane contendedorBoton = new GridPane();
        
        contenedor.setPadding(new Insets(15)); //Margen
        contenedor.setVgap(10);
        contenedor.setHgap(10);
        contenedor.setAlignment(Pos.CENTER);
        
        contenedor.add(llogin, 2,0,7,1);
               
        contenedor.add(lusuario, 0,4);
        contenedor.add(tfusuario, 3,4,5,1);
        
        contenedor.add(lcontrasena, 0,5);
        contenedor.add(pfcontrasena, 3,5,5,1);
        
               
        contenedor.add(btniniciar,1,14,2,1);
        contenedor.add(btnregistro, 3,14,2,1);
        
        this.panelLogin.setCenter(contenedor);
        this.panelLogin.setBottom(contendedorBoton);
        
                
    }

    public BorderPane getPanelLogin() {
        return panelLogin;
    }
    
    
}
