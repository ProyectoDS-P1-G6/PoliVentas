/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.corba.se.impl.io.FVDCodeBaseImpl;
import javafx.scene.layout.Pane;
import models.CreationalUsers.AdministradorModel;
import models.CreationalUsers.CompradorModel;
import models.CreationalUsers.UsuarioModel;
import models.CreationalUsers.VendedorModel;
import static poliventas.PoliVentas.ventanaPrincipal;
import vistas.FAdministrador;
import vistas.FComprador;
import vistas.FLogin;
import vistas.FVendedor;

/**
 *
 * @author Usuario
 */
public class GestionAcceso implements IntefaceAcceso{
    private Pane panel;
    private FLogin fLogin;
    private FComprador fcomprador;
    private FVendedor fvendedor;
    private FAdministrador fadministrador;
    
    private UsuarioModel usuario;
    
    public GestionAcceso(){
        fLogin = new FLogin(this);
        fcomprador = new FComprador();
        ventanaPrincipal.setVentana(panel = new Pane());
        ventanaPrincipal.getVentana().getChildren().add(fLogin.getPanelLogin());
        
    }  
    @Override
    public void recibirUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recibirContraseña() {
        
    }

    @Override
    public void login() {
        if(usuario.comprobarUsuario(usuario)){
            if(usuario.getRol()=="Comprador"){
                ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                ventanaPrincipal.getVentana().getChildren().add(fcomprador.getPanelAdmin());
            } else if(usuario.getRol()=="Vendedor"){
                ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                ventanaPrincipal.getVentana().getChildren().add(fvendedor.getPanelAdmin());
            } else if(usuario.getRol()=="Administrador"){
                ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                ventanaPrincipal.getVentana().getChildren().add(fadministrador.getPanelAdmin());
            }
            
        }
    }
    
}
