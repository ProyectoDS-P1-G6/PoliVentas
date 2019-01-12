package controllers;

import models.entities.Administrador;
import services.AdministradorServiceDB;
import views.MenuAdministrador;

public class MenuAdministradorController {
    private Administrador administrador;
    private MenuAdministrador view;

    private AdministradorServiceDB db;

    public MenuAdministradorController(Administrador administrador, MenuAdministrador view) {
        this.administrador = administrador;
        this.view = view;

        db = new AdministradorServiceDB();
    }


}
