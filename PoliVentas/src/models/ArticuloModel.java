/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.CreationalUsers.VendedorModel;

/**
 *
 * @author Usuario
 */
public class ArticuloModel {
    protected String nomArticulo;
    protected String cateArticulo;
    protected double precioArticulo;
    protected String maxTimeDeliver;
    protected Calificacion calificacion;
    protected VendedorModel vendedor;

    public ArticuloModel() {
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public String getCateArticulo() {
        return cateArticulo;
    }

    public void setCateArticulo(String cateArticulo) {
        this.cateArticulo = cateArticulo;
    }

    public double getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(double precioArticulo) {
        this.precioArticulo = precioArticulo;
    }
    
    
}
