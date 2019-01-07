/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import controllers.IntefaceAcceso;
import javafx.scene.layout.Pane;
import models.CreationalUsers.UsuarioModel;
import static poliventas.PoliVentas.ventanaPrincipal;
import vistas.FAdministrador;
import vistas.FAdministrador;
import vistas.FComprador;
import vistas.FComprador;
import vistas.FLogin;
import vistas.FLogin;
import vistas.FVendedor;
import vistas.FVendedor;

/**
 *
 * @author Usuario
 */
public class GestionAccesos {
    private final Pane panel;
    private final FLogin fLogin;
    private final FComprador fcomprador;
    private FVendedor fvendedor;
    private FAdministrador fadministrador;
    private UsuarioModel usuario;
    
    public GestionAccesos(){
        fLogin = new FLogin((IntefaceAcceso) this);
        fcomprador = new FComprador();
        ventanaPrincipal.setVentana(panel = new Pane());
        ventanaPrincipal.getVentana().getChildren().add(fLogin.getPanelLogin());
        
    }  
    
}
