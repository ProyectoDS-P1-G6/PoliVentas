/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.handlers;

import controllers.DetallePedidoController;
import controllers.MenuVendedorController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Pedido;
import utils.StageDecoratorX;

/**
 *
 * @author miguelps
 */
 public class OnPedidoSelected implements  EventHandler<MouseEvent>{

        Pedido pedido;

        public OnPedidoSelected(Pedido p) {
            this.pedido = p;
        }

        @Override
        public void handle(MouseEvent event) {
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/popup/DetallePedido.fxml"));
                Stage pedidoStage = new Stage();
                pedidoStage.setScene(new Scene(loader.load()));
                loader.<DetallePedidoController>getController().setPedido(pedido);
                new StageDecoratorX(pedidoStage); 
                pedidoStage.showAndWait();
                
            } catch (IOException ex) {
                Logger.getLogger(MenuVendedorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }