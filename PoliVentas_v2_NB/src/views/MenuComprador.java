package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
import views.items.Item;
import views.items.SearchItem;

public class MenuComprador extends Stage {

    AnchorPane root;

    JFXButton carritoButton;
    Label logout;


    /**
     * Sección de busqueda.
     */
    JFXTextField searchBox;
    JFXButton searchButton;
    VBox searchResultList;


    HBox comprasPendientesList;
    VBox masBuscadosList;


    EventHandler<MouseEvent> onSearchItemSelected;
    EventHandler<MouseEvent> onComprasPendientesSelected;
    EventHandler<MouseEvent> onMasBuscadosItemsSelected;



    public MenuComprador() {

        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(640);
        logout.getStyleClass().add("logout-label");

        comprasPendientesList = new HBox(10);
        comprasPendientesList.getStyleClass().add("comprasPendientesList");
        ScrollPane comprasPendientes = new ScrollPane();
        //comprasPendientesList.setOrientation(Orientation.HORIZONTAL);
        comprasPendientes.setPrefWidth(620);
        comprasPendientes.setPrefHeight(150);
        comprasPendientes.setLayoutX(10);
        comprasPendientes.setLayoutY(40);
        comprasPendientes.setContent(comprasPendientesList);

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
        //searchResultList.setOrientation(Orientation.VERTICAL);
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
        //masBuscadosList.setOrientation(Orientation.VERTICAL);
        masBuscados.setPrefWidth(270);
        masBuscados.setPrefHeight(400);
        masBuscados.setLayoutX(410);
        masBuscados.setLayoutY(210);

        masBuscados.setContent(masBuscadosList);


        root = new AnchorPane();
        root.setPrefWidth(700);
        root.setPrefHeight(600);

        Label comprasPendientesLabel = new Label("Compras pendientes.");
        comprasPendientesLabel.getStyleClass().add("title");
        comprasPendientesLabel.setLayoutX(comprasPendientes.getLayoutX());
        comprasPendientesLabel.setLayoutY(comprasPendientes.getLayoutY()-15);
        Label masbuscadosLabel = new Label("Articulos mas buscados.");
        masbuscadosLabel.getStyleClass().add("title");
        masbuscadosLabel.setLayoutX(masBuscados.getLayoutX());
        masbuscadosLabel.setLayoutY(masBuscados.getLayoutY()-15);
        root.getChildren().addAll(carritoButton,logout,comprasPendientesLabel ,comprasPendientes, searchSection,masbuscadosLabel , masBuscados);
        setScene(new Scene(root));
        getScene().getStylesheets().add("assets/menu_comprador.css");

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
            masBuscadosList.getChildren().add(new SearchItem(articulo));
            comprasPendientesList.getChildren().add(new CompradoItem(p));
            searchResultList.getChildren().add(new SearchItem(articulo));
        }
    }

    public void addSearchResultItem(SearchItem item) {
        this.searchResultList.getChildren().add(item);
    }

    public void addComprasPendientesItem(CompradoItem item) {
        this.comprasPendientesList.getChildren().add(item);
    }

    public void addMasBuscadosItem(SearchItem item) {
        this.masBuscadosList.getChildren().add(item);
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


}
