/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;


import javax.xml.registry.JAXRException;
import javax.xml.registry.infomodel.EmailAddress;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Usuario {
    private Integer id;
    private String nombres;
    private String apellidos;
    private ContactInfo contactInfo;
    private String direccion;
    private Integer cedula;
    private String matricula;

    Rol rol;

    private boolean isLogged;


    public class ContactInfo{
        private EmailAddress email;
        private Integer telefono;
        private List<Integer> telefonosEmergencia;
        private boolean usaWhatsapp;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean setEmail(String email){
        try {
            this.contactInfo.email.setAddress(email);
        } catch (JAXRException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTelefono() {
        return contactInfo.telefono;
    }

    public void setTelefono(int telefono) {
        this.contactInfo.telefono = telefono;
    }

    public boolean usaWhatsapp() {
        return contactInfo.usaWhatsapp;
    }

    public void setUsaWhatsapp(boolean usaWhatsapp) {
        this.contactInfo.usaWhatsapp = usaWhatsapp;
    }

    public boolean comprobarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rol getRol() {
        return rol;
    }

    public Integer getId() {
        return id;
    }
}
