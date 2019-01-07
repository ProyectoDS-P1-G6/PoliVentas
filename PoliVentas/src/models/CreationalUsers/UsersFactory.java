/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.CreationalUsers;

/**
 *
 * @author Usuario
 */
public class UsersFactory {
    public UsuarioModel crearUsuario(String rol){
        if(rol == null){
         return null;
      }		
      if(rol.equalsIgnoreCase("COMPRADOR")){
         return new CompradorModel();
         
      } else if(rol.equalsIgnoreCase("VENDEDOR")){
         return new VendedorModel();
         
      } else if(rol.equalsIgnoreCase("ADMINISTRADOR")){
         return new AdministradorModel();
      }
        return null;
    }
}
