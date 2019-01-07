/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.layout.Pane;
import models.UsuarioModel;
import static poliventas.PoliVentas.ventanaPrincipal;
import vistas.FAdministrador;
import vistas.FComprador;
import vistas.FLogin;
import vistas.FVendedor;

/**
 *
 * @author Usuario
 */
public class GestionAccesos implements InterfaceAccesos{
    private final Pane panel;
    private final FLogin fLogin;
    private final FComprador fcomprador;
    private FVendedor fvendedor;
    private FAdministrador fadministrador;
    private UsuarioModel usuario;
    
    public GestionAccesos(){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void login() {
        if(usuario.comprobarUsuario(usuario)){
            if(null != usuario.getRol())switch (usuario.getRol()) {
                case "Comprador":
                    ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                    ventanaPrincipal.getVentana().getChildren().add(fcomprador.getPanelAdmin());
                    break;
                case "Vendedor":
                    ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                    ventanaPrincipal.getVentana().getChildren().add(fvendedor.getPanelAdmin());
                    break;
                case "Administrador":
                    ventanaPrincipal.getVentana().getChildren().remove(fLogin.getPanelLogin());
                    ventanaPrincipal.getVentana().getChildren().add(fadministrador.getPanelAdmin());
                    break;
                default:
                    break;
            }
            
        }

    }
    
}
