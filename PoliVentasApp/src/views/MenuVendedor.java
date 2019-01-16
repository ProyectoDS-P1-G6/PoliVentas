package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    private final TabPane root;
    private final Tab tabVentas;
    private final Tab tabCompras;
    private final AnchorPane comprasPane;
    private final AnchorPane ventasPane;
    

    JFXButton carritoButton;
    Label logout;

    JFXTextField searchBox;
    JFXButton searchButton;
    VBox searchResultList;

    HBox comprasPendientesList;
    VBox masBuscadosList;
    VBox misProductosList;
    VBox ventasPendientesList;
    VBox misVentasList;
    VBox nuevosArticulosList;

    EventHandler<MouseEvent> onSearchItemSelected;
    EventHandler<MouseEvent> onComprasPendientesSelected;
    EventHandler<MouseEvent> onMasBuscadosItemsSelected;
    EventHandler<MouseEvent> onVentasPendientesSelected;
    EventHandler<MouseEvent> onMisProductosSelected;
    EventHandler<MouseEvent> onNuevosArticulosSelected;

    public MenuVendedor() {
        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(20);
        logout.getStyleClass().add("logout-label");

        comprasPendientesList = new HBox(10);
        comprasPendientesList.getStyleClass().add("comprasPendientesList");
        ScrollPane comprasPendientes = new ScrollPane();
        comprasPendientes.setPrefWidth(780);
        comprasPendientes.setPrefHeight(150);
        comprasPendientes.setLayoutX(10);
        comprasPendientes.setLayoutY(40);
        comprasPendientes.setContent(comprasPendientesList);

        misProductosList = new VBox(10);
        misProductosList.getStyleClass().add("misProductosList");
        ScrollPane misProductos = new ScrollPane();
        misProductos.setPrefWidth(250);
        misProductos.setPrefHeight(505);
        misProductos.setLayoutX(10);
        misProductos.setLayoutY(40);
        misProductos.setContent(misProductosList);

        
        ScrollPane ventasPendientes = new ScrollPane();
        ventasPendientesList = new VBox(10);
        ventasPendientesList.getStyleClass().add("ventasPendientesList");
        ventasPendientes.setPrefWidth(250);
        ventasPendientes.setPrefHeight(505);
        ventasPendientes.setLayoutX(530);
        ventasPendientes.setLayoutY(40);

        ventasPendientes.setContent(ventasPendientesList);
        
        ScrollPane HistorialVentas = new ScrollPane();
        misVentasList = new VBox(10);
        misVentasList.getStyleClass().add("misVentasList");
        HistorialVentas.setPrefWidth(250);
        HistorialVentas.setPrefHeight(505);
        HistorialVentas.setLayoutX(270);
        HistorialVentas.setLayoutY(40);

        HistorialVentas.setContent(misVentasList);
        
        Pane searchSection = new Pane();
        searchSection.setLayoutX(10);
        searchSection.setLayoutY(210);
        searchSection.setPrefWidth(235);
        searchSection.setPrefHeight(400);
        searchBox = new JFXTextField();
        searchBox.setPromptText("Buscar Producto");
        searchBox.setLayoutX(10);
        searchBox.setLayoutY(10);
        searchBox.setPrefWidth(230);
        searchBox.setPrefHeight(35);
        searchResultList = new VBox(10);
        searchResultList.getStyleClass().add("searchResultList");

        ScrollPane searchResult = new ScrollPane();
        searchResult.setLayoutX(10);
        searchResult.setLayoutY(50);
        searchResult.setPrefWidth(250);
        searchResult.setPrefHeight(300);

        searchResult.setContent(searchResultList);
        searchButton = new JFXButton();
        searchButton.setLayoutX(200);
        searchButton.setLayoutY(13);
        searchButton.getStyleClass().add("search_button");
        searchSection.getChildren().addAll(searchBox, searchButton, searchResult);

        ScrollPane masBuscados = new ScrollPane();
        masBuscadosList = new VBox(10);
        masBuscadosList.getStyleClass().add("masBuscadosList");
        masBuscados.setPrefWidth(240);
        masBuscados.setPrefHeight(350);
        masBuscados.setLayoutX(270);
        masBuscados.setLayoutY(210);
        masBuscados.setContent(masBuscadosList);

        

        ScrollPane nuevosArticulos = new ScrollPane();
        nuevosArticulosList = new VBox(10);
        nuevosArticulosList.getStyleClass().add("nuevosArticulosList");
        nuevosArticulos.setPrefWidth(270);
        nuevosArticulos.setPrefHeight(350);
        nuevosArticulos.setLayoutX(530);
        nuevosArticulos.setLayoutY(210);

        nuevosArticulos.setContent(nuevosArticulosList);

        root = new TabPane();
        tabVentas = new Tab("Mis Ventas");
        tabVentas.getStyleClass().add("tab-pane");
        tabCompras = new Tab("Mis Compras");
        root.getTabs().addAll(tabVentas,tabCompras);       
        //root.setPadding(new Insets(15,15,15,15));
	root.getSelectionModel().selectFirst();
        
        comprasPane = new AnchorPane();
        ventasPane = new AnchorPane();
        //ScrollPane rootsc = new ScrollPane();
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        Label comprasPendientesLabel = new Label("Mis Compras.");
        comprasPendientesLabel.getStyleClass().add("title");
        comprasPendientesLabel.setLayoutX(comprasPendientes.getLayoutX());
        comprasPendientesLabel.setLayoutY(comprasPendientes.getLayoutY() - 15);
        Label masbuscadosLabel = new Label("Articulos más buscados.");
        masbuscadosLabel.getStyleClass().add("title");
        masbuscadosLabel.setLayoutX(masBuscados.getLayoutX());
        masbuscadosLabel.setLayoutY(masBuscados.getLayoutY() - 15);
        Label misProductosLabel = new Label("Mis Productos.");
        misProductosLabel.getStyleClass().add("title");
        misProductosLabel.setLayoutX(misProductos.getLayoutX());
        misProductosLabel.setLayoutY(misProductos.getLayoutY() - 15);
        Label ventasPendientesLabel = new Label("Mis Ventas Pendientes.");
        ventasPendientesLabel.getStyleClass().add("title");
        ventasPendientesLabel.setLayoutX(ventasPendientes.getLayoutX());
        ventasPendientesLabel.setLayoutY(ventasPendientes.getLayoutY() - 15);
        Label historialVentasLabel = new Label("Historial Ventas.");
        historialVentasLabel.getStyleClass().add("title");
        historialVentasLabel.setLayoutX(HistorialVentas.getLayoutX());
        historialVentasLabel.setLayoutY(HistorialVentas.getLayoutY() - 15);
        Label nuevosArticulosLabel = new Label("Nuevos Articulos");
        nuevosArticulosLabel.getStyleClass().add("title");
        nuevosArticulosLabel.setLayoutX(nuevosArticulos.getLayoutX());
        nuevosArticulosLabel.setLayoutY(nuevosArticulos.getLayoutY() - 15);
        Label busquedaLabel = new Label("Búsqueda");
        busquedaLabel.getStyleClass().add("title");
        busquedaLabel.setLayoutX(searchSection.getLayoutX()+15);
        busquedaLabel.setLayoutY(searchSection.getLayoutY() - 15);
        comprasPane.getChildren().addAll(carritoButton, logout, busquedaLabel, searchSection, comprasPendientesLabel, comprasPendientes, masbuscadosLabel, masBuscados,nuevosArticulosLabel, nuevosArticulos);
        ventasPane.getChildren().addAll(carritoButton, logout,misProductosLabel, misProductos, ventasPendientesLabel, ventasPendientes,historialVentasLabel,HistorialVentas);
        
        tabVentas.setClosable(false);
        tabCompras.setClosable(false);
        tabVentas.setContent(ventasPane);
        tabCompras.setContent(comprasPane);
        
        //root.getChildren().addAll(carritoButton, logout, busquedaLabel, searchSection, comprasPendientesLabel, comprasPendientes, masbuscadosLabel, masBuscados, misProductosLabel, misProductos, ventasPendientesLabel, ventasPendientes, nuevosArticulosLabel, nuevosArticulos);
        //rootsc.setContent(root);
        setScene(new Scene(root));
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
                    ventasPendientesList.getChildren().add(new VendidoItem(p));
                    j++;
                    break;
                default:
                    p.setEstado(Estado.ANULADO);
                    j = 0;
                    break;
            }
            misProductosList.getChildren().add(new SearchItem(articulo));          
            nuevosArticulosList.getChildren().add(new SearchItem(articulo));
            misVentasList.getChildren().add(new VendidoItem(p));
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
