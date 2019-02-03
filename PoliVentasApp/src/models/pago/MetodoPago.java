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
public abstract class MetodoPago{
    CompradorServiceDB db;
    
    public void transferir(Pedido pedido) {
        db.registrarPedido(pedido);
    }
    
    public abstract Boolean confirmar();
}
