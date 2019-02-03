/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.items;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Articulo;
import services.AdministradorServiceDB;

/**
 *
 * @author Usuario
 */
public class ArticuloItemAdm extends Item{
    Articulo articulo;
    VBox opciones;
    JFXButton editar;
    JFXButton eliminar;
    AdministradorServiceDB db = new AdministradorServiceDB();
    public ArticuloItemAdm(Articulo art) {
        super();
        this.articulo = art;
        nombre_producto.setText(articulo.getNombre());
        nombre_vendedor.setText(articulo.getVendedor().getNombres() +" "+ articulo.getVendedor().getApellidos());
        precio.setText(articulo.getPrecio().toString());
        icon.setImage(articulo.getIcon());

        Label numero_busquedas = new Label("Busquedas: "+articulo.getNumero_busquedas().toString());
        description.getChildren().addAll(nombre_producto,nombre_vendedor,precio, numero_busquedas);
        description.setAlignment(Pos.CENTER_LEFT);
        description.setPrefSize(400, 90);
        
        opciones = new VBox(3);
        opciones.setAlignment(Pos.CENTER);
        opciones.setPrefWidth(100);
        
        editar = new JFXButton("Editar");
        editar.setStyle("-fx-background-color: #02B58F");
        editar.getStyleClass().add("colorlabel");
        eliminar = new JFXButton("Eliminar");
        eliminar.setStyle("-fx-background-color: #b52902");
        eliminar.getStyleClass().add("colorlabel");
        editar.setPrefWidth(opciones.getPrefWidth());
        eliminar.setPrefWidth(opciones.getPrefWidth());
        opciones.getChildren().addAll(editar,eliminar);
        
        content.getChildren().addAll(icon, description,opciones);
        
        btnEliminarAction((EventHandler<ActionEvent>) new eventoEliminarProducto(db));
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
    
    public Articulo obtenerArticulo(){
        return this.articulo;
    }
    public String getPrecio() {
        return precio.getText();
    }
    public void btnEliminarAction(EventHandler<ActionEvent> event){
        eliminar.setOnAction(event);
    }
    public void btnEditarAction(EventHandler<ActionEvent> eventHandler){
        editar.setOnAction(eventHandler);
    }
    class eventoEliminarProducto implements EventHandler<ActionEvent>{
        AdministradorServiceDB dbl;
        public eventoEliminarProducto(AdministradorServiceDB db) {
            this.dbl = db;
        }
        
        @Override
        public void handle(ActionEvent event){
            
            String botones[]={"Confirmar","Cancelar"};
            
            int seleccion = JOptionPane.showOptionDialog(new JFrame(),"¿Está seguro de eliminar este producto?","",0,0,null,botones,this);
            if(seleccion==JOptionPane.YES_OPTION){
                dbl.deleteProducto(obtenerArticulo());
                System.out.println("Producto " +obtenerArticulo().getNombre()+" "+obtenerArticulo().getIcon()+ " eliminado");
            }else if(seleccion==JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "Operacion Cancelada");
            }
        }
    }
}
