/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Leche.LecheDeslactosada;
import Leche.LecheDescremada;

/**
 *
 * @author Pedro Mendoza
 */
public class CambioLeche {
    
    public void cambiarTipoLecheDescremada(){
        LecheDescremada ldescrem= new LecheDescremada();
        ldescrem.usar();
    }
    
    public void cambiarTipoLecheDeslactosada(){
        LecheDeslactosada ldeslac= new LecheDeslactosada();
        ldeslac.usar();
    }    
}
