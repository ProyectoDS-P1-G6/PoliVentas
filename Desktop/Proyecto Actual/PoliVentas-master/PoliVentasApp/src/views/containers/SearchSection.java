/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.containers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import views.items.SearchItem;

/**
 *
 * @author miguelps
 */
public class SearchSection extends Pane{
    
    JFXTextField searchBox;
    JFXButton searchButton;
    VBox searchResultList;

    public SearchSection() {
        super();
        
        searchBox    = new JFXTextField();
        searchBox.setPromptText("  Buscar Producto");
        searchBox.setLayoutX(10);
        searchBox.setLayoutY(10);
        searchBox.setPrefWidth(200);
        searchBox.setPrefHeight(30);
        
        searchResultList = new VBox(10);
        searchResultList.getStyleClass().add("searchResultList");
        ScrollPane searchResult = new ScrollPane();
        //searchResultList.setOrientation(Orientation.VERTICAL);
        searchResult.setLayoutX(10);
        searchResult.setLayoutY(50);
        searchResult.setPrefWidth(350);
        searchResult.setPrefHeight(330);

        searchResult.setContent(searchResultList);
        searchButton = new JFXButton();
        searchButton.setLayoutX(200);
        searchButton.setLayoutY(13);
        searchButton.getStyleClass().add("search_button");
        getChildren().addAll(searchBox, searchButton, searchResult);
    }
    
    public void cleanSearchResultItem() {
        this.searchResultList.getChildren().clear();
    }
    
    public void addSearchResultItem(SearchItem item) {
        this.searchResultList.getChildren().add(item);
    }
    
    public void setOnInputChanged(ChangeListener<String> listener){
        searchBox.textProperty().addListener(listener);
    }
    
    public String getTextInput(){
        return searchBox.getText();
    }
    
}
