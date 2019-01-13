package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import models.Estado;
import models.Pedido;
import models.entities.*;
import org.joda.money.Money;
import utils.Constants;
import views.items.CompradoItem;
import views.items.SearchItem;
import views.items.UserBottomView;
import views.items.UserItem;

public final class MenuAdministrador extends Stage {

    BorderPane root;
    VBox menuLateral;
    public Pane viewSubMenu;
    public Pane viewAdmUser;
    public Pane viewBusqueda;
    public Pane viewAdmProduct;
    public Pane viewAdmCompra;
    
    public JFXButton btnUsuarios;
    public JFXButton btnBusqueda;
    public JFXButton btnProductos;
    public JFXButton btnComprar;
    
    public ScrollPane scrollpaneListUser;
    public VBox paneVerticalListUser;
    
    public JFXTabPane tapPaneCompras;
    public Tab tapPendientes;
    public VBox comprasPendientesList;
    public Tab tapAnuladas;
    public VBox compraseAnuladasList;
    public Tab tapExitosas;
    public VBox comprasExitosasList;
    public VBox tabVerticalPane;
    
   
     
        

    public MenuAdministrador() {
        super();
        
        root = new BorderPane();
        root.setPrefWidth(700);
        root.setPrefHeight(600);
        
        
        initComponents();
        chargeMenuAdminUser();
        chargeMenuBuscar();
        chargeMenuProduc();
        chargeMenuCompra();

        asigComponents();
        root.setLeft(menuLateral);
        root.setCenter(viewSubMenu);
        //root.getChildren().addAll(menuLateral,viewSubMenu);
        setScene(new Scene(root));
        getScene().getStylesheets().add("assets/menuAdmin.css");

        //addItems();
    }
    public void initComponents(){
        
        menuLateral = new VBox();
        viewSubMenu = new AnchorPane();
        

        
        viewBusqueda = new Pane();
        viewAdmProduct = new Pane();
        
        scrollpaneListUser = new ScrollPane();
        paneVerticalListUser = new VBox(10);
        
        btnUsuarios = new JFXButton("Usuarios");
        btnBusqueda = new JFXButton("Buscar");
        btnProductos = new JFXButton("Productos");
        btnComprar =  new JFXButton("Compras");
        
        
        
        
    }
    public void asigComponents(){
        menuLateral.getChildren().addAll(btnBusqueda,btnUsuarios,btnProductos,btnComprar);
        menuLateral.setAlignment(Pos.TOP_CENTER);
        menuLateral.setPrefWidth(150);
        menuLateral.setPrefHeight(root.getPrefHeight());
        menuLateral.getStyleClass().add("vbox");
        
        btnBusqueda.setMinWidth(menuLateral.getPrefWidth());
        btnProductos.setMinWidth(menuLateral.getPrefWidth());
        btnUsuarios.setMinWidth(menuLateral.getPrefWidth());
        btnComprar.setMinWidth(menuLateral.getPrefWidth());
        
        viewSubMenu.getChildren().addAll(viewAdmUser,viewBusqueda,viewAdmProduct,viewAdmCompra);
        
    }
    public void chargeMenuAdminUser(){
        viewAdmUser = new Pane();
        
        double altoPane = root.getPrefHeight(); 
        double anchoPane=700-menuLateral.getPrefWidth();
        viewAdmUser.getStyleClass().add("amduser");
        viewAdmUser.setPrefSize(anchoPane,altoPane);
        scrollpaneListUser.setContent(paneVerticalListUser);
        scrollpaneListUser.setPrefSize(anchoPane, altoPane);
        viewAdmUser.getChildren().add(scrollpaneListUser);
        addItems();
        
    }
    public void chargeMenuBuscar(){
        double altoPane = root.getPrefHeight(); 
        double anchoPane=700-menuLateral.getPrefWidth(); 
        viewBusqueda.getStyleClass().add("amdbusqueda");
        viewBusqueda.setPrefSize(anchoPane,altoPane);
    }
    public void chargeMenuProduc(){
        double altoPane = root.getPrefHeight(); 
        double anchoPane=700-menuLateral.getPrefWidth(); 
        viewAdmProduct.getStyleClass().add("amdproduct");
        viewAdmProduct.setPrefSize(anchoPane,altoPane);
    }
    
    public void chargeMenuCompra(){
        viewAdmCompra = new Pane();
        comprasExitosasList = new VBox(10);
        comprasPendientesList = new VBox(10);
        compraseAnuladasList = new VBox(10);
        
        ScrollPane scrollAnuladas = new ScrollPane(compraseAnuladasList);
        ScrollPane scrollExitosas = new ScrollPane(comprasExitosasList);
        ScrollPane scrollPendientes = new ScrollPane(comprasPendientesList);
        tapPaneCompras = new JFXTabPane();
        tapAnuladas = new Tab("Anuladas");
        tapExitosas = new Tab("Exitosas");
        tapPendientes = new Tab("Pendientes");
        
        tapAnuladas.setContent(scrollAnuladas);
        tapExitosas.setContent(scrollExitosas);
        tapPendientes.setContent(scrollPendientes);
        
        double altoPane = root.getPrefHeight(); 
        double anchoPane=700-menuLateral.getPrefWidth(); 
        viewAdmCompra.setPrefSize(anchoPane, altoPane);
        tapPaneCompras.setPrefSize(anchoPane, altoPane);
        scrollAnuladas.setPrefSize(anchoPane, altoPane);
        scrollExitosas.setPrefSize(anchoPane, altoPane);
        scrollPendientes.setPrefSize(anchoPane, altoPane);
        tapPaneCompras.getTabs().addAll(tapPendientes,tapAnuladas,tapExitosas);
        
        viewAdmCompra.getChildren().add(tapPaneCompras);
        addItemsCompras();
    }
    public void changeViewAdmin(String nameButton){
        switch(nameButton){
            case "BUSCAR":
                viewBusqueda.toFront();
                break;
            case "USERS":
                viewAdmUser.toFront();
                break;
            case "PRODUCTO":
                viewAdmProduct.toFront();
                break;
            case "COMPRA":
                viewAdmCompra.toFront();
                break;

        }
    }

    void addItems() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            Usuario user;
            if(i%3 == 0)
                user = new Comprador();
            else if(i%3 == 2)
                user = new Vendedor();
            else user = new Administrador();
            
            user.setNombres("Prueba ");
            user.setApellidos("Prototype Name "+String.valueOf(i));
            
            paneVerticalListUser.getChildren().add(new UserItem(user));


        }
    }
    void addItemsCompras() {
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
            comprasExitosasList.getChildren().add(new SearchItem(articulo));
            comprasPendientesList.getChildren().add(new CompradoItem(p));
            compraseAnuladasList.getChildren().add(new CompradoItem(p));
        }
    }
    
    public void buscarAction(EventHandler<ActionEvent> eventHandler){
        btnBusqueda.setOnAction(eventHandler);
    }
    
    public void productosAction(EventHandler<ActionEvent> eventHandler){
        btnProductos.setOnAction(eventHandler);
        
    }
    public void usersAction(EventHandler<ActionEvent> eventHandler){
        btnUsuarios.setOnAction(eventHandler);
    }
    public void compraAction(EventHandler<ActionEvent> eventHandler){
        btnComprar.setOnAction(eventHandler);
    }
    
//
//    public void addSearchResultItem(SearchItem item) {
//        this.searchResultList.getChildren().add(item);
//    }
//
//    public void addComprasPendientesItem(CompradoItem item) {
//        this.comprasPendientesList.getChildren().add(item);
//    }
//
//    public void addMasBuscadosItem(SearchItem item) {
//        this.masBuscadosList.getChildren().add(item);
//    }
//
//    public void removeSearchResultItem(SearchItem item) {
//        this.searchResultList.getChildren().remove(item);
//    }
//
//    public void removeComprasPendientesItem(CompradoItem item) {
//        this.comprasPendientesList.getChildren().remove(item);
//    }
//
//    public void removeMasBuscadosItem(SearchItem item) {
//        this.masBuscadosList.getChildren().remove(item);
//    }

    
}
