/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.Pedido;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Comprador extends Usuario {


    List<Pedido> compras;

    public Comprador(){
        super();
        this.rol = Rol.COMPRADOR;
    }

    public List<Pedido> getCompras() {
        return compras;
    }

    public void setCompras(List<Pedido> compras) {
        this.compras = compras;
    }
}
