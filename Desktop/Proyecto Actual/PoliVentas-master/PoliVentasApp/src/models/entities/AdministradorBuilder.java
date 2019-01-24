/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.entities.Administrador;

/**
 *
 * @author miguelps
 */
public class AdministradorBuilder extends UsuarioBuilder<AdministradorBuilder> {
    
    @Override
    public Administrador build() {
        return new Administrador(this);
    }
}
