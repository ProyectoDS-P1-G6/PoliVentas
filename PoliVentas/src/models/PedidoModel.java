package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import models.CreationalUsers.CompradorModel;
import java.util.ArrayList;
import java.util.Date;
import models.ArticuloModel;
import models.Estado;

/**
 *
 * @author Usuario
 */
public class PedidoModel {
    protected Date fecha;
    protected ArticuloModel producto;
    protected int cantidad;
    protected CompradorModel comprador;
    protected double costoTotalPedido;
    protected Estado estado;

    public PedidoModel(Date fecha, ArticuloModel producto, int cantidad, CompradorModel comprador, double costoTotalPedido, Estado estado) {
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        this.comprador = comprador;
        this.costoTotalPedido = costoTotalPedido;
        this.estado = estado;
    }
    
    public PedidoModel() {
    }
    
    public ArticuloModel getProducto() {
        return producto;
    }

    public void setProducto(ArticuloModel producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CompradorModel getComprador() {
        return comprador;
    }

    public void setComprador(CompradorModel comprador) {
        this.comprador = comprador;
    }

    public double getCostoTotalPedido() {
        return costoTotalPedido;
    }

    public void setCostoTotalPedido(double costoTotalPedido) {
        this.costoTotalPedido = costoTotalPedido;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
