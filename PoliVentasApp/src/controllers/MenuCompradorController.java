package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import models.Articulo;
import models.Estado;
import models.Pedido;
import models.entities.Comprador;
import models.entities.Vendedor;
import org.joda.money.Money;
import services.CompradorServiceDB;
import utils.Constants;
import views.MenuComprador;
import views.items.CompradoItem;
import views.items.SearchItem;

public class MenuCompradorController {
    private Comprador comprador;
    private MenuComprador menuComprador;

    private CompradorServiceDB db;

    public MenuCompradorController(Comprador comprador, MenuComprador menuComprador) {
        this.comprador = comprador;
        this.menuComprador = menuComprador;

        db = new CompradorServiceDB();
        addItems();
    }



    void addItems() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            Articulo articulo = new Articulo();
            articulo.setNombre("Nave Espacial.");
            Vendedor x = new Vendedor();
            x.setNombres("Nombres");
            x.setApellidos("Apellidos");
            articulo.setVendedor(x);
            articulo.setPrecio(Money.of(Constants.USD, 3000000.5));

            Pedido p = new Pedido(new Comprador(), articulo, 5);
            switch (j) {
                case 0:
                    p.setEstado(Estado.ENVIADO);
                    j++;
                    break;
                case 1:
                    p.setEstado(Estado.PENDIENTE);
                    j++;
                    break;
                case 2:
                    p.setEstado(Estado.ANULADO);
                    j++;
                    break;
                default:
                    p.setEstado(Estado.ENTREGADO);
                    j = 0;
                    break;
            }
            menuComprador.addMasBuscadosItem(new SearchItem(articulo));
            menuComprador.addComprasPendientesItem(new CompradoItem(p));
            menuComprador.addSearchResultItem(new SearchItem(articulo));
        }
    }
}
