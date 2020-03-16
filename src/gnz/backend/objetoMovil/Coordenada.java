/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.objetoMovil;

import gnz.backend.Matriz.Run;

/**
 *
 * @author Jonathan
 */
public class Coordenada {
    int x;
    int y;
    
    
    public Coordenada(int x, int y){
        this.x=x*Run.MULT;
        this.y=y*Run.MULT;
    }
    
}
