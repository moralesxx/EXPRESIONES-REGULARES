/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 


import java.util.regex.Pattern;


/*Clase Decimal que implementa la interfaz Validar
Esta clase valida si un string es un número decimal válido, aceptando separadores de miles
y punto o coma como separador decimal*/
public class Decimal implements Validar {

    /* Patrón EXPRESION REGULAR para validar decimales con o sin separadores de miles
    ^[+-]? → opcionalmente puede tener un signo + o -
    (\d{1,3}([.,]\d{3})*|\d+)? → parte entera, con separadores de miles opcionales
    ([.,]\d+)?$ → parte decimal opcional, con punto o coma*/
    public static final Pattern DECIMAL = Pattern.compile(
            "^[+-]?(\\d{1,3}([.,]\\d{3})*|\\d+)?([.,]\\d+)?$"
    );

    //Implementación del método validar de la interfaz Validar
    @Override
    public Resultado validar(String valor) {
        /*Verifica si el valor es null o está en blanco
        // Si es así, devuelve un Resultado indicando que está vacío y no es válido*/
        if (valor == null || valor.isBlank()) {
            return new Resultado("monto", valor, false, "", "vacio");
        }

        //Aplica la regex para verificar si el valor coincide con el patrón de decimal
        boolean valido = DECIMAL.matcher(valor).matches();

        if (valido) {
            //Normalización: quitar separadores de miles y unificar decimal a punto
            String limpio = valor.trim();

            //Detectar cuál es el separador decimal: el último punto o coma
            int ultimoPunto = limpio.lastIndexOf('.');
            int ultimaComa = limpio.lastIndexOf(',');
            int sepDecimal = Math.max(ultimoPunto, ultimaComa);

            if (sepDecimal != -1) {
                //Separador decimal encontrado, separar entero y decimal
                //Quitar los demás puntos o comas de la parte entera
                String entero = limpio.substring(0, sepDecimal).replaceAll("[.,]", "");
                String decimal = limpio.substring(sepDecimal + 1).replaceAll("[.,]", "");
                //Reconstruir número con punto como decimal
                limpio = entero + "." + decimal;
            } else {
                //No hay separador decimal, solo quitar separadores de miles
                limpio = limpio.replaceAll("[.,]", "");
            }

            //Devuelve un Resultado indicando que es válido y proporcionando la versión normalizada
            return new Resultado("monto", valor, true, limpio, "");
        } else {
            //Si no coincide con la regex, devuelve un Resultado indicando que el número no es válido
            return new Resultado("monto", valor, false, "", "No es un número decimal válido");
        }
    }
}
