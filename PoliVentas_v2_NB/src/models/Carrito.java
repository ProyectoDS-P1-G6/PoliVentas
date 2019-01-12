package models;

import models.entities.Comprador;
import org.joda.money.Money;
import utils.Constants;

import java.util.LinkedList;
import java.util.List;

public class Carrito {
    private Integer id;
    private Comprador comprador;
    Money total;

    List<Pedido> pedidos;
    Float descuento;

    public Carrito(Comprador comprador) {
        this.comprador = comprador;
        pedidos = new LinkedList<>();
        total = Money.parse("USD 0.0");
    }

    public boolean agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        total = total.plus(Money.of(Constants.USD, pedido.articulo.getPrecio().getAmount()));
        return true;
    }

    public boolean removerArticulo(Pedido pedido){
        pedidos.remove(pedido);
        total = total.minus(Money.of(Constants.USD, pedido.articulo.getPrecio().getAmount()));
        return true;
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

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    class Pedido{
        Articulo articulo;

        private String estado;
        Integer cantidad;

        public Pedido(Articulo articulo, Integer cantidad) {
            this.articulo = articulo;
            this.cantidad = cantidad;
        }

        public Articulo getArticulo() {
            return articulo;
        }

        public Integer getCantidad() {
            return cantidad;
        }
    }

}
