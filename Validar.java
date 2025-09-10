/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;


/*Interfaz para definir la validación. Asegura que todas las clases de validación devuelvan un Resultado al evaluar un valor.*/
public interface Validar {
    Resultado validar(String valor);
}
