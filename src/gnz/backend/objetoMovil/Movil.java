/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.objetoMovil;

import gnz.backend.Matriz.ManejadorMatriz;
import gnz.backend.Matriz.Run;
import gnz.frontend.FrameOM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author Jonathan
 */
public class Movil {
    
    //Conexion Frame
    private FrameOM frame;


    //Lista de Coordenadas con las que choca el objeto movil (0,0) (800,800)
    public LinkedList<Coordenada> coordenadas = new LinkedList<Coordenada>();

    //LABEL QUE REPRESENTA EL OBJETO MOVIL
    private JLabel objetoMovil = new JLabel();

    //Tiempo en el que se desplaza un pixel en la velocidad mas lenta (sirve para calcular del)
    int t;

    //Tiempo ajustado a la velocidad 
    int del;

    //Hilo de ejecuccion
    Timer tiempo1;

    //Distancia total recorrida para reporte final
    int distanciat;

    //Lista de movimientos
    LinkedList<Movimiento> Movimientos;

    //ENTRAR AL MOVIMIENTO
    boolean bandera = true;

    //ENTRADA A ENVIAR COORDENADAS XY
    boolean banderaXY = true;

    //DISTANCIA A RECORRER
    double x;
    double y;

    //DISTANCIA A LA QUE SE LLEGARA
    double x1;
    double y1;

    //ANGULOS EN GRADOS INT Y RADIANES DOUBLE
    int angulo1;
    double angulo;

    //CONTADOR DEL LISTADO DE MOVIMIENTOS
    public int n;

    //RELACIONES PARA MOVIMIENTOS DIAGONALES SE USA UNA U OTRA POR CADA MOVIMIENTO
    int rxy;
    int ryx;

    //CONTADOR DE LAS RELACIONES DE XY O DE YX
    int cxy = 0;
    int cyx = 0;

    //VARIABLES  PARA ENVIAR INFORMACION 
    //ENVIAR POS X O POS Y AL FRAME
    JLabel label7;
    JLabel label8;
    //ENIVAR INFO AL TEXTAREA
    JTextArea area;

    public Movil(FrameOM frameOm) {
        this.frame=frameOm;
        //DECLARACION DEL HILO
        tiempo1 = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bandera) {
                    //LIMPIAR VARIABLES Y LLENAR TODAS VARIABLES CON INFORMACION

                    //DELAY DE TIEMPO ENTRE CADA MOVIMIENTO
                    if (n > 0) {
                        delay(2000);
                    }
                    //Se dibua la cuardicula

                    bandera = false;
                    x = 0;
                    y = 0;
                    x1 = 0;
                    y1 = 0;
                    angulo = 0;
                    angulo1 = 0;

                    //CALCULAR DISTANCIAS
                    angulo1 = Movimientos.get(n).angulo;
                    angulo = Math.toRadians(angulo1);
                    x = (Math.round((Math.cos(angulo)) * Run.MULT * Movimientos.get(n).distancia)) / Run.MULT;
                    y = (Math.round(((Math.sin(angulo)) * Run.MULT * Movimientos.get(n).distancia) * -1)) / Run.MULT;
                    distanciat = distanciat + Movimientos.get(n).distancia;

                    //RELACION A USAR YA SEA XY O YX
                    if (y != 0) {
                        rxy = (int) Math.abs(Math.round(x / y));
                    } else {
                        rxy = 1;
                    }
                    if (x != 0) {
                        ryx = (int) Math.abs(Math.round(y / x));
                    } else {
                        ryx = 1;
                    }

                    //CALCULO DE DISTANCIA FINAL A LA QUE SE LLEGARA
                    x1 = (Math.round(objetoMovil.getX() + x * Run.MULT));
                    y1 = (Math.round(objetoMovil.getY() + y * Run.MULT));
                    if (Movimientos.get(n).velocidad != 2) {
                        del = (t / Movimientos.get(n).velocidad);
                    } else {
                        del = ((t * 2) / 3);
                    }
                }
                //ENVIAR X (MUEVE UN PIXEL EN X)
                if (objetoMovil.getX() != x1) {
                    if (banderaXY) {
                        if (angulo1 >= 90 && angulo1 < 271) {
                            objetoMovil.setLocation(objetoMovil.getX() - 1, objetoMovil.getY());
                            objetoMovil.repaint();
                        } else {
                            objetoMovil.setLocation(objetoMovil.getX() + 1, objetoMovil.getY());
                            objetoMovil.repaint();
                        }
                        cxy = cxy + 1;
                        if (cxy > rxy) {
                            cxy = 0;
                            banderaXY = false;
                        }
                    }
                } else {
                    banderaXY = false;
                }

                //ENVIAR Y (MUEVE UN PIXEL EN Y)
                if (objetoMovil.getY() != y1) {
                    if (!banderaXY) {
                        if (angulo1 >= 180 && angulo < 361) {
                            objetoMovil.setLocation(objetoMovil.getX(), objetoMovil.getY() + 1);
                            objetoMovil.repaint();
                        } else {
                            objetoMovil.setLocation(objetoMovil.getX(), objetoMovil.getY() - 1);
                            objetoMovil.repaint();
                        }
                        cyx = cyx + 1;
                        if (cyx > ryx) {
                            cyx = 0;
                            banderaXY = true;
                        }
                    }
                } else {
                    banderaXY = true;
                }

                //PINTAR LAS CORDENADAS EN LOS LABELS DEL FRAME
                label7.setText("" + (objetoMovil.getX() / Run.MULT));
                label8.setText("" + (objetoMovil.getY() / Run.MULT));

                //ENVIAR EL RETRASO DE VELOCIDAD DELAY DEL TIEMPO
                delay(del);

                //COMPROBAR SI YA SE FINALIZO EL MOVIMIENTO
                if (!comprobar()) {
                    if (x1 == objetoMovil.getX() && y1 == objetoMovil.getY()) {
                        bandera = true;
                        area.append("--------------------MOVIMIENTO NUMERO " + (n + 1) + " EXITOSO--------------------\n");
                        area.append("CON DISTANCIA = " + Movimientos.get(n).distancia + ", VELOCIDAD = " + Movimientos.get(n).velocidad + " Y ANGULO = " + Movimientos.get(n).angulo + "\n");
                        area.append("SUS NUEVAS COORDENADAS SON X = " + (objetoMovil.getX() / Run.MULT) + ", Y=" + (objetoMovil.getY() / Run.MULT) + "\n\n");
                        n = n + 1;
                        System.out.println("" + objetoMovil.getX() + "\n");
                        System.out.println("" + objetoMovil.getY() + "\n");
                        if (n >= Movimientos.size()) {
                            area.append("SE REALIZARON UN TOTAL DE  " + (n) + " MOVIMIENTOS\n");
                            area.append("SE RECORRIO UNA DISTANCIA TOTAL DE " + distanciat + " CM\n");
                            area.append("SE FINALIZO EL MOVIMIENTO\n");
                            JOptionPane.showMessageDialog(null, "SE FINALIZARON LOS MOVIMIENTOS");
                            JOptionPane.showMessageDialog(null, "Fin");
                            frame.getManPanel().dibujarCuadriculaDeMatriz(frame.getManPanel().getManMatriz(), frame.getMatrizPanel());
                            tiempo1.stop();
                        }
                    }
                }
            }
        });
    }

    //METODO EN EL CUAL SE LE ENVIAN LOS PARAMETROS AL OBJETO MOVIL (LISTA DE MOVIMIENTO, TIEMPO, LABELS PARA POS "X" y "Y" y EL TEXT AREA
    public void enviar(LinkedList<Movimiento> Movimientos, int t, JLabel label7, JLabel label8, JTextArea area) {
        this.Movimientos = Movimientos;
        this.t = t;
        this.n = 0;
        this.label7 = label7;
        this.label8 = label8;
        this.area = area;
        distanciat = 0;
        area.append("--------------------INICIO DEL MOVIMIENTO EN LAS COORDENADAS--------------------\n");
        area.append("Cordenada X=" + (objetoMovil.getX() / Run.MULT) + "   Cordenada Y=" + (objetoMovil.getY() / Run.MULT) + "\n\n");
        tiempo1.start();
    }

    //METODO DE REPOCICIONAR EL OBJETO MOVIL
    public void repos(int x, int y, JTextArea area) {
        objetoMovil.setLocation(x, y);
        objetoMovil.repaint();
        this.area = area;
        comprobar();
        area.append("--------------------SE REPOCISIONO EL OBJETO EN LAS CORDENADAS--------------------\n");
        area.append("Cordenada X=" + (objetoMovil.getX() / Run.MULT) + "   Cordenada Y=" + (objetoMovil.getY() / Run.MULT) + "\n\n");

    }

    //METDO PARA COMPROBAR LAS COORDENADAS OCUPADAS
    private boolean comprobar() {
        int l = limites();
        if (l == 1) {
            area.append("----------------------ERROR EN EL OBJETO MOVIL----------------------\n");
            area.append("---------------------------COLISION EN------------------------------\n");
            area.append("Cordenada X=" + (objetoMovil.getX() / Run.MULT) + "   Cordenada Y=" + (objetoMovil.getY() / Run.MULT) + "\n\n");
            JOptionPane.showMessageDialog(null, "EL OBJETO MOVIL CHOCO");
            tiempo1.stop();
            return true;

        } else {
            for (int i = 0; i < coordenadas.size(); i++) {
                if (((coordenadas.get(i).x >= objetoMovil.getX()) && (coordenadas.get(i).x <= objetoMovil.getX() + (26 * Run.MULT))) && ((coordenadas.get(i).y >= objetoMovil.getY()) && (coordenadas.get(i).y <= objetoMovil.getY() + (16 * Run.MULT)))) {
                    area.append("----------ERROR EN EL OBJETO MOVIL----------\n");
                    area.append("---------------COLISION EN------------------\n");
                    area.append("Cordenada X=" + (objetoMovil.getX() / Run.MULT) + "   Cordenada Y=" + (objetoMovil.getY() / Run.MULT) + "\n\n");
                    JOptionPane.showMessageDialog(null, "EL OBJETO MOVIL CHOCO");
                    tiempo1.stop();
                    return true;
                }
            }
        }

        return false;
    }

    //LIMITES DEL MAPA
    private int limites() {
        if (((objetoMovil.getX() / Run.MULT) > 174) || ((objetoMovil.getX() / Run.MULT) < 0)) {
            return 1;
        }
        if ((((objetoMovil.getY()) / Run.MULT) > 184) || (((objetoMovil.getY()) / Run.MULT) < 0)) {
            return 1;
        }
        return 0;
    }

    //METODO DE LIMPIAR VARIABLES
    public void limpiar() {
        Movimientos = null;
        int t;
        tiempo1.stop();
        bandera = true;
        banderaXY = true;
        x = 0;
        y = 0;
        x1 = 0;
        y1 = 0;
        angulo1 = 0;
        angulo = 0;
        n = 0;
    }

    //FUNCION DE DELAY
    public void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrameOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JLabel getObjetoMovil() {
        return objetoMovil;
    }

    public void setObjetoMovil(JLabel objetoMovil) {
        this.objetoMovil = objetoMovil;
    }

}


/*

Ini trayecto
MovDiagonal(100 cm, 3, -45 grados)
Fin trayecto

Ini trayecto
MovRecto(100 cm, 3, adelante)
Fin trayecto


 */
