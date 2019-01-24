package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import models.Articulo;

public class SearchItem extends Item{


    public SearchItem(Articulo articulo) {
        super();

        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());
        
        Label numero_busquedas = new Label("Busquedas: "+articulo.getNumero_busquedas().toString());
        description.getChildren().addAll(nombre_producto,nombre_vendedor,precio, numero_busquedas);

        content.getChildren().addAll(new ImageView(articulo.getIcon()), description);
    }
}
