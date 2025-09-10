/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

/*Clase Telefono que implementa la interfaz Validar
Esta clase valida si un string es un número de teléfono válido.*/
public class Telefono implements Validar {

    /* Patrón EXPRESION REGULAR para validar números de teléfono
     ^\\+?[0-9]{7,15}$  
     ^ → inicio de la cadena
     \\+? → opcionalmente puede iniciar con un signo '+'
     [0-9]{7,15} → debe tener entre 7 y 15 dígitos
     $ → fin de la cadena*/
    private static final Pattern TELEFONO = 
            Pattern.compile("^\\+?[0-9]{7,15}$");

    //Implementación del método validar definido en la interfaz Validar
    @Override
    public Resultado validar(String valor){
        /* Verifica si el valor es null o está en blanco
         Si es así, devuelve un resultado indicando que está vacío y no es válido*/
        if(valor == null || valor.isBlank()){
            return new Resultado("telefono", valor, false, "", "vacio");
        }

        //Aplica la EXPRESION REGULAR para verificar si el valor coincide con el patrón de teléfono
        boolean valido = TELEFONO.matcher(valor).matches();

        if(valido){
            String normalizado = valor.replaceAll("[\\s\\-()]", "");
            
            //Devuelve un Resultado indicando que es válido y proporcionando la versión normalizada
            return new Resultado("telefono", valor, true, normalizado, "");
        }else{
            //Si no coincide con la regex, devuelve un Resultado indicando que el formato es inválido
            return new Resultado("telefono", valor, false, "", "Formato invalido");
        }
    }
}
