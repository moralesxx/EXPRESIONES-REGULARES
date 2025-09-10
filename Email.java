/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

/*Clase Email que implementa la interfaz Validar
 Esta clase valida si un string es un correo electrónico válido*/
public class Email implements Validar {

    /* Patrón regex para validar emails
     ^[A-Za-z0-9._%+-]+ → el nombre de usuario puede contener letras, números y algunos caracteres especiales
     @ → obligatorio el símbolo "@"
     [A-Za-z0-9.-]+ → el dominio puede tener letras, números, puntos y guiones
     \\. → punto antes de la extensión
     [A-Za-z]{2,63}$ → la extensión debe tener entre 2 y 63 letras*/
    private static final Pattern EMAIL = 
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$");

    //Implementación del método validar definido en la interfaz Validar
    @Override
    public Resultado validar(String valor){
        /* Verifica si el valor es null o está en blanco
         Si es así, devuelve un Resultado indicando que está vacío y no es válido*/
        if(valor == null || valor.isBlank()){
            return new Resultado("email", valor, false, "", "vacío");
        }

        // Aplica la EXPRESION REGULAR para verificar si el valor coincide con el patrón de email
        boolean valido = EMAIL.matcher(valor).matches();

        if(valido){
            //NORMALIZAMOS
            String normalizado = valor.toLowerCase();

            //Devuelve un Resultado indicando que es válido y proporcionando la versión normalizada
            return new Resultado("email", valor, true, normalizado, "");
        } else {
            //Si no coincide con la regex, devuelve un Resultado indicando que el formato es inválido
            return new Resultado("email", valor, false, "", "Formato invalido");
        }
    }
}
