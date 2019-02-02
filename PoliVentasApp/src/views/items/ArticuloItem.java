package views.items;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import models.Articulo;

public class ArticuloItem extends Item{

    Label nombre_vendedor;
    private Articulo articulo;
    
    public ArticuloItem(Articulo articulo) {
        super();
        this.articulo = articulo;

        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor = new Label();
        nombre_vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        nombre_vendedor.getStyleClass().add("vendedor-label");
        precio.setText(articulo.getPrecio().toString());
        icon = new ImageView(articulo.getIcon());
        icon.setFitWidth(60);
        icon.setFitHeight(60);
        
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
 
    public Articulo getArticulo(){
        return this.articulo;
    }
}
