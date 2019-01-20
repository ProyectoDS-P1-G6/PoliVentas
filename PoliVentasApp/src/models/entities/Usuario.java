/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;


import java.util.LinkedList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.joda.money.Money;

/**
 *
 * @author Usuario
 */
public class Usuario {
    protected Integer cedula;
    protected String nombres;
    protected String apellidos;
    protected ContactInfo contactInfo;
    protected String direccion;
    protected Integer matricula;
    protected Money saldo;

    Rol rol;

    private boolean isLogged;

    public Usuario() {
    }

    
    public Usuario(UsuarioBuilder builder) {
        this.cedula = builder.cedula;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
        this.contactInfo = builder.contactInfo;
        this.direccion = builder.direccion;
        this.matricula = builder.matricula;
        this.saldo = builder.saldo;
    }


    public String getNombres() {
        return nombres;
    }

    public Usuario setNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    
    
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public boolean comprobarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rol getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", contactInfo=" + contactInfo + ", direccion=" + direccion + ", matricula=" + matricula + ", rol=" + rol + ", isLogged=" + isLogged + '}';
    }
    
     
    
    static public class ContactInfo{
        InternetAddress email;
        Integer telefono;
        List<Integer> telefonosEmergencia;
        boolean usaWhatsapp;

        public ContactInfo(String email, Integer telefono, boolean usaWhatsapp) {
            try {
                this.email = new InternetAddress(email);
                this.email.validate();
            } catch (AddressException e) { 
                System.out.println(e.getMessage());
            }
            this.telefono = telefono;
            this.telefonosEmergencia = new LinkedList<>();
            this.usaWhatsapp = usaWhatsapp;
        }

        @Override
        public String toString() {
            return "ContactInfo{" + "email=" + email + ", telefono=" + telefono + ", telefonosEmergencia=" + telefonosEmergencia + ", usaWhatsapp=" + usaWhatsapp + '}';
        } 
    }

}
