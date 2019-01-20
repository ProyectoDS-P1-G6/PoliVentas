package controllers;

import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Articulo;
import models.Estado;
import models.Pedido;
import models.entities.Comprador;
import models.entities.CompradorBuilder;
import models.entities.Vendedor;
import models.entities.VendedorBuilder;
import org.joda.money.Money;
import services.CompradorServiceDB;
import utils.Constants;
import views.MenuComprador;
import views.items.CompradoItem;
import views.items.SearchItem;

public class MenuCompradorController {
    LoginController login;
    private Comprador comprador;
    private MenuComprador menuComprador;

    private CompradorServiceDB db;

    public MenuCompradorController(Comprador comprador, MenuComprador menuComprador) {
        this.comprador = comprador;
        this.menuComprador = menuComprador;
        
        this.menuComprador.setOnLogout(new LogOutAction());
        db = new CompradorServiceDB();
        addItems();
    }



    void addItems() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            Articulo articulo = new Articulo();
            articulo.setNombre("Nave Espacial.");
            Vendedor x = new Vendedor(new VendedorBuilder());
            x.setNombres("Nombres");
            x.setApellidos("Apellidos");
            articulo.setVendedor(x);
            articulo.setPrecio(Money.of(Constants.USD, 3000000.5));

            Pedido p = new Pedido(new Comprador(new CompradorBuilder()), articulo, 5);
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
    
    class LogOutAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
        
                    Optional<ButtonType> result = logoutAlert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        menuComprador.close();
                        login.getView().show();
                    }
                    else {
                        
                    }
        }
    }

    public void setLoginController(LoginController login) {
        this.login = login;
    }
    
    
}
