/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import Postres.*;
import Procesos.*;
import Leche.*;
import Otros.*;
/**
 *
 * @author djurado
 */
public class Sistema {
    public static void main(String [ ] args){
        // Producir un helado de vainilla y una torta de chocolate, 
        // a ambos agregarles CREMA y FRUTILLAS
        
        // Producir Helado
        Helado helado_vainilla = new Helado("Vainilla");
        OperacionesAderezo.anadirAderezoHelado(helado_vainilla, Aderezo.CREMA);
        OperacionesAderezo.anadirAderezoHelado(helado_vainilla, Aderezo.FRUTILLA);
        System.out.println(helado_vainilla);
        
        // Producir Pastel
        Pastel pastel_chocolate = new Pastel("Chocolate");
        OperacionesAderezo.quitarAderezoPastel(pastel_chocolate, Aderezo.CREMA);
        OperacionesAderezo.anadirAderezoPastel(pastel_chocolate, Aderezo.FRUTILLA);
        System.out.println(pastel_chocolate);
        
        
    }
}
