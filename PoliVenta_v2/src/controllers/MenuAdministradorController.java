package controllers;

import models.entities.Administrador;
import views.MenuAdministrador;

public class MenuAdministradorController {
    private Administrador administrador;
    private MenuAdministrador menuAdministrador;

    public MenuAdministradorController(Administrador administrador, MenuAdministrador menuAdministrador) {
        this.administrador = administrador;
        this.menuAdministrador = menuAdministrador;
    }


}
