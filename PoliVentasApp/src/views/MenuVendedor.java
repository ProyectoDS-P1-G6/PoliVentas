package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import models.Estado;
import models.Pedido;
import models.entities.Comprador;
import models.entities.Vendedor;
import org.joda.money.Money;
import utils.Constants;
import views.items.CompradoItem;
import views.items.SearchItem;
import views.items.VendidoItem;


public class MenuVendedor extends Stage {

    AnchorPane root;

    JFXButton carritoButton;
    Label logout;
    
    JFXTextField searchBox;
    JFXButton searchButton;
    VBox searchResultList;


    VBox comprasPendientesList;
    VBox masBuscadosList;
    HBox misProductosList;
    HBox ventasPendientesList;
    VBox nuevosArticulosList;

    EventHandler<MouseEvent> onSearchItemSelected;
    EventHandler<MouseEvent> onComprasPendientesSelected;
    EventHandler<MouseEvent> onMasBuscadosItemsSelected;
    EventHandler<MouseEvent> onVentasPendientesSelected;
    EventHandler<MouseEvent> onMisProductosSelected;
    EventHandler<MouseEvent> onNuevosArticulosSelected;
    
public MenuVendedor(){    
        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(20);
        logout.getStyleClass().add("logout-label");

        comprasPendientesList = new VBox(10);
        comprasPendientesList.getStyleClass().add("comprasPendientesList");
        ScrollPane comprasPendientes = new ScrollPane();
        comprasPendientes.setPrefWidth(290);
        comprasPendientes.setPrefHeight(400);
        comprasPendientes.setLayoutX(695);
        comprasPendientes.setLayoutY(210);
        comprasPendientes.setContent(comprasPendientesList);
        
        misProductosList = new HBox(5);
        misProductosList.getStyleClass().add("misProductosList");
        ScrollPane misProductos = new ScrollPane();
        misProductos.setPrefWidth(605);
        misProductos.setPrefHeight(150);
        misProductos.setLayoutX(685);
        misProductos.setLayoutY(40);
        misProductos.setContent(misProductosList);

        Pane searchSection = new Pane();
        searchSection.setLayoutX(10);
        searchSection.setLayoutY(210);
        searchSection.setPrefWidth(350);
        searchSection.setPrefHeight(400);
        searchBox    = new JFXTextField();
        searchBox.setPromptText("  Buscar Producto");
        searchBox.setLayoutX(10);
        searchBox.setLayoutY(10);
        searchBox.setPrefWidth(200);
        searchBox.setPrefHeight(30);
        searchResultList = new VBox(10);
        searchResultList.getStyleClass().add("searchResultList");
        
        ScrollPane searchResult = new ScrollPane();
        searchResult.setLayoutX(10);
        searchResult.setLayoutY(50);
        searchResult.setPrefWidth(350);
        searchResult.setPrefHeight(330);

        searchResult.setContent(searchResultList);
        searchButton = new JFXButton();
        searchButton.setLayoutX(200);
        searchButton.setLayoutY(13);
        searchButton.getStyleClass().add("search_button");
        searchSection.getChildren().addAll(searchBox, searchButton, searchResult);

        ScrollPane masBuscados = new ScrollPane();
        masBuscadosList = new VBox(10);
        masBuscadosList.getStyleClass().add("masBuscadosList");
        masBuscados.setPrefWidth(270);
        masBuscados.setPrefHeight(400);
        masBuscados.setLayoutX(410);
        masBuscados.setLayoutY(210);

        masBuscados.setContent(masBuscadosList);
        
        ScrollPane ventasPendientes = new ScrollPane();
        ventasPendientesList = new HBox(10);
        ventasPendientesList.getStyleClass().add("ventasPendientesList");
        ventasPendientes.setPrefWidth(620);
        ventasPendientes.setPrefHeight(150);
        ventasPendientes.setLayoutX(10);
        ventasPendientes.setLayoutY(40);

        ventasPendientes.setContent(ventasPendientesList);
        
        ScrollPane nuevosArticulos = new ScrollPane();
        nuevosArticulosList = new VBox(10);
        nuevosArticulosList.getStyleClass().add("nuevosArticulosList");
        nuevosArticulos.setPrefWidth(290);
        nuevosArticulos.setPrefHeight(400);
        nuevosArticulos.setLayoutX(990);
        nuevosArticulos.setLayoutY(210);

        nuevosArticulos.setContent(nuevosArticulosList);
        
        
        root = new AnchorPane();
        ScrollPane rootsc= new ScrollPane();
        root.setPrefWidth(900);
        root.setPrefHeight(600);

        Label comprasPendientesLabel = new Label("Mis Compras.");
        comprasPendientesLabel.getStyleClass().add("title");
        comprasPendientesLabel.setLayoutX(comprasPendientes.getLayoutX());
        comprasPendientesLabel.setLayoutY(comprasPendientes.getLayoutY()-15);
        Label masbuscadosLabel = new Label("Articulos más buscados.");
        masbuscadosLabel.getStyleClass().add("title");
        masbuscadosLabel.setLayoutX(masBuscados.getLayoutX());
        masbuscadosLabel.setLayoutY(masBuscados.getLayoutY()-15);
        Label misProductosLabel = new Label("Mis Productos.");
        misProductosLabel.getStyleClass().add("title");
        misProductosLabel.setLayoutX(misProductos.getLayoutX());
        misProductosLabel.setLayoutY(misProductos.getLayoutY()-15);
        Label ventasPendientesLabel = new Label("Mis Ventas.");
        ventasPendientesLabel.getStyleClass().add("title");
        ventasPendientesLabel.setLayoutX(ventasPendientes.getLayoutX());
        ventasPendientesLabel.setLayoutY(ventasPendientes.getLayoutY()-15);
        Label nuevosArticulosLabel = new Label("Nuevos Articulos");
        nuevosArticulosLabel.getStyleClass().add("title");
        nuevosArticulosLabel.setLayoutX(nuevosArticulos.getLayoutX());
        nuevosArticulosLabel.setLayoutY(nuevosArticulos.getLayoutY()-15);
        Label busquedaLabel = new Label("Búsqueda");
        busquedaLabel.getStyleClass().add("title");
        busquedaLabel.setLayoutX(searchSection.getLayoutX());
        busquedaLabel.setLayoutY(searchSection.getLayoutY()-15);
        root.getChildren().addAll(carritoButton,logout, busquedaLabel,searchSection,comprasPendientesLabel ,comprasPendientes,masbuscadosLabel , masBuscados,misProductosLabel,misProductos,ventasPendientesLabel,ventasPendientes,nuevosArticulosLabel,nuevosArticulos);
        rootsc.setContent(root);
        setScene(new Scene(rootsc));
        getScene().getStylesheets().add("assets/menu_vendedor.css");

        addItemsComprador();
        addItemsVendedor();
    }


    void addItemsComprador() {
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
            masBuscadosList.getChildren().add(new SearchItem(articulo));
            comprasPendientesList.getChildren().add(new CompradoItem(p));
            searchResultList.getChildren().add(new SearchItem(articulo));
            
          
        }
    }
    
    void addItemsVendedor() {
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
                    p.setEstado(Estado.ENTREGADO);
                    j++;
                    break;
                case 1:
                    p.setEstado(Estado.PENDIENTE);
                    j++;
                    break;
                default:
                    p.setEstado(Estado.ANULADO);
                    j = 0;
                    break;
            }
            misProductosList.getChildren().add(new SearchItem(articulo));
            ventasPendientesList.getChildren().add(new VendidoItem(p));
            nuevosArticulosList.getChildren().add(new SearchItem(articulo));
        }
    }

    public void addSearchResultItem(SearchItem item) {
        this.searchResultList.getChildren().add(item);
    }

    public void addComprasPendientesItem(CompradoItem item) {
        this.comprasPendientesList.getChildren().add(item);
    }
    public void addNuevosArticulosItem(SearchItem item) {
        this.nuevosArticulosList.getChildren().add(item);
    }
    
    public void addVentasPendientesItem(CompradoItem item) {
        this.ventasPendientesList.getChildren().add(item);
    }

    public void addMasBuscadosItem(SearchItem item) {
        this.masBuscadosList.getChildren().add(item);
    }
    
    public void addMisProductosItem(SearchItem item) {
        this.misProductosList.getChildren().add(item);
    }

    public void removeSearchResultItem(SearchItem item) {
        this.searchResultList.getChildren().remove(item);
    }

    public void removeComprasPendientesItem(CompradoItem item) {
        this.comprasPendientesList.getChildren().remove(item);
    }

    public void removeMasBuscadosItem(SearchItem item) {
        this.masBuscadosList.getChildren().remove(item);
    }
    
    public void removeNuevosArticulosItem(SearchItem item) {
        this.nuevosArticulosList.getChildren().remove(item);
    }
    
    public void removeVentasPendientesItem(VendidoItem item) {
        this.ventasPendientesList.getChildren().remove(item);
    }
    
    public void removeMisProductosItem(SearchItem item) {
        this.misProductosList.getChildren().remove(item);
    }
}



