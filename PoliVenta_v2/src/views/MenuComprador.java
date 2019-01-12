package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Articulo;

public class MenuComprador extends Stage {

    AnchorPane root;

    JFXButton carritoButton;
    Label logout;


    /**
     * Sección de busqueda.
     */
    JFXTextField searchBox;
    JFXButton searchButton;
    ListView<ItemArticulo> searchResultList;


    ListView<ItemArticulo> comprasPendientesList;
    ListView<ItemArticulo> masBuscadosList;


    public MenuComprador() {

        super();

        carritoButton = new JFXButton();
        logout = new Label("Log out");
        logout.setLayoutX(640);

        comprasPendientesList = new ListView<>();
        comprasPendientesList.setOrientation(Orientation.HORIZONTAL);
        comprasPendientesList.setPrefWidth(680);
        comprasPendientesList.setPrefHeight(150);
        comprasPendientesList.setLayoutX(10);
        comprasPendientesList.setLayoutY(30);


        Pane searchSection = new Pane();
        searchSection.setStyle("-fx-background-color: grey;");
        searchSection.setLayoutX(10);
        searchSection.setLayoutY(190);
        searchSection.setPrefWidth(450);
        searchSection.setPrefHeight(400);
        searchBox    = new JFXTextField();
        searchBox.setLayoutX(10);
        searchBox.setLayoutY(10);
        searchBox.setPrefWidth(200);
        searchBox.setPrefHeight(30);
        searchResultList = new ListView<>();
        searchResultList.setOrientation(Orientation.VERTICAL);
        searchResultList.setLayoutX(10);
        searchResultList.setLayoutY(50);
        searchResultList.setPrefWidth(430);
        searchResultList.setPrefHeight(330);
        searchButton = new JFXButton();
        searchButton.setLayoutX(200);
        searchButton.setLayoutY(13);
        searchButton.getStyleClass().add("search_button");
        searchSection.getChildren().addAll(searchBox, searchButton, searchResultList);

        masBuscadosList = new ListView<>();
        masBuscadosList.setOrientation(Orientation.VERTICAL);
        masBuscadosList.setPrefWidth(220);
        masBuscadosList.setPrefHeight(400);
        masBuscadosList.setLayoutX(470);
        masBuscadosList.setLayoutY(190);


        root = new AnchorPane();
        root.setPrefWidth(700);
        root.setPrefHeight(600);

        root.getChildren().addAll(carritoButton,logout,comprasPendientesList, searchSection, masBuscadosList);
        setScene(new Scene(root));
        getScene().getStylesheets().add("assets/menu_comprador.css");


        for (int i = 0; i<50; i++){
            masBuscadosList.getItems().add(new ItemArticulo(new Articulo()));
            comprasPendientesList.getItems().add(new ItemArticulo(new Articulo()));
            searchResultList.getItems().add(new ItemArticulo(new Articulo()));
        }
    }

}
