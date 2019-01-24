/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.Image;
import models.entities.Vendedor;
import org.joda.money.Money;

/**
 *
 * @author Usuario
 */
public class Articulo {
    private Integer id;
    private String nombre;
    private String categoria;
    private String descripción;
    private Money precio;
    private Integer tiempo_max_entrega;
    private Image icon;
    Vendedor vendedor;
    Integer numero_busquedas;

    public Articulo(ArticuloBuilder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.categoria = builder.categoria;
        this.descripción = builder.descripción;
        this.precio = builder.precio;
        this.tiempo_max_entrega = builder.tiempo_max_entrega;
        this.vendedor = builder.vendedor;
        this.icon = builder.icon;
        this.numero_busquedas = builder.numero_busquedas;
    }

    public Integer getNumero_busquedas() {
        return numero_busquedas;
    }
    
    public Image getIcon() {
        return icon;
    }

    public String getDescripción() {
        return descripción;
    }

    public Integer getTiempo_max_entrega() {
        return tiempo_max_entrega;
    }
    
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Money getPrecio() {
        return precio;
    }

    public void setPrecio(Money precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", descripci\u00f3n=" + descripción + ", precio=" + precio + ", tiempo_max_entrega=" + tiempo_max_entrega + ", icon=" + icon + ", vendedor=" + vendedor + '}';
    }
    
    
    
    
}
