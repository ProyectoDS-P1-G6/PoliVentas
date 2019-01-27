/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import models.Articulo;
import models.Pedido;
import services.CompradorServiceDB;
import services.LoginServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.items.PedidoItem;
import views.items.ArticuloItem;
import views.popup.RealizarCompraController;


public class MenuCompradorController implements Initializable, Returnable{
    
    Returnable login;
    Stage active_popup;
   
    @FXML
    private Label logout;

    @FXML
    private JFXTextField searchBox;
    @FXML
    private ContextMenu sugerencias_busqueda;
    
    
    @FXML
    private VBox searchResultList;
    @FXML
    private HBox pedidosList;
    @FXML
    private VBox masBuscadosList;


    private CompradorServiceDB db;

    public MenuCompradorController() {

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = new CompradorServiceDB();

        searchBox.textProperty().addListener(new OnSearchInputChanged());
        active_popup = new Stage();
        
        actualizarPedidos();
        addArticulosMasBuscados();
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
            if(active_popup.isShowing())
                active_popup.close();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/RealizarCompra.fxml"));
            Stage realizarCompra = new Stage();
            try {
                realizarCompra.setScene(new Scene(loader.load()));
                 new StageDecoratorX(realizarCompra);
                 realizarCompra.show();
                 RealizarCompraController controller = loader.getController();
                 controller.setItem(item);
            } catch (IOException ex) {
                Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
    }

    class OnPedidoSelected implements  EventHandler<MouseEvent>{

        Pedido pedido;

        public OnPedidoSelected(Pedido p) {
            this.pedido = p;
        }

        @Override
        public void handle(MouseEvent event) {
            
            if(active_popup.isShowing())
                active_popup.close();
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/DetallePedido.fxml"));
                active_popup = new Stage();
                active_popup.setScene(new Scene(loader.load()));
                loader.<DetallePedidoController>getController().setPedido(pedido);
                new StageDecoratorX(active_popup); 
                active_popup.showAndWait();
                
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
                   
                    addArticulosMasBuscados();
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
            pedidosList.getChildren().add(item);
        }
    }
    
    void addArticulosMasBuscados(){
        cleanMasBuscados();
        List<Articulo> articulos = db.getArticulosMasBuscados();
        
        for(Articulo a: articulos){
            ArticuloItem item = new ArticuloItem(a);
            item.setOnMouseClicked(new OnSearchItemSelected(item));
            masBuscadosList.getChildren().add(item);
        }
    }

    @Override
    public void setPreviousWindow(Returnable previous) {
        this.login = previous;
    }

    @Override
    public void showWindow() {
        
    }
    
    public void cleanSearchResultItem() {
        this.searchResultList.getChildren().clear();
    }
    
    public void cleanMasBuscados(){
        this.masBuscadosList.getChildren().clear();
    }
    
    public void addSearchResultItem(ArticuloItem item) {
        this.searchResultList.getChildren().add(item);
    }
    
    public String getTextInput(){
        return searchBox.getText();
    }
    
     public void cleanPedidosPendientes(){
        this.pedidosList.getChildren().clear();
    }
    
    
}
