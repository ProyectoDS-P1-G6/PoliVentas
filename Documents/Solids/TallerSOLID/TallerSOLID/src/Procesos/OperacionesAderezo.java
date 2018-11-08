/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Otros.Aderezo;
import Postres.Pastel;
import Postres.Helado;

/**
 *
 * @author Pedro Mendoza
 */
public class OperacionesAderezo {
    
    public static void anadirAderezoHelado(Helado helado,Aderezo aderezo){
        helado.getAderezos().add(aderezo);
    }
    
    public static void quitarAderezoHelado(Helado helado,Aderezo aderezo){
        helado.getAderezos().remove(aderezo);
    }
    
    public static void anadirAderezoPastel(Pastel pastel,Aderezo aderezo){
        pastel.getAderezos().add(aderezo);
    }
    
    public static void quitarAderezoPastel(Pastel pastel,Aderezo aderezo){
        pastel.getAderezos().remove(aderezo);
    }
    
}
