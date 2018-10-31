/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3isp.bien;


/**
 *
 * @author Palacios
 */
public class PokemonAgua extends Pokemon implements EntrenamientoPokemonBasico,EntrenamientoPokemonAgua{

    @Override
    public void aprenderAtaqueAgua() {
        this.getMovimientos().add("Agua");
    }
    
    @Override
    public void aprenderAtaqueCorte() {
        this.getMovimientos().add("Corte");
    }
    
}
