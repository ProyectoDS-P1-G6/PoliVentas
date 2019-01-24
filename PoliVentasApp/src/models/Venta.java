/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.entities.*;
import org.joda.money.Money;
import views.items.PendienteItem;

/**
 *
 * @author carlasanchez
 */
public class Venta {
    Integer id;
    Articulo articulo;
    Vendedor vendedor;
    Estado estado;
    Money total;
    Integer cantidad;
    Float descuento;
    Date fecha;
    
    public Venta(){
    ;}
    public Venta(Integer id, Articulo articulo, Vendedor vendedor,Estado estado,Money Total,Integer cantidad,Date fecha) {
        this.id = id;
        this.articulo = articulo;
        this.vendedor = vendedor;
        this.estado = estado;
        this.total = total;
        this.cantidad = cantidad;
        this.fecha = fecha;
      
    }

    public Integer getId() {
        return id;
    }


    public Vendedor getVendedor() {
        return vendedor;
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

    public Venta setId(Integer id) {
        this.id = id;
                return this;

    }

    public Venta setArticulo(Articulo articulo) {
        this.articulo = articulo;
        return this;
    }

    public Venta setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        return this;

    }

    public Venta setEstado(Estado estado) {
        this.estado = estado;
                return this;

    }

    public Venta setTotal(Money total) {
        this.total = total;
                return this;

    }

    public Venta setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
                return this;

    }

    

    public Venta setFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }
    
   
    
    
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", articulo=" + articulo + ", vendedor=" + vendedor + ", estado=" + estado + ", total=" + total + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
    }
}
