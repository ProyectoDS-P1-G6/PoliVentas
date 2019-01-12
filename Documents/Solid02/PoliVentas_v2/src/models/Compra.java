package models;

import models.entities.Comprador;
import models.entities.Vendedor;

import java.util.LinkedList;
import java.util.List;

public class Compra {
    private Integer id;
    private Comprador comprador;
    private Vendedor vendedor;
    private String estado;

    protected List<Articulo> articulos;

    protected Double total;
    protected Float descuento;

    public Compra(Comprador comprador, Vendedor vendedor) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        articulos = new LinkedList<>();
    }

    public boolean agregarArticulo(Articulo articulo){
        return true;
    }

    public boolean removerArticulo(Articulo articulo){
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }
}
