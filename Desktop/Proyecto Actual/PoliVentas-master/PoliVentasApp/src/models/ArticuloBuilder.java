/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.Image;
import models.entities.Vendedor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 *
 * @author miguelps
 */
public class ArticuloBuilder {
    Integer id;
    String nombre;
    String categoria;
    String descripción;
    Money precio;
    Integer tiempo_max_entrega;
    Image icon;
    Vendedor vendedor;
    Integer numero_busquedas;

    public ArticuloBuilder setTiempo_max_entrega(Integer tiempo_max_entrega) {
        this.tiempo_max_entrega = tiempo_max_entrega;
        return this;
    }

    public ArticuloBuilder setNumero_busquedas(Integer numero_busquedas) {
        this.numero_busquedas = numero_busquedas;
        return this;
    }

    
    public ArticuloBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ArticuloBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ArticuloBuilder setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ArticuloBuilder setDescripción(String descripción) {
        this.descripción = descripción;
        return this;
    }

    public ArticuloBuilder setPrecio(Double precio) {
        this.precio = Money.of(CurrencyUnit.USD, precio);
        return this;
    }

    public ArticuloBuilder setIcon(String icon_path) {
        this.icon = new Image("file:static"+ icon_path);
        return this;
    }

    public ArticuloBuilder setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        return this;
    }
    
    
    
    public Articulo build(){
        return new Articulo(this);
    }
    
}
