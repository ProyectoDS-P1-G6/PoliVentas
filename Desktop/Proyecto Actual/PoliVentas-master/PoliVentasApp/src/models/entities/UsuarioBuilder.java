/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import models.entities.Usuario;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;


/**
 *
 * @author miguelps
 */

/**
 * Extend this for Mammal subtype builders.
 */
abstract class UsuarioBuilder<B extends UsuarioBuilder<B>> {
    protected Integer cedula;
    protected String nombres;
    protected String apellidos;
    protected Usuario.ContactInfo contactInfo;
    protected String direccion;
    protected Integer matricula;
    protected Money saldo;

    private boolean isLogged;

    public B setNombres(String nombres) {
        this.nombres = nombres;
        return self();
    }


    public B setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return self();
    }

    public B setContactInfo(String email, Integer telefono, boolean usaWhatsapp){
        this.contactInfo = new Usuario.ContactInfo(email, telefono, usaWhatsapp);
        return self();
    }


    public B setDireccion(String direccion) {
        this.direccion = direccion;
        return self();
    }



    public B setCedula(Integer cedula) {
        this.cedula = cedula;
        return self();
    }



    public B setMatricula(Integer matricula) {
        this.matricula = matricula;
        return self();
    }

    public B setSaldo(Double saldo) {
        this.saldo = Money.of(CurrencyUnit.USD, saldo);
        return self();
    }
    
    
    abstract public Usuario build();

    @SuppressWarnings("unchecked")
    final B self() {
        return (B) this;
    }
}

