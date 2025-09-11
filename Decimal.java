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

    /*^[+-]? → permite un signo positivo o negativo opcional al inicio.
    (\d{1,3}(\.\d{3})*|\d+) 
	\d{1,3}(\.\d{3})*  → Un grupo de 1 a 3 dígitos, seguido de cero o más bloques de punto + 3 dígitos. 
        \d+  → Uno o más dígitos, sin separador de miles. 
    (,\d+|\.\d+)?   →  Permite agregar decimales de dos formas: coma como separador decimal y punto como separador decimal. 
    $ → marca el final de la cadena, asegurando que toda la cadena cumpla el patrón.
    */
    public static final Pattern DECIMAL = Pattern.compile(
        "^[+-]?(\\d{1,3}(\\.\\d{3})*|\\d+)(,\\d+|\\.\\d+)?$"
    );

    @Override
    public Resultado validar(String valor) {
        if (valor == null || valor.isBlank()) {
            return new Resultado("monto", valor, false, "", "vacio");
        }

        boolean valido = DECIMAL.matcher(valor).matches();

        if (valido) {
            String limpio = valor.trim();

            // Detectar separador decimal (último punto o coma)
            int ultimoPunto = limpio.lastIndexOf('.');
            int ultimaComa = limpio.lastIndexOf(',');
            int sepDecimal = Math.max(ultimoPunto, ultimaComa);

            if (sepDecimal != -1) {
                String entero = limpio.substring(0, sepDecimal).replaceAll("[.,]", "");
                String decimal = limpio.substring(sepDecimal + 1).replaceAll("[.,]", "");
                limpio = entero + "." + decimal; // Usar punto como separador decimal
            } else {
                limpio = limpio.replaceAll("[.,]", ""); // Solo quitar separadores de miles
            }

            return new Resultado("monto", valor, true, limpio, "");
        } else {
            return new Resultado("monto", valor, false, "", "No es un número decimal válido");
        }
    }
}
