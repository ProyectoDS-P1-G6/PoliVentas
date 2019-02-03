package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import views.items.ArticuloItem;
import views.items.ArticuloItemAdm;
import views.items.PedidoItem;
import views.items.UserItem;

public final class MenuAdministrador extends Stage {

    BorderPane root;
    public Pane viewSubMenu;
    
    //Controles de vista Busqueda
    public Label tituloBusqueda;
    public VBox viewBusqueda;
    public JFXTextField barraBusqueda;
    public JFXButton btnSearch;
    public VBox contenedorBusquedas;
    
    //Controles de vista del menu lateral principal
    AnchorPane anchorMenuLateral;
    AnchorPane menuLateral, anchorMenuIcon;
    Line lineuser;
    public Label lUserAdminName;
    public ImageView iconUserAdmin;
    public JFXButton btnUsuarios;
    public JFXButton btnBusqueda;
    public JFXButton btnProductos;
    public JFXButton btnComprar;

    //Controles de vista administracion de usuario
    public Pane viewAdmUser;    
    public ScrollPane scrollpaneListUser;
    public VBox paneVerticalListUser;
    public JFXButton btnCreateUser;
    public JFXButton btnActualizarVista;
    
    //Componentes menuAdministracion de productos
    public Pane viewAdmProduct;
    public ScrollPane scrollpaneListProduct;
    public VBox paneVerticalListProduct;
    public JFXButton btnCrearProducto;
    public JFXButton btnActualizarVistaProducto;
    
    //Controles de vista Compras
    public Pane viewAdmCompra;
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
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        
        
        initMenuPrincipal();
        chargeMenuAdminUser();
        chargeMenuBuscar();
        chargeMenuProduc();
        chargeMenuCompra();

        initCharacterMenuPrincipal();
        
        root.setLeft(menuLateral);
        root.setCenter(viewSubMenu);
        setScene(new Scene(root));
        getScene().getStylesheets().add("assets/menuAdmin.css");

    }
    public void initMenuPrincipal(){
        anchorMenuLateral= new AnchorPane();
        anchorMenuIcon = new AnchorPane();
        menuLateral = new AnchorPane();
        viewSubMenu = new AnchorPane();
        
        iconUserAdmin = new ImageView(new Image("file:src/assets/iconUser.png"));
        iconUserAdmin.setFitWidth(40); iconUserAdmin.setFitHeight(40);
        lUserAdminName =  new Label("User Admin");
        iconUserAdmin.getStyleClass().add("colorLabel");
        lineuser = new Line();
        
        btnUsuarios = new JFXButton("Usuarios");
        btnUsuarios.getStyleClass().add("colorlabel");
        btnBusqueda = new JFXButton("Buscar");
        btnBusqueda.getStyleClass().add("colorlabel");
        btnProductos = new JFXButton("Productos");
        btnProductos.getStyleClass().add("colorlabel");
        btnComprar =  new JFXButton("Compras");
        btnComprar.getStyleClass().add("colorlabel");
        
    }
    public void initCharacterMenuPrincipal(){
        
        anchorMenuIcon.setPrefWidth(50);
        anchorMenuIcon.setPrefHeight(root.getPrefHeight());
        anchorMenuIcon.setLayoutX(0);
        anchorMenuIcon.setLayoutY(0);
        anchorMenuIcon.getStyleClass().add("colorpanelicon");
        
        ImageView iconLupa = new ImageView(new Image("file:src/assets/search.png"));
        iconLupa.setLayoutX(8); iconLupa.setLayoutY(100);
        ImageView iconProductos = new ImageView(new Image("file:src/assets/miniProduc.png"));
        iconProductos.setLayoutX(10); iconProductos.setLayoutY(iconLupa.getLayoutY()+41);
        ImageView iconUsuario = new ImageView(new Image("file:src/assets/miniuser.png"));
        iconUsuario.setLayoutX(15); iconUsuario.setLayoutY(iconProductos.getLayoutY()+41);
        ImageView iconCompras = new ImageView(new Image("file:src/assets/minicompras.png"));
        iconCompras.setLayoutX(12); iconCompras.setLayoutY(iconUsuario.getLayoutY()+41);
        
        anchorMenuIcon.getChildren().addAll(iconLupa,iconProductos,iconUsuario,iconCompras);
        
        iconUserAdmin.setLayoutX(60);
        iconUserAdmin.setLayoutY(10);
        lUserAdminName.setLayoutX(60);
        lUserAdminName.getStyleClass().add("colorlabel");
        lUserAdminName.setLayoutY(70);
        lineuser.setStartX(60);lineuser.setEndX(240);
        lineuser.setLayoutY(lUserAdminName.getLayoutY()+20);
        lineuser.getStyleClass().add("linecolor");
        
        menuLateral.getChildren().addAll(anchorMenuIcon,iconUserAdmin,lUserAdminName,lineuser,btnBusqueda,btnUsuarios,btnProductos,btnComprar);
        menuLateral.setPrefWidth(250);
        menuLateral.setPrefHeight(root.getPrefHeight());
        menuLateral.getStyleClass().add("vbox");
        
        btnBusqueda.setMinWidth(menuLateral.getPrefWidth());
        btnBusqueda.setLayoutY(100);
        btnProductos.setMinWidth(menuLateral.getPrefWidth());
        btnProductos.setLayoutY(btnBusqueda.getLayoutY()+40);
        btnUsuarios.setMinWidth(menuLateral.getPrefWidth());
        btnUsuarios.setLayoutY(btnProductos.getLayoutY()+40);
        btnComprar.setMinWidth(menuLateral.getPrefWidth());
        btnComprar.setLayoutY(btnUsuarios.getLayoutY()+40);
        
        viewSubMenu.getChildren().addAll(viewAdmUser,viewAdmProduct,viewAdmCompra,viewBusqueda);
        
    }
    public void chargeMenuAdminUser(){
        scrollpaneListUser = new ScrollPane();
        paneVerticalListUser = new VBox(10);
        viewAdmUser = new HBox();
        VBox contenedorBotones = new VBox();
       
        double altoPane = root.getPrefHeight(); 
        double anchoPane=800-menuLateral.getPrefWidth();
        viewAdmUser.getStyleClass().add("amduser");
        viewAdmUser.setPrefSize(anchoPane,altoPane);
        scrollpaneListUser.setContent(paneVerticalListUser);
        scrollpaneListUser.setPrefSize(anchoPane-200, altoPane);
        
        btnActualizarVista = new JFXButton("Actualizar");
        btnCreateUser = new JFXButton("Nuevo\nUsuario");
        contenedorBotones.getChildren().addAll(btnCreateUser,btnActualizarVista);
        
        viewAdmUser.getChildren().addAll(scrollpaneListUser,contenedorBotones);
                
    }
    public void chargeMenuBuscar(){
        viewBusqueda = new VBox(15);
        double altoPane = root.getPrefHeight(); 
        double anchoPane=800-menuLateral.getPrefWidth(); 
        viewBusqueda.getStyleClass().add("amdbusqueda");
        viewBusqueda.setPrefSize(anchoPane,altoPane);
        
        tituloBusqueda = new Label("Busqueda de Articulos");
        barraBusqueda = new JFXTextField();
        barraBusqueda.setPrefWidth(300);
        
        btnSearch = new JFXButton();
        
        contenedorBusquedas = new VBox(15);
        contenedorBusquedas.setPrefWidth(400);
        contenedorBusquedas.setPrefHeight(400);
        contenedorBusquedas.getStyleClass().add("colorpanelicon");
        
        viewBusqueda.getChildren().addAll(tituloBusqueda,barraBusqueda,contenedorBusquedas);
        
    }
    public void chargeMenuProduc(){
        viewAdmProduct = new HBox();
        VBox contenedorBoton =  new VBox();
        scrollpaneListProduct = new ScrollPane();
        paneVerticalListProduct = new VBox(15);
        
        double altoPane = root.getPrefHeight(); 
        double anchoPane=800-menuLateral.getPrefWidth(); 
        viewAdmProduct.getStyleClass().add("amdproduct");
        viewAdmProduct.setPrefSize(anchoPane,altoPane);
        scrollpaneListProduct.setContent(paneVerticalListProduct);
        paneVerticalListProduct.setPrefSize(anchoPane-200, altoPane);
        
        btnCrearProducto = new JFXButton("Nuevo\nProducto");
        btnActualizarVistaProducto = new JFXButton("Actualizar");
        contenedorBoton.getChildren().addAll(btnCrearProducto,btnActualizarVistaProducto);
        
        viewAdmProduct.getChildren().addAll(scrollpaneListProduct,contenedorBoton);
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
        tapPaneCompras.getStyleClass().add("jfx-tab-pane");
        
        
        tapAnuladas = new Tab("Anuladas");
        tapAnuladas.getStyleClass().add("jfx-tab-pane");
        tapExitosas = new Tab("Exitosas");
        tapExitosas.getStyleClass().add("jfx-tab-pane");
        tapPendientes = new Tab("Pendientes");
        tapPendientes.getStyleClass().add("jfx-tab-pane");
        
        tapAnuladas.setContent(scrollAnuladas);
        tapExitosas.setContent(scrollExitosas);
        tapPendientes.setContent(scrollPendientes);
        
        double altoPane = root.getPrefHeight(); 
        double anchoPane=800-menuLateral.getPrefWidth(); 
        viewAdmCompra.setPrefSize(anchoPane, altoPane);
        tapPaneCompras.setPrefSize(anchoPane, altoPane);
        scrollAnuladas.setPrefSize(anchoPane, altoPane);
        scrollExitosas.setPrefSize(anchoPane, altoPane);
        scrollPendientes.setPrefSize(anchoPane, altoPane);
        tapPaneCompras.getTabs().addAll(tapPendientes,tapAnuladas,tapExitosas);
        viewAdmCompra.getChildren().add(tapPaneCompras);
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
    public void clearPanelUser(){
        paneVerticalListUser.getChildren().clear();
    }
    public void AsignarDatosAdministardor(String dato){
        this.lUserAdminName.setText(dato);
    }
    public void chargerUsuarios(UserItem userView){
        this.paneVerticalListUser.getChildren().add(userView);
    }
    public void chargerProductos(ArticuloItemAdm articuloView){
        this.paneVerticalListProduct.getChildren().add(articuloView);
    }
    public void chargerPedidosPendientes(PedidoItem item){
        this.comprasPendientesList.getChildren().add(item);
    }
    public void chargerPedidosExitosos(PedidoItem item){
        this.comprasExitosasList.getChildren().add(item);
    }
    public void chargerPedidosAnulados(PedidoItem item){
        this.compraseAnuladasList.getChildren().add(item);
    }

    public void createButtonUserAction(EventHandler<MouseEvent> eventHandler){
        btnCreateUser.setOnMouseClicked(eventHandler);
    }
    public void actualizarBtnAction(EventHandler<MouseEvent> eventHandler){
        btnActualizarVista.setOnMouseClicked(eventHandler);
    }
    public void createButtonProducAction(EventHandler<MouseEvent> eventHandler){
        btnCrearProducto.setOnMouseClicked(eventHandler);
    }
    public void actualizarBtnProductAction(EventHandler<MouseEvent> eventHandler){
        btnActualizarVistaProducto.setOnMouseClicked(eventHandler);
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
