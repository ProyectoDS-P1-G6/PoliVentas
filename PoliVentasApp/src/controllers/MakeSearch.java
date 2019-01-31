/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import services.CompradorServiceDB;
import views.items.ArticuloItem;

/**
 *
 * @author miguelps
 */
public interface MakeSearch {
    void cleanSearchResultItem();
    void addSearchResultItem(ArticuloItem item);
    String getTextInput();
    void cleanPedidosPendientes();
    void actualizarPedidosPendientes();
    ContextMenu getSugerencias_busqueda();
    TextField getSearchBox();
    CompradorServiceDB getDB();
    void addArticulosMasBuscados();
}
