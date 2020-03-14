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
public class Celda implements Serializable {

    private int limiteSuperiorX, limiteSuperiorY;
    private boolean estaPintado;
    private boolean estaVisitado;
    private Point posicionEnMatriz;
    private Color color;

    public Celda(int limiteSuperiorX, int limiteSuperiorY, boolean estaPintado, boolean estaVisitado, Point posicionEnMatriz) {
        this.limiteSuperiorX = limiteSuperiorX;
        this.limiteSuperiorY = limiteSuperiorY;
        this.estaPintado = estaPintado;
        this.estaVisitado = estaVisitado;
        this.posicionEnMatriz = posicionEnMatriz;
    }

    public void pintarCelda(Color color, Graphics g,int numeroDeCuadros) {
        this.estaPintado = true;
        this.color=color;
        g.setColor(color);
        System.out.println("LimiteSuperiorX:" + limiteSuperiorX);
        System.out.println("LimiteSuperiorY:" + limiteSuperiorY);
        int comodinX = limiteSuperiorX - (ManejadorMatriz.LONGITUD_PANEL / numeroDeCuadros - 1);
        int comodinY = limiteSuperiorY - (ManejadorMatriz.LONGITUD_PANEL / numeroDeCuadros - 1);
        for (int x = comodinX; x <= limiteSuperiorX; x++) {
            for (int y = comodinY; y <= limiteSuperiorY; y++) {
                g.drawLine(x, y, x, y);
            }
        }
    }

    public void borrarCelda(Color color, Graphics g,int numeroDeCuadros) {
        this.estaPintado = false;
        g.setColor(color);
        int comodinX = limiteSuperiorX - (ManejadorMatriz.LONGITUD_PANEL / numeroDeCuadros - 1);
        int comodinY = limiteSuperiorY - (ManejadorMatriz.LONGITUD_PANEL / numeroDeCuadros - 1);
        for (int x = comodinX; x <= limiteSuperiorX; x++) {
            for (int y = comodinY; y <= limiteSuperiorY; y++) {
                g.drawLine(x, y, x, y);
            }
        }
    }

    public int getLimiteInferior() {
        return limiteSuperiorX;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteSuperiorX = limiteInferior;
    }

    public int getLimiteSuperior() {
        return limiteSuperiorY;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperiorY = limiteSuperior;
    }

    public boolean estaPintado() {
        return estaPintado;
    }

    public void setEstaPintado(boolean estaPintado) {
        this.estaPintado = estaPintado;
    }

    public boolean estaVisitado() {
        return estaVisitado;
    }

    public void setEstaVisitado(boolean estaVisitado) {
        this.estaVisitado = estaVisitado;
    }

    public Point getPosicionEnMatriz() {
        return posicionEnMatriz;
    }

    public void setPosicionEnMatriz(Point posicionEnMatriz) {
        this.posicionEnMatriz = posicionEnMatriz;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
