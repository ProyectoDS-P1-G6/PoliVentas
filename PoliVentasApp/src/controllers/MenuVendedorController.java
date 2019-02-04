/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controllers.handlers.OnPedidoSelected;
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

/**
 * FXML Controller class
 *
 * @author miguelps
 */
public class MenuVendedorController implements Initializable, Returnable, MakeSearch {

    Returnable login;
    VendedorServiceDB db;

    @FXML
    private Label logout;

    @FXML
    private Label agregarProducto;

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
    @FXML
    private JFXButton updateArticulos;
    @FXML
    private JFXButton updateVentasPendientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        db = new VendedorServiceDB();
        //vendedor = (Vendedor) LoginServiceDB.getActualLogin().getUsuario();
        logout.setOnMouseClicked(new LogOutAction());
        agregarProducto.setOnMouseClicked(new agregarProductoAction());
        searchBox.setText("");
        searchBox.textProperty().addListener(new OnSearchInputChanged(this));

        actualizarVentas();
        addMisProductos();
        actualizarPedidosPendientes();
        actualizarVentasPendientes();
    }

   

    @Override
    public void showWindow() {
        ((Stage) logout.getScene().getWindow()).show();
    }

    @FXML
    void logOutAction(MouseEvent event) {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");
     
        Optional<ButtonType> result = logoutAlert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                ((Stage) logout.getScene().getWindow()).close();
                login.showWindow();
            }
        }
    }

    @FXML
    void search(ActionEvent event) {

    }

    @Override
    public void setPreviusWindow(Returnable previous) {
        this.login = previous;
    }

    class OnMiProductoSelected implements EventHandler<MouseEvent> {

        ArticuloItem item;

        public OnMiProductoSelected(ArticuloItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/DetallesProducto.fxml"));
            Stage detalles = new Stage();
            try {
                detalles.setScene(new Scene(loader.load()));
                new StageDecoratorX(detalles);
                detalles.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            DetallesProductoController controller = loader.getController();
            controller.setArticulo(item.getArticulo(), db);

        }

    }

    @Override
    public void actualizarPedidosPendientes() {
        cleanPedidosPendientes();
        List<Pedido> pedidos = db.getPedidosPendientes(LoginServiceDB.getActualLogin().getUsuario());

        for (Pedido p : pedidos) {
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(p));
            addPedidoItem(item);
        }
    }

    private void actualizarVentas() {
        List<Pedido> ventas = db.getVentas((Vendedor) LoginServiceDB.getActualLogin().getUsuario());

        for (Pedido p : ventas) {
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnVentaSelected(item));
            addVentaItem(item);
        }

    }

    private void actualizarVentasPendientes() {
        List<Pedido> ventas = db.getVentasPendientes((Vendedor) LoginServiceDB.getActualLogin().getUsuario());

        for (Pedido p : ventas) {
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnVentaSelected(item));
            addVentaPendienteItem(item);
        }

    }

    private void addMisProductos() {
        List<Articulo> misArticulos = db.getMisArticulos((Vendedor) LoginServiceDB.getActualLogin().getUsuario());

        for (Articulo a : misArticulos) {
            ArticuloItem item = new ArticuloItem(a);
            item.setOnMouseClicked(new OnMiProductoSelected(item));
            addProductoItem(item);
        }

    }

    class LogOutAction implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setContentText("¿Está seguro de cerrar la sesión?");

            Optional<ButtonType> result = logoutAlert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    ((Stage) logout.getScene().getWindow()).close();
                    login.showWindow();
                }
            }
        }
    }

    @FXML
    void updateActiculosAction(ActionEvent event) {

        cleanProductoItem();
        addMisProductos();

    }

    @FXML
    void updateVentasPendientesAction(ActionEvent event) {

        cleanVentas();
        cleanVentasPendientes();

        actualizarVentas();
        actualizarVentasPendientes();

    }

    class agregarProductoAction implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/AgregarProducto.fxml"));
            Stage agregar = new Stage();
            try {
                agregar.setScene(new Scene(loader.load()));
                new StageDecoratorX(agregar);
                agregar.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            AgregarProductoController controller = loader.getController();
            controller.setVendedorCon(db, LoginServiceDB.getActualLogin().getUsuario());

        }
    }

    class OnVentaSelected implements EventHandler<MouseEvent> {

        PedidoItem item;

        public OnVentaSelected(PedidoItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/DetallesVenta.fxml"));
            Stage ventaDetalles = new Stage();
            try {
                ventaDetalles.setScene(new Scene(loader.load()));
                new StageDecoratorX(ventaDetalles);
                ventaDetalles.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
            }

            DetallesVentaController controller = loader.getController();
            controller.setVenta(item.getPedido(), db);
            
        }
    }

    @Override
    public String getTextInput() {
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

    public void addVentaItem(PedidoItem item) {
        this.historialVentasList.getChildren().add(item);
    }

    public void addProductoItem(ArticuloItem item) {
        this.articulosList.getChildren().add(item);
    }

    public void cleanProductoItem() {
        this.articulosList.getChildren().clear();
    }

    public void cleanVentasPendientes() {
        this.ventasPendientesList.getChildren().clear();
    }

    public void cleanVentas() {
        this.historialVentasList.getChildren().clear();
    }

    @Override
    public void cleanPedidosPendientes() {
        this.misComprasList.getChildren().clear();
    }

    public void addPedidoItem(PedidoItem item) {
        this.misComprasList.getChildren().add(item);
    }

    @Override
    public ContextMenu getSugerencias_busqueda() {
        return sugerencias_busqueda;
    }

    @Override
    public TextField getSearchBox() {
        return searchBox;
    }

    @Override
    public CompradorServiceDB getDB() {
        return this.db;
    }

    @Override
    public void addArticulosMasBuscados() {
    }

}
