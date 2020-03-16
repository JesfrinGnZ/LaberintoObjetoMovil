/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.Matriz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author jesfrin
 */
public class ManejadorMatriz implements Serializable {

    public final static int LONGITUD_PANEL = 620;
    public final static int ALTITUD_PANEL = 620;
    public static final int LONGITUD_REALCM = 200;

    private Celda[][] matriz;
    private int numeroDeCuadros;

    public ManejadorMatriz(int numeroDeCuadros) {
        this.numeroDeCuadros = numeroDeCuadros;
        llenarMatriz();
    }

    private void llenarMatriz() {
        int n = LONGITUD_PANEL / numeroDeCuadros;
        int limiteSuperiorY;
        int limiteSuperiorX;
        this.matriz = new Celda[numeroDeCuadros][numeroDeCuadros];
        for (int x = 0; x < numeroDeCuadros; x++) {
            limiteSuperiorX = n * (x + 1);
            for (int y = 0; y < numeroDeCuadros; y++) {
                limiteSuperiorY = n * (y + 1);
                // System.out.println("Guardando:"+limiteInferior+":"+limiteSuperior);
                this.matriz[x][y] = new Celda(limiteSuperiorX, limiteSuperiorY, false, false, new Point(x, y));
            }
        }
    }

    public void pintarCuadrosDeMatriz(Graphics g) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                Celda celda = matriz[i][j];
                if (celda.estaPintado()) {
                    celda.pintarCelda(celda.getColor(), g, numeroDeCuadros);
                    System.out.println("Pintada:" + i + "," + j);
                } else {
                    celda.pintarCelda(Color.WHITE, g, numeroDeCuadros);
                }
            }
        }
    }

    private int averiguarEquivalenteEnMatriz(int numero) {
//        System.out.println("/****************************************/");
//        System.out.println("Numero:" + numero);
        int posEnMatriz;
        int relacion = LONGITUD_PANEL / numeroDeCuadros;
        if (numero % relacion != 0) {
            posEnMatriz = (numero / relacion) + 1;
        } else {
            posEnMatriz = (numero / relacion);
        }
//        System.out.println("Relacion:" + relacion);
//        System.out.println("Resto:" + numero % relacion);
//        System.out.println("Pos en matriz:" + posEnMatriz);
//        System.out.println("/*******************************************/\n\n");
        return posEnMatriz;

    }

    
    public boolean buscarSiPuntoEsPared(int x,int y){
        return buscarEnMatriz(x, y).estaPintado();
    }
    
    public Celda buscarEnMatriz(int x, int y) {
        int xM = averiguarEquivalenteEnMatriz(x);
        int yM = averiguarEquivalenteEnMatriz(y);
        System.out.println("X:" + xM + " Y:" + yM);
        return this.matriz[xM - 1][yM - 1];
    }

    public void pintarPared(int x, int y, Graphics g, Color color) {
        buscarEnMatriz(x, y).pintarCelda(color, g, numeroDeCuadros);
    }

    public void borrarPared(int x, int y, Graphics g) {
        buscarEnMatriz(x, y).pintarCelda(Color.WHITE, g, numeroDeCuadros);
    }

    public Celda[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Celda[][] matriz) {
        this.matriz = matriz;
    }

    public int getNumeroDeCuadros() {
        return numeroDeCuadros;
    }

    public void setNumeroDeCuadros(int numeroDeCuadros) {
        this.numeroDeCuadros = numeroDeCuadros;
    }

}
