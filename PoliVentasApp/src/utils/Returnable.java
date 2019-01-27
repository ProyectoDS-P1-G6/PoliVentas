/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author miguelps
 */
public interface Returnable {
    
    void setPreviousWindow(Returnable previous);
    void showWindow();
    
}
