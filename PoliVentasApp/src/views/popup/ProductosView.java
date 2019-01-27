package views.popup;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.StageDecoratorX;
import views.items.*;

public class ProductosView extends Stage {
    protected final AnchorPane root;
    protected final VBox vBoxContainer;
    protected final Label title;
    
    protected  HBox hBoxContainer;
    protected  Button eliminarButton;
    protected  Button modificarButton;
    
    

    public ProductosView(ArticuloItem item) {
        
        root= new AnchorPane();
        vBoxContainer = new VBox();
        title = new Label();
        
        hBoxContainer = new HBox();
        eliminarButton = new Button();
        modificarButton = new Button();
        

       
        root.setPrefHeight(300.0);
        root.setPrefWidth(300.0);

        vBoxContainer.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBoxContainer.setPrefHeight(300.0);
        vBoxContainer.setPrefWidth(300.0);

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        title.setPrefHeight(64.0);
        title.setPrefWidth(400.0);
        title.getStyleClass().add("title");
        title.setText("Detalle producto");

        
        hBoxContainer.setAlignment(javafx.geometry.Pos.CENTER);
        hBoxContainer.setLayoutX(1.0);
        hBoxContainer.setLayoutY(404.0);
        hBoxContainer.setPrefHeight(82.0);
        hBoxContainer.setPrefWidth(250.0);
        hBoxContainer.setSpacing(30.0);

        eliminarButton.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        eliminarButton.setLayoutX(10.0);
        eliminarButton.setMnemonicParsing(false);
        eliminarButton.setOnAction(this::eliminarProducto);
        eliminarButton.setPrefHeight(30.0);
        eliminarButton.setPrefWidth(90.0);
        eliminarButton.getStyleClass().add("button_style");
        eliminarButton.setText("Eliminar");
        eliminarButton.setPadding(new Insets(0.0, 5.0, 0.0, 0.0));

        modificarButton.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        modificarButton.setMnemonicParsing(false);
        
        modificarButton.setPrefHeight(30.0);
        modificarButton.setPrefWidth(90.0);
        modificarButton.getStyleClass().add("button_style");
        modificarButton.setText("Modificar");
        root.setPadding(new Insets(5,5,5,5));
        
        
        vBoxContainer.setSpacing(10);
        vBoxContainer.getChildren().add(title);
        vBoxContainer.getChildren().add(item);
        
        vBoxContainer.getChildren().add(eliminarButton);
        vBoxContainer.getChildren().add(modificarButton);
        root.getChildren().add(vBoxContainer);

        setScene(new Scene(root));
        root.getStylesheets().add("assets/Vendedor_Items.css");
    }
    
     void eliminarProducto(ActionEvent actionEvent){
     String answer = "";
        ButtonType buttonYes = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType buttonNO = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.WARNING,
                 "¿Esta seguro que desea eliminar este producto?",
                buttonYes,
                buttonNO);

        alert.setTitle("Confirmar eliminación de producto");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(buttonYes).equals("Si")) {
            answer = "Y";
        } else if (result.orElse(buttonNO).equals("No")) {
            answer = "N";
        }
     }

     class modificarProducto implements EventHandler<MouseEvent> {
        ArticuloItem item;

        public modificarProducto(ArticuloItem item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            ModificarProducto mp = new ModificarProducto(item);
            new StageDecoratorX(mp);
            mp.show();         
        }
    }
}
