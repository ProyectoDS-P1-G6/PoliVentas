package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Articulo;
import models.Estado;
import models.*;

public class VendidoItem extends Item{

        Label estado;
        Label cantidad;
        Label numero;
        
        
    public VendidoItem(Venta venta){

        super();
        Articulo articulo = venta.getArticulo();
        cantidad = new Label();
        numero = new Label();
        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());

        estado = new Label();
        estado.getStyleClass().add("estado-label");
        Estado e = venta.getEstado();
        switch (e){
            case ANULADO:
                estado.setStyle("-fx-text-fill: red;");break;
            case ENTREGADO:
                estado.setStyle("-fx-text-fill: blue;");break;            
            case PENDIENTE:
                estado.setStyle("-fx-text-fill: green;");break;
        }
        estado.setText(e.toString());
        numero.setText("No ventas: "+Integer.toString(venta.getId()));
        cantidad.setText(Integer.toString(venta.getCantidad()));
        description.getChildren().addAll(numero,nombre_producto,nombre_vendedor,precio,cantidad, estado);
        content.setOnMouseClicked(event -> {

        });

        content.getChildren().addAll(new ImageView(new Image("file:src/assets/env1.png")), description);
        content.getStylesheets().add("assets/Vendedor_Items.css");

    }
}
