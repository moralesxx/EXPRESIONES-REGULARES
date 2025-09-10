/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;

/*Está clase guarda el resultado de validar/normalizar un valor.*/
public class Resultado {
    
    //ATRIBUTOS
    private String campo;
    private String valor_original;
    private boolean es_valido;
    private String valor_normalizado;
    private String motivo;

    //CONSTRUCTOR
    public Resultado(String campo, String valor_original, boolean es_valido,
                          String valor_normalizado, String motivo) {
        this.campo = campo; 
        this.valor_original = valor_original; 
        this.es_valido = es_valido; 
        this.valor_normalizado = valor_normalizado; 
        this.motivo = motivo; 
    }
    
    //GETTERS
    public String getCampo(){
        return campo; 
    }
    
    public String getValorOriginal(){
        return valor_original;
    }
    
    public boolean isEsValido(){
        return es_valido;
    }
    
    public String getValorNormalizado(){
        return valor_normalizado; 
    }
    
    public String getMotivo(){
        return motivo; 
    }
    
}


