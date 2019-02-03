/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import controllers.handlers.OnSearchInputChanged;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.*;
import models.entities.Vendedor;
import services.CompradorServiceDB;
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
public class MenuVendedorController implements Initializable, Returnable, MakeSearch {

    Returnable previusWindow;
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
        searchBox.clear();
        searchBox.textProperty().addListener(new OnSearchInputChanged(this));
        
        actualizarVentas();
        addMisProductos();

    }

    @Override
    public void setPreviusWindow(Returnable previous) {
        this.previusWindow = previous;
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
                previusWindow.showWindow();
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
    
    
   void actualizarPedidos(){
       cleanPedidosPendientes();
        List<Pedido> pedidos =  db.getPedidosPendientes(LoginServiceDB.getActualLogin().getUsuario());
        
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
                        previusWindow.showWindow();
                    }
        }
    }
    
    
        
    @Override
    public String getTextInput(){
        return searchBox.getText();
    }
    @Override
    public void cleanSearchResultItem() {
        this.searchResultList.getChildren().clear();
    }
    
    @Override
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
    
    @Override
     public void cleanPedidosPendientes(){
        this.misComprasList.getChildren().clear();
    }
     
      @FXML
    void search(ActionEvent event){
        
    }

    @Override
    public void actualizarPedidosPendientes() {
        actualizarPedidos();
    }

    @Override
    public ContextMenu getSugerencias_busqueda() {
        return this.sugerencias_busqueda;
    }

    @Override
    public TextField getSearchBox() {
       return this.searchBox;
    }

    @Override
    public CompradorServiceDB getDB() {
        return this.db;
    }

    @Override
    public void addArticulosMasBuscados() {
    }
     
     public void addPedidoItem(PedidoItem item) {
        this.misComprasList.getChildren().add(item);
    }
    
}



