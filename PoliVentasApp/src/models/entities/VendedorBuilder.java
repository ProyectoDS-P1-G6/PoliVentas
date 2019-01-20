/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.entities.Vendedor;

/**
 *
 * @author miguelps
 */
public class VendedorBuilder extends UsuarioBuilder<VendedorBuilder> {
    
    @Override
    public Vendedor build() {
        return new Vendedor(this);
    }
}