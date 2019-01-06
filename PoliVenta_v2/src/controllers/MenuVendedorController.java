package controllers;

import models.entities.Vendedor;
import views.MenuVendedor;

public class MenuVendedorController {
    private Vendedor vendedor;
    private MenuVendedor menuVendedor;

    public MenuVendedorController(Vendedor vendedor, MenuVendedor menuVendedor) {
        this.vendedor = vendedor;
        this.menuVendedor = menuVendedor;
    }


}
