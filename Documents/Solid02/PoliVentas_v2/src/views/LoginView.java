package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Stage {

    Pane root;

    private JFXTextField fieldUsuario;
    private JFXPasswordField fieldContrasena;
    private JFXButton loginButton;
    Label singUpLabel;
    Label statusMessage;

    public LoginView(){
        super();
        fieldUsuario    = new JFXTextField();
        fieldUsuario.setPromptText("Usuario");
        fieldContrasena = new JFXPasswordField();
        fieldContrasena.setPromptText("Contraseña");
        loginButton     = new JFXButton("Log In");
        loginButton.getStyleClass().add("loginButton");
        singUpLabel = new Label("Sign Up");
        singUpLabel.getStyleClass().add("singUpLabel");
        singUpLabel.setLayoutX(250);
        singUpLabel.setLayoutY(220);

        statusMessage = new Label();
        statusMessage.setLayoutX(180);
        statusMessage.setLayoutY(190);

        ImageView userIcon = new ImageView(new Image("file:src/assets/user.png"));
        userIcon.setFitWidth(25);
        userIcon.setFitHeight(25);
        userIcon.getStyleClass().add("userIcon");
        ImageView passIcon = new ImageView(new Image("file:src/assets/pass.png"));
        passIcon.setFitWidth(25);
        passIcon.setFitHeight(25);
        passIcon.getStyleClass().add("passIcon");

        GridPane fields = new GridPane();
        fields.add(userIcon,0,0);
        fields.add(fieldUsuario, 1,0);
        fields.add(passIcon,0,1);
        fields.add(fieldContrasena, 1,1);
        fields.getStyleClass().add("form_gridpane");
        fields.setHgap(5);
        fields.setVgap(5);

        VBox form = new VBox(10);
        form.getChildren().addAll(fields, loginButton);
        form.setAlignment(Pos.CENTER);
        form.setMargin(fields, new Insets(50,70,30,70));
        form.setMargin(loginButton, new Insets(0,0,70,0));

        root = new Pane();
        root.getChildren().addAll(form, singUpLabel, statusMessage);
        root.getStyleClass().add("loginView");

        setScene(new Scene(root));
        getScene().getStylesheets().add("assets/loginView.css");
    }

    public void addLoginAction(EventHandler<ActionEvent> eventHandler){
        loginButton.setOnAction(eventHandler);
    }

    public void addLogUpAction(EventHandler<MouseEvent> eventHandler){
        singUpLabel.setOnMouseClicked(eventHandler);
    }

    public String getUsuarioInput(){
        return fieldUsuario.getText();
    }

    public String getContrasenaInput(){
        return fieldContrasena.getText();
    }

    public void setStatusMessage(String msg){
        statusMessage.setText(msg);
    }
    public void clearMessageStatus(){
        statusMessage.setText("");
    }

    public Pane getRoot(){
        return this.root;
    }
}
