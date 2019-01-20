/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.entities.Comprador;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 *
 * @author miguelps
 */
public class PedidoBuilder{
        
        Integer id;
        Articulo articulo;
        Comprador comprador;
        Estado estado;
        Money total;
        Integer cantidad;
        Float descuento;
        Date fecha;
        
        public PedidoBuilder setId(Integer idx) {
            this.id = idx;
            return this;
        }

        public PedidoBuilder setArticulo(Articulo articulox) {
            this.articulo = articulox;
            return this;
        }

        public PedidoBuilder setComprador(Comprador compradorx) {
           this.comprador = compradorx;
           return this;
        }

        public PedidoBuilder setEstado(Estado estadox) {
            this.estado = estadox;
            return this;
        }

        public PedidoBuilder setTotal(Double totalx) {
            this.total = Money.of(CurrencyUnit.USD, totalx);
            return this;
        }

        public PedidoBuilder setCantidad(Integer cantidadx) {
            this.cantidad = cantidadx;
            return this;
        }

        public PedidoBuilder setDescuento(Float descuentox) {
            this.descuento = descuentox;
            return this;
        }

        public PedidoBuilder setFecha(Date fechax) {
            this.fecha = fechax;
            return this;
        }

        
        public Pedido build(){
            return new Pedido(this);
        }
    }