/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.Carrito;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Comprador extends Usuario {


    List<Carrito> compras;

    public Comprador(){
        this.rol = Rol.COMPRADOR;
    }

    public List<Carrito> getCompras() {
        return compras;
    }

    public void setCompras(List<Carrito> compras) {
        this.compras = compras;
    }
}
