/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import controllers.handlers.OnPedidoSelected;
import controllers.handlers.OnSearchInputChanged;
import controllers.handlers.OnSearchItemSelected;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Articulo;
import models.Pedido;
import services.CompradorServiceDB;
import services.LoginServiceDB;
import utils.Returnable;
import views.items.PedidoItem;
import views.items.ArticuloItem;


public class MenuCompradorController implements Initializable, Returnable, MakeSearch{
    
    Returnable previusWindow;
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

        searchBox.textProperty().addListener(new OnSearchInputChanged(this));
        active_popup = new Stage();
        
        actualizarPedidosPendientes();
        addArticulosMasBuscados();
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
    
    @FXML
    void search(ActionEvent event){
        
    }
    

    
    
    @Override
    public void actualizarPedidosPendientes(){

        cleanPedidosPendientes();
        List<Pedido> pedidos =  db.getPedidosPendientes(LoginServiceDB.getActualLogin().getUsuario());

        for(Pedido p: pedidos){
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(p));
            pedidosList.getChildren().add(item);
        }
  
    }
    
    @Override
    public void addArticulosMasBuscados(){
        cleanMasBuscados();
        List<Articulo> articulos = db.getArticulosMasBuscados();
        
        for(Articulo a: articulos){
            ArticuloItem item = new ArticuloItem(a);
            item.setOnMouseClicked(new OnSearchItemSelected(a, this));
            masBuscadosList.getChildren().add(item);
        }
    }

    @Override
    public void setPreviusWindow(Returnable previous) {
        this.previusWindow = previous;
    }

    @Override
    public void showWindow() {
        ((Stage)this.logout.getScene().getWindow()).show();
    }
    
    @Override
    public void cleanSearchResultItem() {
        this.searchResultList.getChildren().clear();
    }
    @Override
    public void addSearchResultItem(ArticuloItem item) {
        this.searchResultList.getChildren().add(item);
    }
    @Override
    public TextField getSearchBox() {
        return searchBox;
    }
    
    public void cleanMasBuscados(){
        this.masBuscadosList.getChildren().clear();
    }
    
    
    
    @Override
    public String getTextInput(){
        return searchBox.getText();
    }
    
    @Override
    public void cleanPedidosPendientes(){
        this.pedidosList.getChildren().clear();
    }
     
    @Override
     public ContextMenu getSugerencias_busqueda(){
         return sugerencias_busqueda;
     }
     
    @Override
     public CompradorServiceDB getDB(){
         return db;
     }
    
}
