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
public class PagoEfectivo extends MetodoPago{
   

    public PagoEfectivo(CompradorServiceDB db) {
        this.db = db;
    }

    @Override
    public Boolean confirmar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
