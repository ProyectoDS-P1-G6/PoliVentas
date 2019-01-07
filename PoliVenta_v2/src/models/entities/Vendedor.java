/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.Compra;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Vendedor extends Usuario {

    List<Comprador> compradores;
    List<Compra> ventas;
    final Rol rol = Rol.VENDEDOR;

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

    public List<Compra> getVentas() {
        return ventas;
    }

    public void setVentas(List<Compra> ventas) {
        this.ventas = ventas;
    }
}