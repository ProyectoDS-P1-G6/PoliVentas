/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.handlers;

import controllers.MakeSearch;
import controllers.MenuCompradorController;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import models.Articulo;
import views.items.ArticuloItem;

/**
 *
 * @author miguelps
 */
public class OnSearchInputChanged implements ChangeListener<String> {

    MakeSearch controller;
    public OnSearchInputChanged(MakeSearch makeSearchController) {
        this.controller = makeSearchController;
    }
    
    
    
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            
           List<CustomMenuItem> menuItems = new LinkedList<>();
           
           
           if(controller.getTextInput().length() < 3 || controller.getTextInput().length() > 20){
               controller.getSugerencias_busqueda().hide();
               return;
           }
           if(newValue.length() < oldValue.length()){
               return;
           }
               
           //cleanSearchResultItem();
           List<Articulo> articulos = controller.getDB().buscarArticulo(controller.getTextInput().toLowerCase());
           for(Articulo articulo: articulos){
               CustomMenuItem sugerenciaItem = new CustomMenuItem(new Label(articulo.toString()), true);
               sugerenciaItem.setOnAction((ActionEvent actionEvent) -> {
                    controller.cleanSearchResultItem();
                    String nombre = ((Label) sugerenciaItem.getContent()).getText();
                    controller.getSearchBox().setText(articulo.getNombre().toLowerCase());
                    controller.getSugerencias_busqueda().hide();
                    ArticuloItem item = new ArticuloItem(articulo);
                    item.setOnMouseClicked(new OnSearchItemSelected(articulo, controller));
                    controller.addSearchResultItem(item);
                    controller.getDB().addPuntoDeBusqueda(articulo);
                   
                    controller.addArticulosMasBuscados();
               });
               menuItems.add(sugerenciaItem);
           }
            controller.getSugerencias_busqueda().show(controller.getSearchBox(), Side.BOTTOM, 0, 0);
            controller.getSugerencias_busqueda().getItems().clear();
            controller.getSugerencias_busqueda().getItems().addAll(menuItems);
   
        } 
}
