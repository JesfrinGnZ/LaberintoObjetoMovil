/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnz.backend.objetoMovil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jonathan
 */
public class Archivos {
    
    public String leer(String cadena){
        String texto="";
        try {
            BufferedReader bf= new BufferedReader(new FileReader(cadena));
            String temp="";
            String bfReader;
            while ((bfReader=bf.readLine())!=null) {
                temp=temp+bfReader+"\n";
            }
            texto=temp;
        } catch (Exception e) {
        }
        return texto;
    }
    
    public File Abrir(){
        JFileChooser chooser=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("obmo", "OBMO");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        return f;
    }
    
    
}
