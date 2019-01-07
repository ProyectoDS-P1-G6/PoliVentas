/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class FRegistro{
    protected BorderPane panelRegistro;
    protected Pane ventana;
    protected GridPane contenedor;
    protected Label lnombres, lapellidos, lemail, ldireccion, lcedula, lmatricula, ltelefono, lwhatsapp, lperfil;
    protected TextField tfnombres, tfapellidos, tfemail, tfdireccion, tfcedula, tfmatricula, tftelefono, tfwhatsapp;
    protected ComboBox cbperfil;
    protected Button btnregistrar, btncancelar;
    

    public FRegistro() {
        iniciarFRegistro();
    }

    public void iniciarComponentes() {
        this.lnombres = new Label("Nombres: ");
        this.lapellidos = new Label("Apellidos: ");
        this.lemail = new Label("E-mail: ");
        this.ldireccion = new Label("Addres: ");
        this.lcedula = new Label("Cédula: ");
        this.lmatricula = new Label("Matrícula: ");
        this.ltelefono = new Label("Telf: ");
        this.lwhatsapp = new Label("Whatsapp: ");
        this.lperfil = new Label("Perfil: ");
        
        this.tfnombres = new TextField();
        this.tfapellidos = new TextField();
        this.tfemail = new TextField();
        this.tfdireccion = new TextField();
        this.tfcedula = new TextField();
        this.tfmatricula = new TextField();
        this.tftelefono = new TextField();
        this.tfwhatsapp = new TextField();
        this.cbperfil = new ComboBox();
        
        this.btnregistrar = new Button();
        this.btnregistrar.setText("Registrar");
        this.btncancelar = new Button();
        this.btncancelar.setText("Cancelar");
        
    }

    public final void iniciarFRegistro() {
        iniciarComponentes();
        Label nameRegistro = new Label("Registro");
        this.ventana = new Pane();
        this.panelRegistro = new BorderPane();
        
        this.contenedor = new GridPane();
        GridPane contendedorBoton = new GridPane();
        
        contenedor.setPadding(new Insets(15)); //Margen
        contenedor.setVgap(10);
        contenedor.setHgap(20);
        contenedor.setAlignment(Pos.CENTER);
        
        contenedor.add(lnombres, 0,0,1,1);
        contenedor.add(tfnombres, 3,0,9,1);
        
        contenedor.add(lapellidos, 0,1);
        contenedor.add(tfapellidos, 3,1,9,1);
        
        contenedor.add(lemail, 0,2);
        contenedor.add(tfemail, 3,2,9,1);
        
        contenedor.add(ldireccion, 0,3);
        contenedor.add(tfdireccion, 3,3,9,1);
        
        contenedor.add(lcedula, 0,4);
        contenedor.add(tfcedula, 3,4,5,1);
        
        contenedor.add(lmatricula, 0,5);
        contenedor.add(tfmatricula, 3,5,5,1);
        
        contenedor.add(ltelefono, 0,6);
        contenedor.add(tftelefono, 3,6,5,1);
        
        contenedor.add(lwhatsapp, 0,7);
        contenedor.add(tfwhatsapp, 3,7,5,1);
        
        contenedor.add(lperfil, 0,8);
        contenedor.add(cbperfil, 3,8,5,1);
        
        contenedor.add(btnregistrar,3,14);
        contenedor.add(btncancelar, 4, 14,2,1);
        
        this.panelRegistro.setCenter(contenedor);
        this.panelRegistro.setBottom(contendedorBoton);
        
                
    }

    public BorderPane getPanelRegistro() {
        return panelRegistro;
    }
    
    
    
}
