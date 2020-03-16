/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.archivos;

import gnz.backend.Matriz.ManejadorMatriz;
import gnz.frontend.FrameOM;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jesfrin
 */
public class ManejadorDeArchivos {

    private boolean mapaEstaGuardado;
    private String nombreDeArchivo;
    private File rutaDeArchivo;
    private FrameOM laberintoFrame;

    public ManejadorDeArchivos(FrameOM laberinto) {
        this.mapaEstaGuardado = false;
        this.nombreDeArchivo = null;
        this.rutaDeArchivo = null;
        this.laberintoFrame = laberinto;
    }

    public boolean verificarGuardadoYSeguimientoDeAccion(ManejadorMatriz manMatriz) throws Exception {
        if (mapaEstaGuardado) {
            JOptionPane.showMessageDialog(this.laberintoFrame, "El mapa ya ha sido guardado");
        } else {
            int opcion = JOptionPane.showConfirmDialog(laberintoFrame, "Desea guardar el archivo actual?");
            switch (opcion) {
                case 0://Acepta guardar el archivo
                    guardarMapaEnArchivo(manMatriz);
                    break;
                case 1:
                    break;
                default://Cancelo la opcion
                    return false;
            }
        }
        return true;
    }

    public void guardarMapaEnArchivo(ManejadorMatriz manMatriz) throws Exception {
        if (rutaDeArchivo == null) {
            this.rutaDeArchivo = guardarMapaComo(manMatriz);
        } else {
            guardarMapa(manMatriz);
        }
    }

    public File guardarMapaComo(ManejadorMatriz manMatriz) throws Exception {
        mapaEstaGuardado=true;
        JFileChooser guardar = new JFileChooser();
        guardar.showSaveDialog(laberintoFrame);
        guardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File ruta = guardar.getSelectedFile();
        FileOutputStream fichero;
        fichero = new FileOutputStream(ruta);
        ObjectOutputStream tuberia = new ObjectOutputStream(fichero);
        tuberia.writeObject(manMatriz);
        JOptionPane.showMessageDialog(this.laberintoFrame, "Archivo guardado con exito!!!");
        return ruta;
        //Logger.getLogger(LaberintoFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

    public void abrirMapa(JPanel panel, ManejadorMatriz manMatriz) throws Exception {
        if (verificarGuardadoYSeguimientoDeAccion(manMatriz)) {
            JFileChooser abrir = new JFileChooser();
            abrir.showOpenDialog(this.laberintoFrame);
            abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
            File ruta = abrir.getSelectedFile();
            System.out.println("Ruta:"+ruta);
            FileInputStream fichero;
            fichero = new FileInputStream(ruta);
            ObjectInputStream tuberia = new ObjectInputStream(fichero);
            this.laberintoFrame.getManPanel().dibujarCuadriculaDeMatriz((ManejadorMatriz) tuberia.readObject(), panel);
            JOptionPane.showMessageDialog(null, "Matriz ejecutada");
            //Logger.getLogger(LaberintoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        public void crearNuevoMapa(ManejadorMatriz manMatriz,JPanel panel) throws Exception{
        if(verificarGuardadoYSeguimientoDeAccion(manMatriz)){
            panel.setBackground(Color.GRAY);
            panel.setBackground(Color.WHITE);
        }
    }

    private void guardarMapa(ManejadorMatriz manMatriz) throws Exception {
        mapaEstaGuardado=true;
        FileOutputStream fichero;
        fichero = new FileOutputStream(rutaDeArchivo);
        ObjectOutputStream tuberia = new ObjectOutputStream(fichero);
        tuberia.writeObject(manMatriz);
        JOptionPane.showMessageDialog(this.laberintoFrame, "Archivo guardado con exito!!!");
    }

    public void setMapaEstaGuardado(boolean mapaEstaGuardado) {
        this.mapaEstaGuardado = mapaEstaGuardado;
    }

}
