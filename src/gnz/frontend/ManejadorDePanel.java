/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.frontend;

import gnz.backend.Matriz.ManejadorMatriz;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author jesfrin
 */
public class ManejadorDePanel {


    private ManejadorMatriz manMatriz;
    private FrameOM frame;
    private Color color;
    private Color copiaColor;
    private int numeroDeCuadros;
    private Graphics2D g;
    
    public ManejadorDePanel(FrameOM frame,int numeroDeCuadros) {
        this.frame = frame;
        this.color = Color.BLACK;
        this.copiaColor = Color.BLACK;
        this.numeroDeCuadros = numeroDeCuadros;
        this.manMatriz = new ManejadorMatriz(numeroDeCuadros);

    }

    public void pintarCuadricula(Graphics g) {
        g.setColor(Color.GRAY);
        int longitudDeCuadro = ManejadorMatriz.LONGITUD_PANEL / numeroDeCuadros;
        for (int x = 0; x <= ManejadorMatriz.LONGITUD_PANEL; x += longitudDeCuadro) {
            g.drawLine(x, 0, x, ManejadorMatriz.ALTITUD_PANEL);
        }
        for (int y = 0; y <= ManejadorMatriz.ALTITUD_PANEL; y += longitudDeCuadro) {
            g.drawLine(0, y, ManejadorMatriz.LONGITUD_PANEL, y);
        }
    }
    
    public void dibujarCuadriculaDeMatriz(ManejadorMatriz manMatriz,JPanel panel){
        this.manMatriz =manMatriz;
        this.manMatriz.pintarCuadrosDeMatriz(g);
        this.numeroDeCuadros=this.manMatriz.getNumeroDeCuadros();
        pintarCuadricula(g);
        
        
    }

    public void accionParaClick(MouseEvent evt) {
        g = (Graphics2D) ((JPanel) evt.getSource()).getGraphics();
        if (evt.getButton() == MouseEvent.BUTTON1) {
            this.manMatriz.pintarPared(evt.getX(), evt.getY(), g, color);
            System.out.println(evt.getX() + " " + evt.getY());
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            this.manMatriz.borrarPared(evt.getX(), evt.getY(), g);
            System.out.println("IZQUIERDO");
        }
        pintarCuadricula(g);
        double n = (ManejadorMatriz.LONGITUD_REALCM + 0.0) / (ManejadorMatriz.LONGITUD_PANEL + 0.0);
        DecimalFormat df = new DecimalFormat("#");
        String texto = "Coordenada x:" + df.format((n * evt.getX())) + " Coordenada y:" + df.format(n * evt.getY());
        this.frame.cambiarTextoDeCoordenadas(texto);
    }

    public void accionParaMouseDragged(MouseEvent e) {
        if (this.frame.seDebeBorrarAlMantenerPresionado()) {
            this.color = Color.WHITE;
        } else {
            this.color = copiaColor;
        }

        g = (Graphics2D) ((JPanel) e.getSource()).getGraphics();
        this.manMatriz.pintarPared(e.getX(), e.getY(), g, color);
        System.out.println(e.getX() + " " + e.getY());
        double n = (ManejadorMatriz.LONGITUD_REALCM + 0.0) / (ManejadorMatriz.LONGITUD_PANEL + 0.0);
        DecimalFormat df = new DecimalFormat("#");
        String texto = "Coordenada x:" + df.format((n * e.getX())) + " Coordenada y:" + df.format(n * e.getY());
        this.frame.cambiarTextoDeCoordenadas(texto);
        pintarCuadricula(g);
    }

    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.copiaColor = color;
    }

    public int getNumeroDeCuadros() {
        return numeroDeCuadros;
    }

    public void setNumeroDeCuadros(int numeroDeCuadros) {
        this.numeroDeCuadros = numeroDeCuadros;
    }

    public ManejadorMatriz getManMatriz() {
        return manMatriz;
    }

    public void setManMatriz(ManejadorMatriz manMatriz) {
        this.manMatriz = manMatriz;
    }

}
