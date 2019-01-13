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
public class UsersFactory {
    public Usuario crearUsuario(String rol){
        if(rol == null){
         return null;
      }		
      if(rol.equalsIgnoreCase("COMPRADOR")){
         return new Comprador();
         
      } else if(rol.equalsIgnoreCase("VENDEDOR")){
         return new Vendedor();
         
      } else if(rol.equalsIgnoreCase("ADMINISTRADOR")){
         return new Administrador();
      }
        return null;
    }
}
