/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane; 
import java.io.File; 
import java.util.*; 

/*Está clase permite al usuario seleccionar un CSV mediante JFileChooser, valida cada valor usando los validadores
definidos, y genera archivos de resultado CSV y JSON mostrando mensajes mediante ventanas emergentes.*/

public class Principal {
    public static void main(String[] args){
        ProcesadorCSV procesador = new ProcesadorCSV();
        
        JFileChooser seleccionar = new JFileChooser();
        seleccionar.setDialogTitle("SELECCIONA EL ARCHIVO CSV");
        int resultado = seleccionar.showOpenDialog(null); 
        
        if(resultado != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(null,"NO SE SELECCIONO NINGUN ARCHIVO");
            return; 
        }
        
        File archivo = seleccionar.getSelectedFile();
        
        try{
            List<Resultado> resultados = procesador.procesarArchivo(archivo.getAbsolutePath());
            
            String rutaCSV = archivo.getParent() + File.separator + "resultado.csv"; 
            String rutaJSON = archivo.getParent() + File.separator + "resultado.json";
            
            procesador.escribirCSV(resultados, rutaCSV);
            procesador.escribirJSON(resultados, rutaJSON);
            
            JOptionPane.showMessageDialog(null, 
                    "Procesamiento completo. \nArchivos generados:\n"+rutaCSV + "\n" + rutaJSON);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error procesando el archivo: "+e.getMessage());
            e.printStackTrace();
        }
        
    }
}
