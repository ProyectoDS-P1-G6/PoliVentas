package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.entities.Rol;

public class RegistroForm extends Stage {

    private final Scene scene;
    private final AnchorPane root;
    private final BorderPane registroPane;
    private final VBox container;
    private final HBox buttonPanel;
    private final Button buttonRegistrar;
    private final Button buttonClear;
    private final GridPane sharedPane;

    private final TextField cedula;
    private final TextField nombre;
    private final ComboBox<Rol> perfil;
    private final TextField apellidos;
    private final TextField telefono;
    private final ComboBox<String> Whatsapp;
    private final TextField email;
    private final TextField direccion;
    private final TextField matricula;

    private final Label cedulalabel;
    private final Label nombrelabel;
    private final Label perfillabel;
    private final Label apellidoslabel;
    private final Label telefonolabel;
    private final Label whatsapplabel;
    private final Label emaillabel;
    private final Label direccionlabel;
    private final Label matriculalabel;

    private final List<Node> nodos;
    

    public RegistroForm() {
        super();
        container = new VBox();
        buttonPanel = new HBox();
        registroPane = new BorderPane();
        sharedPane = new GridPane();

        buttonRegistrar = new Button("Registrar");
        buttonRegistrar.setMinWidth(100);
        buttonClear = new Button("Limpiar");
        buttonClear.setMinWidth(100);

        nombre = new TextField();
        nombre.getStyleClass().add("jfx-text-field");
        perfil = new ComboBox<>();
        perfil.setMinWidth(200);
        cedula = new TextField();
        cedula.getStyleClass().add("jfx-text-field");
        apellidos = new TextField();
        apellidos.getStyleClass().add("jfx-text-field");
        telefono = new TextField();
        telefono.getStyleClass().add("jfx-text-field");
        Whatsapp = new ComboBox<>();
        Whatsapp.getStyleClass().add("combo-box");
        Whatsapp.setMinWidth(200);
        email = new TextField();
        email.getStyleClass().add("jfx-text-field");
        direccion = new TextField();
        direccion.getStyleClass().add("jfx-text-field");
        matricula = new TextField();
        matricula.getStyleClass().add("jfx-text-field");
        
        nombrelabel= new Label("Nombre: ");
        nombrelabel.getStyleClass().add("text_style");
        cedulalabel= new Label("Cédula: ");
        cedulalabel.getStyleClass().add("text_style");
        perfillabel= new Label("Perfil: ");
        perfillabel.getStyleClass().add("text_style");
        apellidoslabel= new Label("Apellidos: ");
        apellidoslabel.getStyleClass().add("text_style");
        telefonolabel= new Label("Teléfono: ");
        telefonolabel.getStyleClass().add("text_style");
        whatsapplabel= new Label("WhatsApp: ");
        whatsapplabel.getStyleClass().add("text_style");
        emaillabel= new Label("E-mail: ");
        emaillabel.getStyleClass().add("text_style");
        direccionlabel= new Label("Dirección: ");
        direccionlabel.getStyleClass().add("text_style");
        matriculalabel= new Label("Matrícula: ");
        matriculalabel.getStyleClass().add("text_style");
        

        nodos = new ArrayList<>(15);
        nodos.addAll(Arrays.asList(nombre, apellidos,perfil, cedula , telefono, Whatsapp, email, direccion, matricula));

        //Dimensiones Ventana
        root = new AnchorPane();
        root.setPrefWidth(500);
        root.setPrefHeight(500);
        scene = new Scene(root);
        setScene(scene);
        setupView();
        setupControl();
    }

    private void setupView() {
        root.setPadding(new Insets(25, 25, 25, 25));
        getScene().getStylesheets().add("assets/registroform.css");
        sharedPane.setPadding(new Insets(30, 30, 30, 30));
        registroPane.setCenter(container);
        registroPane.setBottom(buttonPanel);
        registroPane.setPadding(new Insets(25, 25, 25, 25));
        container.setSpacing(20);
        
        sharedPane.addRow(0, nombrelabel,nodos.get(0));
        sharedPane.addRow(1, apellidoslabel,nodos.get(1));
        sharedPane.addRow(2, perfillabel,nodos.get(2));
        sharedPane.addRow(3, cedulalabel,nodos.get(3));
        sharedPane.addRow(4, telefonolabel,nodos.get(4));
        sharedPane.addRow(5, whatsapplabel,nodos.get(5));
        sharedPane.addRow(6, emaillabel,nodos.get(6));
        sharedPane.addRow(7, direccionlabel,nodos.get(7));
        sharedPane.addRow(8, matriculalabel,nodos.get(8));
        
        
        sharedPane.setPadding(new Insets(25, 25, 25, 25));
        sharedPane.setHgap(25);
        sharedPane.setVgap(15);
        container.getChildren().addAll(sharedPane);
        buttonPanel.setAlignment(Pos.BOTTOM_CENTER);

        buttonPanel.setSpacing(100);
        root.getStyleClass().add("bg_color");

        buttonRegistrar.getStyleClass().add("button_style");
        buttonClear.getStyleClass().add("button_style");

        perfil.getItems().addAll(Rol.COMPRADOR, Rol.VENDEDOR);
        perfil.getSelectionModel().selectFirst();
        
        Whatsapp.getItems().addAll("SI","NO");
        Whatsapp.getSelectionModel().selectFirst();

        setDisableGuardarEliminar(true);
        Label title = new Label("Formulario de Registro");
        title.getStyleClass().add("title");
        title.setLayoutX(100);
        title.setLayoutY(registroPane.getLayoutY() - 15);

        buttonPanel.getChildren().addAll(buttonRegistrar, buttonClear);
        registroPane.setLayoutX(60);
        root.getChildren().addAll(title, registroPane);

    }

    private void setupControl() {
        buttonClear.setOnAction(a -> {

            limpiarCampos();
        });

    }

    private void setDisableGuardarEliminar(boolean b) {
        //buttonGuardar.setDisable(b);
    }

    private void setupRegistrarTab() {
        buttonRegistrar.setOnAction(a -> {

        });

    }

    private void limpiarCampos() {

    }

    public Parent getRoot() {
        return root;
    }
}
