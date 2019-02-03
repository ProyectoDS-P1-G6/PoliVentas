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
    private Boolean estado;

    public Articulo() {

    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public void setTiempo_max_entrega(Integer tiempo_max_entrega) {
        this.tiempo_max_entrega = tiempo_max_entrega;
    }

    public void setNumero_busquedas(Integer numero_busquedas) {
        this.numero_busquedas = numero_busquedas;
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
    
    public void setIcon(String icon_path) {
        this.icon = new Image("file:static"+ icon_path);
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

    public void setPrecio(Double precio) {
        this.precio = Money.of(CurrencyUnit.USD, precio);
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return nombre + "  "+ categoria +"  " +precio ;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
