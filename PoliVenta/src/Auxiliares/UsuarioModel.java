/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

/**
 *
 * @author Usuario
 */
public abstract class UsuarioModel {
    private String rol;
    private String nombres;
    private String apellidos;
    private String email;
    private String direccion;
    private String cedula;
    private String matricula;
    private int telefono;
    private boolean whatsapp;
    
    abstract public void busqueda();
        
    
}
