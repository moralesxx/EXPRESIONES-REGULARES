/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automatasexpresionesregulares;


/*Está clase procesa archivos CSV validando sus columnas según (email,telefono,url,monto)
Tiene un mapa de validadores que almacena objetos que implementas la interfaz Validar para 
cada tipo de dato (Email, Telefono, URL, Decimal)*/

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*; 

public class ProcesadorCSV {
    private Map<String, Validar> validadores; 
    
    public ProcesadorCSV(){
        validadores = new HashMap<>();
        validadores.put("email",new Email());
        validadores.put("telefono", new Telefono());
        validadores.put("url", new URL());
        validadores.put("monto", new Decimal());  
    }
    
    
    /*Lee un archivo CSV, recorre cada registro y cada columna, si hay validador para la columna
    aplica la validacion y guarda el resultado en una lista de Resultado.*/
    public List<Resultado> procesarArchivo(String rutaEntrada) throws IOException{
        List<Resultado> resultados = new ArrayList<>();
        Reader in = new FileReader(rutaEntrada);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);

        for (CSVRecord record : records) {
            for (String columna : record.toMap().keySet()) {
                Validar v = validadores.get(columna.toLowerCase());
                if (v != null) {
                    resultados.add(v.validar(record.get(columna)));
                }
            }
        }
        return resultados;
    }

    /*Toma la lista de resultados y los escribe en un archivo CSV con columnas: 
    campo,valor_original,es_valido,valor_normalizado,motivo*/
    public void escribirCSV(List<Resultado> resultados, String rutaSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("campo", "valor_original", "es_valido", "valor_normalizado", "motivo"))) {
            for (Resultado r : resultados) {
                csvPrinter.printRecord(r.getCampo(), r.getValorOriginal(),
                        r.isEsValido(), r.getValorNormalizado(), r.getMotivo());
            }
        }
    }

    /*Convierte la lista de resultados a JSON y la escribe en un archivo, usando jackson*/
    public void escribirJSON(List<Resultado> resultados, String rutaSalida) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaSalida), resultados);
    }    
   
}
