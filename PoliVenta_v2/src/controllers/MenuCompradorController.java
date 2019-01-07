package controllers;

import models.entities.Comprador;
import views.MenuComprador;

public class MenuCompradorController {
    private Comprador comprador;
    private MenuComprador menuComprador;

    public MenuCompradorController(Comprador comprador, MenuComprador menuComprador) {
        this.comprador = comprador;
        this.menuComprador = menuComprador;
    }
}
