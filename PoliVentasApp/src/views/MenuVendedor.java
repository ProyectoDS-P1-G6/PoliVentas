/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import views.containers.SearchSection;
import views.items.*;

/**
 *
 * @author carlasanchez
 */
public class MenuVendedor extends Stage {

    private final TabPane root;
    private final Tab tabVentas;
    private final Tab tabCompras;
    private final AnchorPane comprasPane;
    private final AnchorPane ventasPane;

    JFXButton carritoButton;
    Label logout;

    SearchSection searchSection;
    HBox pedidosList;
    VBox masBuscadosList;
    VBox ventasList;
    VBox productosList;
    VBox historialList;

    public MenuVendedor() {
        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(20);
        logout.getStyleClass().add("logout-label");
        
//Ventana comprador
        pedidosList = new HBox(10);
        pedidosList.getStyleClass().add("comprasPendientesList");
        ScrollPane comprasPendientes = new ScrollPane();

        comprasPendientes.setPrefWidth(780);
        comprasPendientes.setPrefHeight(150);
        comprasPendientes.setLayoutX(10);
        comprasPendientes.setLayoutY(40);
        comprasPendientes.setContent(pedidosList);

        searchSection = new SearchSection();
        searchSection.setLayoutX(10);
        searchSection.setLayoutY(210);
        searchSection.setPrefWidth(235);
        searchSection.setPrefHeight(400);
        
        ScrollPane masBuscados = new ScrollPane();
        masBuscadosList = new VBox(10);
        masBuscadosList.getStyleClass().add("masBuscadosList");
        masBuscados.setPrefWidth(240);
        masBuscados.setPrefHeight(350);
        masBuscados.setLayoutX(270);
        masBuscados.setLayoutY(210);
        
        masBuscados.setContent(masBuscadosList);
        
//Ventana Vendedor
        ventasList = new VBox(10);
        ScrollPane ventasPendientes = new ScrollPane();

        ventasPendientes.setPrefWidth(250);
        ventasPendientes.setPrefHeight(505);
        ventasPendientes.setLayoutX(530);
        ventasPendientes.setLayoutY(40);
        ventasPendientes.setContent(ventasList);
        
        ScrollPane HistorialVentas = new ScrollPane();
        historialList = new VBox(10);
        historialList.getStyleClass().add("historialList");
        HistorialVentas.setPrefWidth(250);
        HistorialVentas.setPrefHeight(505);
        HistorialVentas.setLayoutX(270);
        HistorialVentas.setLayoutY(40);
        HistorialVentas.setContent(historialList);
        
        productosList = new VBox(10);
        productosList.getStyleClass().add("productosList");
        ScrollPane misProductos = new ScrollPane();
        misProductos.setPrefWidth(250);
        misProductos.setPrefHeight(505);
        misProductos.setLayoutX(10);
        misProductos.setLayoutY(40);
        misProductos.setContent(productosList);

        root = new TabPane();
        tabVentas = new Tab("Mis Ventas");
        tabVentas.getStyleClass().add("tab-pane");
        tabCompras = new Tab("Mis Compras");
        root.getTabs().addAll(tabVentas,tabCompras);       
	root.getSelectionModel().selectFirst();
        
        comprasPane = new AnchorPane();
        ventasPane = new AnchorPane();
        
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        
        tabVentas.setClosable(false);
        tabCompras.setClosable(false);
        tabVentas.setContent(ventasPane);
        tabCompras.setContent(comprasPane);

        Label comprasPendientesLabel = new Label("Compras pendientes.");
        comprasPendientesLabel.getStyleClass().add("title");
        comprasPendientesLabel.setLayoutX(comprasPendientes.getLayoutX());
        comprasPendientesLabel.setLayoutY(comprasPendientes.getLayoutY()-15);
        Label masbuscadosLabel = new Label("Articulos mas buscados.");
        masbuscadosLabel.getStyleClass().add("title");
        masbuscadosLabel.setLayoutX(masBuscados.getLayoutX());
        masbuscadosLabel.setLayoutY(masBuscados.getLayoutY()-15);
        
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
        
        comprasPane.getChildren().addAll(carritoButton,logout,comprasPendientesLabel ,comprasPendientes, searchSection,masbuscadosLabel , masBuscados);
        ventasPane.getChildren().addAll(carritoButton, logout,misProductosLabel, misProductos, ventasPendientesLabel, ventasPendientes,historialVentasLabel,HistorialVentas);

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
    
    public void addVentaPendienteItem(VendidoItem item) {
        this.ventasList.getChildren().add(item);
    }
    
    public void addProductoItem(ProductoItem item) {
        this.productosList.getChildren().add(item);
    }
    
    public void cleanVentasPendientes(){
        this.ventasList.getChildren().clear();
    }

}
