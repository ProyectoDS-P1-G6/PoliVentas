/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Estado;
import models.Pedido;
import services.VendedorServiceDB;
import utils.StageDecoratorX;

/**
 * FXML Controller class
 *
 * @author carlasanchez
 */
public class DetallesVentaController implements Initializable {

    @FXML
    VBox vBox;
    @FXML
    Label noventa;
    @FXML
    Label nombre_producto;
    @FXML
    Label cantidad;
    @FXML
    Label total;
    @FXML
    Label estado;
    @FXML
    Label titulo;
    @FXML
    HBox hBox;
    @FXML
    ImageView image;
    @FXML
    Button anular_button;
    @FXML
    Button verMapa_button;

    Pedido venta;
    private VendedorServiceDB db;

    public DetallesVentaController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setVenta(Pedido venta, VendedorServiceDB db) {
        this.db = db;
        this.venta = venta;
        image.setImage(venta.getArticulo().getIcon());

        titulo.setText("Detalles de la venta");
        noventa.setText("No. venta: " + Integer.toString(venta.getId()));
        cantidad.setText("Cantidad: " + venta.getCantidad());
        nombre_producto.setText("Nombre producto: " + venta.getArticulo().getNombre());
        total.setText("Precio: " + venta.getArticulo().getPrecio().toString());
        Estado e = venta.getEstado();
        estado.setText(e.toString());
        switch (e) {
            case ANULADO:
                estado.setStyle("-fx-text-fill: red;");
                break;
            case ENVIADO:
                estado.setStyle("-fx-text-fill: blue;");
                break;
            case ENTREGADO:
                estado.setStyle("-fx-text-fill: black;");
                break;
            case PENDIENTE:
                estado.setStyle("-fx-text-fill: green;");
                break;
        }

    }

    @FXML
    public void anularAction() {
        System.out.println("Anular");
        ButtonType buttonYes = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType buttonNO = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.WARNING,
                "¿Esta seguro que desea anular la venta?",
                buttonYes,
                buttonNO);

        alert.setTitle("Confirmar anulación de venta");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonYes) {
            db.anularVenta(this.venta);
            this.venta.setEstado(Estado.ANULADO);
            System.out.println(venta.getEstado());
            Stage stage = (Stage) anular_button.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    public void verMapaAction(javafx.event.ActionEvent actionEvent) {
        System.out.println("ver mapa");
        Stage stage = (Stage) verMapa_button.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/VerMapa.fxml"));
        Stage vermapa = new Stage();
        try {
            vermapa.setScene(new Scene(loader.load()));
            new StageDecoratorX(vermapa);
            vermapa.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuCompradorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public VendedorServiceDB getDb() {
        return db;
    }
}
