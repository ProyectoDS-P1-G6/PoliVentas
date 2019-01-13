package models;

import models.entities.Comprador;
import org.joda.money.Money;
import utils.Constants;

import java.util.LinkedList;
import java.util.List;

public class Pedido {
    private Integer id;
    private Comprador comprador;
    Money total;

    Articulo articulo;

    private Estado estado;
    Integer cantidad;

    Float descuento;

    public Pedido(Comprador comprador, Articulo articulo, Integer cantidad) {
        this.comprador = comprador;
        this.articulo = articulo;
        this.cantidad = cantidad;
        total = Money.parse("USD 0.0");
        total = total.plus(articulo.getPrecio());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }
}
