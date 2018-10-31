/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _1ocp.bien;

/**
 *
 * @author Usuario
 */
public class GenerarAtaque {
    private final Pokemon pokemon;

    public GenerarAtaque(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
       
    public void generarAtaque(final Ataque ataque){
        this.pokemon.setPorcentajeVida(this.pokemon.getPorcentajeVida()*ataque.ataque());
        }
    }


