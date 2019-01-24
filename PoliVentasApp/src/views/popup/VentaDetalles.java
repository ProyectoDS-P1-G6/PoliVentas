package views.popup;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.StageDecoratorX;
import views.items.*;

public class VentaDetalles extends Stage {

    protected final AnchorPane root;
    protected final VBox vboxcontainer;
    protected final Label title;
    
    protected final HBox hBoxContainer;
    protected final Button anular;
    protected final Button verMapa;

    public VentaDetalles(VendidoItem item) {

        root = new AnchorPane();
        vboxcontainer = new VBox();
        title = new Label();
        
        hBoxContainer = new HBox();
        anular = new Button();
        verMapa = new Button();

        root.setPrefHeight(495.0);
        root.setPrefWidth(434.0);

        vboxcontainer.setPrefHeight(491.0);
        vboxcontainer.setPrefWidth(458.0);

        title.setAlignment(javafx.geometry.Pos.CENTER);
        title.setPrefHeight(51.0);
        title.setPrefWidth(466.0);
        title.getStyleClass().add("title");
        title.setText("Detalles de la Venta");

        

        hBoxContainer.setAlignment(javafx.geometry.Pos.CENTER);
        hBoxContainer.setPrefHeight(100.0);
        hBoxContainer.setPrefWidth(200.0);
        hBoxContainer.setSpacing(55.0);

        anular.setMnemonicParsing(false);
        anular.setOnAction(this::AnularVentaAction);
        anular.setPrefHeight(40.0);
        anular.setPrefWidth(150.0);
        anular.getStyleClass().add("button_style");
        anular.setText("Anular venta");

        verMapa.setAlignment(javafx.geometry.Pos.CENTER);
        verMapa.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        verMapa.setMnemonicParsing(false);
        verMapa.setOnAction(this::verMapaAction);
        verMapa.setPrefHeight(40.0);
        verMapa.setPrefWidth(150.0);
        verMapa.getStyleClass().add("button_style");
        verMapa.setText("Ver Mapa");
        root.setPadding(new Insets(5.0));

        vboxcontainer.getChildren().add(title);
        
        hBoxContainer.getChildren().add(anular);
        hBoxContainer.getChildren().add(verMapa);
        vboxcontainer.getChildren().add(item);
        vboxcontainer.getChildren().add(hBoxContainer);
        root.getChildren().addAll(vboxcontainer);

        setScene(new Scene(root));
        root.getStylesheets().add("assets/Vendedor_Items.css");

    }

    void AnularVentaAction(ActionEvent actionEvent) {
        String answer = "";
        ButtonType buttonYes = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType buttonNO = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.WARNING,
                 "¿Esta seguro que desea anular la venta?",
                buttonYes,
                buttonNO);

        alert.setTitle("Confirmar anulación de venta");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(buttonYes).equals("Si")) {
            answer = "Y";
        } else if (result.orElse(buttonNO).equals("No")) {
            answer = "N";
        }

    }

    void verMapaAction(ActionEvent actionEvent) {
        VerMapa vm = new VerMapa();
        new StageDecoratorX(vm);
        vm.show();
    }
}
