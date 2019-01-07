/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

/**
 *
 * @author Usuario
 */
public class Administrador extends Usuario {

    Rol actualRol = Rol.ADMIN;

    public boolean cambiarRol(Rol rol){

        if(rol == Rol.ADMIN)
            return false;

        this.rol = rol;
        return true;
    }
}
