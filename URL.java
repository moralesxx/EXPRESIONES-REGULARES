/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;
import java.util.regex.Pattern; 
import java.util.regex.Matcher; 

/*Clase URL que implementa la interfaz Validar
Esta clase valida si un string es una URL válida con esquema http o https.*/
public class URL implements Validar {

    /*Patrón de EXPRESION REGULAR para validar URLs
    ^(https?://) → La URL debe comenzar con "http://" o "https://"
    ([a-z0-9.-]+\.[a-z]{2,}) → El dominio debe tener letras/números, puntos o guiones, y al menos un punto seguido de una extensión de al menos 2 letras
    (/.*)? → Opcionalmente puede tener un path después del dominio
    Pattern.CASE_INSENSITIVE → No distingue entre mayúsculas y minúsculas*/
    private static final Pattern URL =
            Pattern.compile("^(https?://)([a-z0-9.-]+\\.[a-z]{2,})(/.*)?$", Pattern.CASE_INSENSITIVE);

    //Implementación del método validar definido en la interfaz Validar
    @Override
    public Resultado validar(String valor) {
        /*Verifica si el valor es null o está en blanco
        Si es así, devuelve un resultado indicando que está vacío y no es válido*/
        if (valor == null || valor.isBlank()) {
            return new Resultado("url", valor, false, "", "vacio");
        }

        //Aplica la EXPRESION REGULAR para verificar si el valor coincide con el patrón de URL
        boolean valido = URL.matcher(valor).matches();

        if (valido) {
            //NORMALIZAMOS
            String normalizado = valor.toLowerCase();

            //Devuelve un Resultado indicando que es válido y proporcionando la versión normalizada
            return new Resultado("url", valor, true, normalizado, "");
        } else {
            //Si no coincide con la regex, devuelve un Resultado indicando que el formato es inválido
            return new Resultado("url", valor, false, "", "Formato invalido");
        }
    }
}
