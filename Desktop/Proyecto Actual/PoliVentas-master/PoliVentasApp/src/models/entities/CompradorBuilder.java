/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.entities.Comprador;

/**
 *
 * @author miguelps
 */
public class CompradorBuilder extends UsuarioBuilder<CompradorBuilder>{

    @Override
    public Comprador build() {
        return new Comprador(this);
    }
    
}
