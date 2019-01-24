package views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Inicio extends Stage {

	  AnchorPane root;
	  VBox nuevosArticulosList;
	  VBox masBuscadosArticulosList ; 
	  private JFXButton loginButton;
	  private JFXButton registerButton;
	  
	public Inicio() {
		super();
		 root = new AnchorPane();
	     root.setPrefWidth(600);
	     root.setPrefHeight(400);
	     
	     ScrollPane nuevosArticulos = new ScrollPane();
	        nuevosArticulosList = new VBox(10);
	        nuevosArticulosList.getStyleClass().add("nuevosArticulosList");
	        nuevosArticulos.setPrefWidth(290);
	        nuevosArticulos.setPrefHeight(400);
	        nuevosArticulos.setLayoutX(0);
	        nuevosArticulos.setLayoutY(210);

	        nuevosArticulos.setContent(nuevosArticulosList);
	        Label tituloLabel = new Label("Poliventas");
	        tituloLabel.getStyleClass().add("title");
	        tituloLabel.setLayoutX(nuevosArticulos.getLayoutX()+100);
	        tituloLabel.setLayoutY(nuevosArticulos.getLayoutY()-100);
	        
	        
	        Label nuevosLabel = new Label("Articulos nuevos.");
	        nuevosLabel.getStyleClass().add("text_style");
	        nuevosLabel.setLayoutX(nuevosArticulos.getLayoutX());
	        nuevosLabel.setLayoutY(nuevosArticulos.getLayoutY()-25);
	        
	        ScrollPane masBuscados = new ScrollPane();
	        masBuscadosArticulosList = new VBox(10);
	        masBuscadosArticulosList.getStyleClass().add("nuevosArticulosList");
	        masBuscados.setPrefWidth(290);
	        masBuscados.setPrefHeight(400);
	        masBuscados.setLayoutX(300);
	        masBuscados.setLayoutY(210);
	        
	        Label masbuscadosLabel = new Label("Articulos mas buscados.");
	        masbuscadosLabel.getStyleClass().add("text_style");
	        masbuscadosLabel.setLayoutX(masBuscados.getLayoutX());
	        masbuscadosLabel.setLayoutY(masBuscados.getLayoutY()-25);
	        
	        
	        loginButton     = new JFXButton("Log In");
	        loginButton.getStyleClass().add("button_style");
	        loginButton.setLayoutX(masBuscados.getLayoutX() + 100);
	        loginButton.setLayoutY(masBuscados.getLayoutY()-200);
	        
	        registerButton     = new JFXButton("Register");
	        registerButton.getStyleClass().add("button_style");
	        registerButton.setLayoutX(masBuscados.getLayoutX() + 100);
	        registerButton.setLayoutY(masBuscados.getLayoutY()-150);
	        
	        masBuscados.setContent(masBuscadosArticulosList);
	        root.getChildren().addAll(tituloLabel,registerButton,loginButton, nuevosLabel,masbuscadosLabel,nuevosArticulos, masBuscados);
	        setScene(new Scene(root));
	        getScene().getStylesheets().add("assets/Inicio.css");
	        
	}
	
	public void addLoginAction(EventHandler<ActionEvent> eventHandler){
        loginButton.setOnAction(eventHandler);
    }
	public void addregisterinAction(EventHandler<ActionEvent> eventHandler){
        registerButton.setOnAction(eventHandler);
    }

	
	


}
