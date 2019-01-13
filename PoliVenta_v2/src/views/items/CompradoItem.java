package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Articulo;
import models.Estado;
import models.Pedido;

public class CompradoItem extends Item {

    Label estado;

    public CompradoItem(Pedido pedido){

        super();
        Articulo articulo = pedido.getArticulo();
        nombre.setText(articulo.getNombre());
        vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());

        estado = new Label();
        estado.getStyleClass().add("estado-label");
        Estado e = pedido.getEstado();
        switch (e){
            case ANULADO:
                estado.setStyle("-fx-text-fill: red;");break;
            case ENVIADO:
                estado.setStyle("-fx-text-fill: blue;");break;
            case ENTREGADO:
                estado.setStyle("-fx-text-fill: black;");break;
            case PENDIENTE:
                estado.setStyle("-fx-text-fill: green;");break;
        }
        estado.setText(e.toString());

        description.getChildren().addAll(nombre,vendedor,precio, estado);

        content.setOnMouseClicked(event -> {

        });

        content.getChildren().addAll(new ImageView(new Image("file:src/assets/env1.png")), description);
    }
}
