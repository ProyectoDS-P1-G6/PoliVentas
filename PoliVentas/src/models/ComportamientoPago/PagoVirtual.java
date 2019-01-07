/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.ComportamientoPago;

/**
 *
 * @author Usuario
 */
public class PagoVirtual implements MetodoPago {
    private double saldo;
    

    @Override
    public void pagar(double monto) {
        saldo=saldo-monto;
    }
    
    public void recargar(double monto){
        saldo=saldo+monto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}