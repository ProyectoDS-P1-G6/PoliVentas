/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.CreationalUsers;

import models.Calificacion;

/**
 *
 * @author Usuario
 */
public class VendedorModel extends CompradorModel{
    protected Calificacion scoreVendedor;

    public VendedorModel() {
    }

    public VendedorModel(Calificacion scoreVendedor) {
        this.scoreVendedor = scoreVendedor;
    }
    
    
    

    
    
}
