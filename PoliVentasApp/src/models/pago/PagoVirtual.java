/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.pago;
import models.Pedido;
import services.CompradorServiceDB;

/**
 *
 * @author Usuario
 */
public class PagoVirtual extends MetodoPago {

    
    
    public PagoVirtual(CompradorServiceDB db) {
        this.db = db;
    }

    @Override
    public Boolean confirmar() {
        return true;
    }

    public Boolean checkSaldo() {
        return true;
    }

    

}
