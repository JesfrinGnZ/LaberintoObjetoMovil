/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.objetoMovil;

/**
 *
 * @author Jonathan
 */
public class Movimiento {
    //Tipo adelante=0, atras=1, arriba=2, abajo=3, diagonal=4
    public int tipo;
    
    //Distancia en centimetros
    public int distancia;
    
    //Angulo de 0 a 360;
    public int angulo;
    
    //Velocidad 1, 2, 3
    public int velocidad;
            
    
    public Movimiento(){
        
    }
    
    //INSTANCIAR MOVIMIENTO
    public Movimiento(String tipo1, String distancia1, String velocidad1, String angulo1){
        int tipo = Integer.parseInt(tipo1); 
        int distancia = Integer.parseInt(distancia1); 
        int velocidad = Integer.parseInt(velocidad1);
        int angulo = Integer.parseInt(angulo1);
        this.velocidad=velocidad;
        this.distancia=distancia;
        this.tipo=tipo;
        
        switch (tipo) {
            case 0:
                this.angulo=0;
                break;
            case 1:
                this.angulo=180;
                break;
            case 2:
                this.angulo=90;
                break;
            case 3:
                this.angulo=270;
                break;
            default:
                if(angulo<0){
                    this.angulo=360+angulo;
                }else{
                    this.angulo=angulo;
                }
                break;
        }
        
    }
}
