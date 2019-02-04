package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Articulo;
import models.Estado;
import models.Pedido;

public class PedidoItem extends Item {

    Label estado;
    private Pedido pedido;

    public PedidoItem(Pedido pedido) {

        super();
        this.pedido=pedido;
        Articulo articulo = pedido.getArticulo();
        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() + " " + articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());

        estado = new Label();
        estado.getStyleClass().add("estado-label");
        Estado e = pedido.getEstado();
        switch (e) {
            case ANULADO:
                estado.setStyle("-fx-text-fill: red;");
                break;
            case ENVIADO:
                estado.setStyle("-fx-text-fill: blue;");
                break;
            case ENTREGADO:
                estado.setStyle("-fx-text-fill: black;");
                break;
            case PENDIENTE:
                estado.setStyle("-fx-text-fill: green;");
                break;
        }
        estado.setText(e.toString());

        description.getChildren().addAll(nombre_producto, nombre_vendedor, precio, estado);
        icon.setImage(articulo.getIcon());

        content.getChildren().addAll(icon, description);
    }

    public Pedido getPedido() {
        return this.pedido;
    }
}
