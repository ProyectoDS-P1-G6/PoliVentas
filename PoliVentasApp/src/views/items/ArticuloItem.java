package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import models.Articulo;

public class ArticuloItem extends Item{

    public ArticuloItem(Articulo articulo) {
        super();
        

        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());
        icon.setImage(articulo.getIcon());

        Label numero_busquedas = new Label("Busquedas: "+articulo.getNumero_busquedas().toString());
        description.getChildren().addAll(nombre_producto,nombre_vendedor,precio, numero_busquedas);

        content.getChildren().addAll(icon, description);
    }

    public Image getIcon() {
        return icon.getImage();
    }

    public String getNombre_producto() {
        return nombre_producto.getText();
    }

    public String getNombre_vendedor() {
        return nombre_vendedor.getText();
    }

    public String getPrecio() {
        return precio.getText();
    }
 
    
}
