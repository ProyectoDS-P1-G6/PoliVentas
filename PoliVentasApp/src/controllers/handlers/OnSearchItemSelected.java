/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.handlers;

import controllers.MakeSearch;
import controllers.RealizarCompraController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Articulo;
import utils.StageDecoratorX;

/**
 *
 * @author miguelps
 */
public class OnSearchItemSelected implements EventHandler<MouseEvent>{
    Articulo articulo;
    MakeSearch makeSearchcontroller;

        public OnSearchItemSelected(Articulo item, MakeSearch controller) {
            this.articulo = item;
            this.makeSearchcontroller = controller;
        }

        @Override
        public void handle(MouseEvent event) {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/RealizarCompra.fxml"));
            Stage realizarCompra = new Stage();
            try {
                realizarCompra.setScene(new Scene(loader.load()));
                new StageDecoratorX(realizarCompra);
                RealizarCompraController controller = loader.getController();
                controller.setArticulo(articulo);
                controller.setDBService(makeSearchcontroller.getDB());
                realizarCompra.showAndWait();
                makeSearchcontroller.actualizarPedidosPendientes();
            } catch (IOException ex) {
                Logger.getLogger(OnSearchItemSelected.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
}
