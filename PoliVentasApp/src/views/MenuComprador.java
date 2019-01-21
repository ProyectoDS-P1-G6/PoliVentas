package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
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
import views.containers.SearchSection;
import views.items.PendienteItem;
import views.items.SearchItem;


public class MenuComprador extends Stage {

    AnchorPane root;

    JFXButton carritoButton;
    Label logout;


    /**
     * Sección de busqueda.
     */
     SearchSection searchSection;

    HBox pedidosList;
    VBox masBuscadosList;


    public MenuComprador() {

        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(640);
        logout.getStyleClass().add("logout_label");

        pedidosList = new HBox(10);
        pedidosList.getStyleClass().add("comprasPendientesList");
        ScrollPane comprasPendientes = new ScrollPane();
        //comprasPendientesList.setOrientation(Orientation.HORIZONTAL);
        comprasPendientes.setPrefWidth(620);
        comprasPendientes.setPrefHeight(150);
        comprasPendientes.setLayoutX(10);
        comprasPendientes.setLayoutY(40);
        comprasPendientes.setContent(pedidosList);

        searchSection = new SearchSection();
        searchSection.setLayoutX(10);
        searchSection.setLayoutY(210);
        searchSection.setPrefWidth(350);
        searchSection.setPrefHeight(400);


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

    }


    

    public void addPedidoItem(PendienteItem item) {
        this.pedidosList.getChildren().add(item);
    }

    public void addMasBuscadosItem(SearchItem item) {
        this.masBuscadosList.getChildren().add(item);
    }

    
    public void cleanPedidosPendientes(){
        this.pedidosList.getChildren().clear();
    }
    

    public void removeComprasPendientesItem(PendienteItem item) {
        this.pedidosList.getChildren().remove(item);
    }

    public void removeMasBuscadosItem(SearchItem item) {
        this.masBuscadosList.getChildren().remove(item);
    }

    public void setOnLogout(EventHandler<MouseEvent> eventHandler){
        logout.setOnMouseClicked(eventHandler);
    }
    
    public void setOnInputChanged(ChangeListener<String> listener){
        searchSection.setOnInputChanged(listener);
    }
    
    public String getTextInput(){
        return searchSection.getTextInput();
    }
    
    public void cleanSearchResultItem() {
        this.searchSection.cleanSearchResultItem();
    }
    
    public void addSearchResultItem(SearchItem item) {
        this.searchSection.addSearchResultItem(item);
    }

}
