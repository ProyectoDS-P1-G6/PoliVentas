package controllers;

import models.entities.Comprador;
import services.CompradorServiceDB;
import views.MenuComprador;

public class MenuCompradorController {
    private Comprador comprador;
    private MenuComprador menuComprador;

    private CompradorServiceDB db;

    public MenuCompradorController(Comprador comprador, MenuComprador menuComprador) {
        this.comprador = comprador;
        this.menuComprador = menuComprador;

        db = new CompradorServiceDB();
    }
}
