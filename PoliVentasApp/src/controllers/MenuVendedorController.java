/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.*;
import models.entities.Vendedor;
import services.LoginServiceDB;
import services.VendedorServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.items.*;
import views.popup.*;

/**
 * FXML Controller class
 *
 * @author miguelps
 */
public class MenuVendedorController implements Initializable, Returnable {

    Returnable login;
    private VendedorServiceDB db;
    
    @FXML
    private Label logout;

    @FXML
    private HBox misComprasList;
    
    @FXML
    private VBox articulosList;
    @FXML
    private VBox historialVentasList;
    @FXML
    private VBox ventasPendientesList;
    
    
    @FXML
    private JFXTextField searchBox;
    @FXML
    private ContextMenu sugerencias_busqueda;
    @FXML
    private VBox searchResultList;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        db = new VendedorServiceDB();
        
        logout.setOnMouseClicked(new LogOutAction());
        searchBox.setText("");
        searchBox.textProperty().addListener(new OnSearchInputChanged());
        
        actualizarVentas();
        addMisProductos();

    }

    @Override
    public void setPreviousWindow(Returnable previous) {
        this.login = previous;
    }

    @Override
    public void showWindow() {
        ((Stage)logout.getScene().getWindow()).show();
    }
    
    @FXML
    void logOutAction(MouseEvent event){
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
        
            Optional<ButtonType> result = logoutAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                ((Stage)logout.getScene().getWindow()).close();
                login.showWindow();
            }
    }
    
    @FXML
    void search(ActionEvent event){
        
    }
    
        
    class OnSearchItemSelected implements EventHandler<MouseEvent> {
        ArticuloItem item;

        public OnSearchItemSelected(ArticuloItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/RealizarCompra.fxml"));
            Stage realizarCompra = new Stage();
            try {
                realizarCompra.setScene(new Scene(loader.load()));
                 new StageDecoratorX(realizarCompra);
                 realizarCompra.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuVendedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            RealizarCompraController controller = loader.getController();
            controller.setItem(item);
        }
    }
    
    class OnMiProductoSelected implements EventHandler<MouseEvent> {
        ArticuloItem item;

        public OnMiProductoSelected(ArticuloItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            ProductosView pv = new ProductosView(item);
            new StageDecoratorX(pv);
            pv.show();         
        }
    }

    class OnPedidoSelected implements  EventHandler<MouseEvent>{

        Pedido pedido;

        public OnPedidoSelected(Pedido p) {
            this.pedido = p;
        }

        @Override
        public void handle(MouseEvent event) {
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/DetallePedido.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                loader.<DetallePedidoController>getController().setPedido(pedido);
                new StageDecoratorX(stage); 
                stage.showAndWait();
                
            } catch (IOException ex) {
                Logger.getLogger(MenuVendedorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    class OnSearchInputChanged implements ChangeListener<String>{

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            
           List<CustomMenuItem> menuItems = new LinkedList<>();
           
           if(getTextInput().length() < 3 || getTextInput().length() > 20){
               sugerencias_busqueda.hide();
               return;
           }
           //cleanSearchResultItem();
           List<Articulo> articulos = db.buscarArticulo(getTextInput());
           for(Articulo articulo: articulos){
               CustomMenuItem sugerenciaItem = new CustomMenuItem(new Label(articulo.toString()), true);
               sugerenciaItem.setOnAction((ActionEvent actionEvent) -> {
                    cleanSearchResultItem();
                    String nombre = ((Label) sugerenciaItem.getContent()).getText();
                    searchBox.setText(articulo.getNombre());
                    sugerencias_busqueda.hide();
                    ArticuloItem item = new ArticuloItem(articulo);
                    item.setOnMouseClicked(new OnSearchItemSelected(item));
                    addSearchResultItem(item);
                    db.addPuntoDeBusqueda(articulo);

               });
               menuItems.add(sugerenciaItem);
           }
            sugerencias_busqueda.show(searchBox, Side.BOTTOM, 0, 0);
            sugerencias_busqueda.getItems().clear();
            sugerencias_busqueda.getItems().addAll(menuItems);
        }
        
    }
    
        void actualizarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(LoginServiceDB.getActualLogin().getUsuario());
        
        for(Pedido p: pedidos){
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(p));
            addPedidoItem(item);
        }
    }
    


    private void actualizarVentas() {
        List<Pedido> ventas = db.getVentas((Vendedor)LoginServiceDB.getActualLogin().getUsuario());

        for (Pedido p : ventas) {
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(p));
            addVentaPendienteItem(item);
        }
        
    }

    private void addMisProductos() {
        List<Articulo> misArticulos = db.getMisArticulos((Vendedor)LoginServiceDB.getActualLogin().getUsuario());
        
        for(Articulo a: misArticulos){
            ArticuloItem item = new ArticuloItem(a);
            item.setOnMouseClicked(new OnMiProductoSelected(item));
            addProductoItem(item);
        }

    }
    
    
    
    class LogOutAction implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
        
                    Optional<ButtonType> result = logoutAlert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        ((Stage)logout.getScene().getWindow()).close();
                        login.showWindow();
                    }
        }
    }
    
    
        
    public String getTextInput(){
        return searchBox.getText();
    }
    public void cleanSearchResultItem() {
        this.searchResultList.getChildren().clear();
    }
    
    public void addSearchResultItem(ArticuloItem item) {
        this.searchResultList.getChildren().add(item);
    }

    public void addVentaPendienteItem(PedidoItem item) {
        this.ventasPendientesList.getChildren().add(item);
    }
    
    public void addProductoItem(ArticuloItem item) {
        this.articulosList.getChildren().add(item);
    }
    
    public void cleanVentasPendientes(){
        this.ventasPendientesList.getChildren().clear();
    }
    
     public void cleanPedidosPendientes(){
        this.misComprasList.getChildren().clear();
    }
     
     public void addPedidoItem(PedidoItem item) {
        this.misComprasList.getChildren().add(item);
    }
    
}



