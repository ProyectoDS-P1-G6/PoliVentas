package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Articulo;
import models.Estado;
import models.Pedido;


public class PedidoItem extends Item {

    Label estado;
    Label nombre_comprador;
    private Pedido pedido;

    public PedidoItem(Pedido pedido){

        super();
        this.pedido = pedido;
        Articulo articulo = pedido.getArticulo();
        nombre_producto.setText(articulo.getNombre());
        nombre_comprador = new Label();
        nombre_comprador.setText(pedido.getComprador().getNombres() +" "+ pedido.getComprador().getApellidos());
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

        description.getChildren().addAll(nombre_producto,nombre_comprador,precio, estado);
        icon = new ImageView(pedido.getArticulo().getIcon());
        icon.setFitWidth(60);
        icon.setFitHeight(60);
        
        content.getChildren().addAll(icon, description);
    }
    
    public Pedido getPedido(){
    return this.pedido;
    }
    
}
