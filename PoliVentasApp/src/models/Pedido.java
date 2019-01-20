package models;

import java.util.Date;
import models.entities.Comprador;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;


public class Pedido {
    Integer id;
    Articulo articulo;
    Comprador comprador;
    Estado estado;
    Money total;
    Integer cantidad;
    Float descuento;
    Date fecha;
    
    
    public Pedido(PedidoBuilder builder) {
        
        id = builder.id;
        articulo = builder.articulo;
        comprador = builder.comprador;
        estado = builder.estado;
        total = builder.total;
        cantidad = builder.cantidad;
        descuento = builder.descuento;
        fecha = builder.fecha;
      
    }

    public Integer getId() {
        return id;
    }


    public Comprador getComprador() {
        return comprador;
    }

    public Money getTotal() {
        return total;
    }


    public Articulo getArticulo() {
        return articulo;
    }

    public Estado getEstado() {
        return estado;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public Float getDescuento() {
        return descuento;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", articulo=" + articulo + ", comprador=" + comprador + ", estado=" + estado + ", total=" + total + ", cantidad=" + cantidad + ", descuento=" + descuento + ", fecha=" + fecha + '}';
    }
    
    
    
}
