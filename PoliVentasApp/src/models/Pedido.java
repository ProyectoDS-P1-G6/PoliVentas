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
    
    
    public Pedido() {
      
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setTotal(Double total) {
        this.total = Money.of(CurrencyUnit.USD, total);
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
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
        return " articulo=" + articulo + ", comprador=" + comprador + ", estado=" + estado + ", total=" + total + ", cantidad=" + cantidad;
    }
    
    
    
}
