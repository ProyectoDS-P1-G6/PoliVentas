package controllers;

import models.entities.Vendedor;
import services.VendedorServiceDB;
import views.MenuVendedor;

public class MenuVendedorController {
    private Vendedor vendedor;
    private MenuVendedor menuVendedor;

    private VendedorServiceDB db = new VendedorServiceDB();

    public MenuVendedorController(Vendedor vendedor, MenuVendedor menuVendedor) {
        this.vendedor = vendedor;
        this.menuVendedor = menuVendedor;

        db = new VendedorServiceDB();
    }


}
