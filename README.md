
¡¡¡NOTA IMPORTANTE!!!
AGREGAR ESTAS DEPENDENCIAS/LIBRERIAS AL "pom.xml" DEL PROYECTO:

 <dependencies>
    <!-- Apache Commons CSV -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-csv</artifactId>
        <version>1.10.0</version>
    </dependency>

    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.17.2</version>
    </dependency>
</dependencies>

Bien, acá en este archivo daré una breve explicación de como funciona y que fue lo que hice para realizar mi proyecto. Utilicé el lenguaje de programación Java en donde hice mi aplicación
la cual va a poder leer archivos .CSV y generar archivos de salida .CSV/.JSON por medio de las dependencias que adjunté al principio de este documento. Esas dependencias (Apache Commons CSV, Jackson) permiten manipular archivos CSV y JSON con los cuales nosotros justamente vamos a trabajar. Bien, luego dentro de mi aplicación Java clasifiqué el programa en varias Clases cada
una con la lógica correspondiente a lo que se solicita, así mismo, quiero dar un pequeño ejemplo de lo que sucede.

<!--2 EJEMPLOS DE CASOS VALIDOS-->
<!--DATOS DE ENTRADA UN ARCHIVO LLAMADO: DATOS_ENTRADA.csv-->
- 2 EJEMPLOS DE CASOS VALIDOS 
- ARCHIVO DE ENTRADA .csv -------> DATOS_ENTRADA.csv
<!--COLUMNAS DATOS DE ENTRADA-->
email, telefono, url, monto
<!--EJEMPLO 1--> 
user774@example.com, +50290969937, https://page.net, 1234.56
<!--EJEMPLO 2--> 
user1727@test.org, +50216646838, https://missingcolon.com, 5633.83



<!--DATOS DE SALIDA UN ARCHIVO LLAMADO: resultado.csv-->
- ARCHIVO DE SALIDA .csv --------> resultado.csv
<!--COLUMNAS DATOS DE SALIDA-->
campo, valor_original, es_valido, valor_normalizado, motivo
<!--EJEMPLO 1--> 
email, user774@example.com, true, user774@example.com,
telefono, +50290969937, true, +50290969937,
url, https://page.net, true, https://page.net,
monto, 1234.56, true, 1234.56,
<!--EJEMPLO 2--> 
email, user1727@test.org, true, user1727@test.org,
telefono, +50216646838, true, +50216646838,
url, https://missingcolon.com, true, https://missingcolon.com,
monto, 5633.83, true, 5633.83,


<!--DATOS DE SALIDA UN ARCHIVO LLAMADO: resultado.json-->
- ARCHIVO DE SALIDA .json ------> resultado.json
<!--EJEMPLO 1-->
{
  "campo" : "email",
  "motivo" : "",
  "valorOriginal" : "user774@example.com",
  "valorNormalizado" : "user774@example.com",
  "esValido" : true
}, {
  "campo" : "telefono",
  "motivo" : "",
  "valorOriginal" : "+50290969937",
  "valorNormalizado" : "+50290969937",
  "esValido" : true
}, {
  "campo" : "url",
  "motivo" : "",
  "valorOriginal" : "https://page.net",
  "valorNormalizado" : "https://page.net",
  "esValido" : true
}, {
  "campo" : "monto",
  "motivo" : "",
  "valorOriginal" : "1234.56",
  "valorNormalizado" : "1234.56",
  "esValido" : true
}


<!---EJEMPLO 2-->
{
  "campo" : "email",
  "motivo" : "",
  "valorOriginal" : "user1727@test.org",
  "valorNormalizado" : "user1727@test.org",
  "esValido" : true
}, {
  "campo" : "telefono",
  "motivo" : "",
  "valorOriginal" : "+50216646838",
  "valorNormalizado" : "+50216646838",
  "esValido" : true
}, {
  "campo" : "url",
  "motivo" : "",
  "valorOriginal" : "https://missingcolon.com",
  "valorNormalizado" : "https://missingcolon.com",
  "esValido" : true
}, {
  "campo" : "monto",
  "motivo" : "",
  "valorOriginal" : "5633.83",
  "valorNormalizado" : "5633.83",
  "esValido" : true
}




- 2 EJEMPLOS DE CASOS INVALIDOS
<!--DATOS DE ENTRADA UN ARCHIVO LLAMADO: DATOS_ENTRADA.csv-->
- ARCHIVO DE ENTRADA .csv -------> DATOS_ENTRADA.csv
<!--COLUMNAS DATOS DE ENTRADA-->
email, telefono, url, monto
<!--EJEMPLO 1--> 
bademail.com, 1234, htt://fail.com, abc
<!--EJEMPLO 2--> 
user@@example.com, +50, https://wrong-url, 12,34


<!--DATOS DE SALIDA UN ARCHIVO LLAMADO: resultado.csv-->
- ARCHIVO DE SALIDA .csv --------> resultado.csv
<!--COLUMNAS DATOS DE SALIDA-->
campo, valor_original, es_valido, valor_normalizado, motivo
<!--EJEMPLO 1--> 
email, bademail.com, false, ,Formato invalido
telefono, 1234, false, ,Formato invalido
url, htt://fail.com, false, ,Formato invalido
monto, abc, false, ,No es un número decimal válido
<!--EJEMPLO 2--> 
email, user@@example.com, false, ,Formato invalido
telefono, +50, false, ,Formato invalido
url, https://wrong-url, false, ,Formato invalido
monto, 12, true, 12,


- ARCHIVO DE SALIDA .json ------> resultado.json
<!--DATOS DE SALIDA UN ARCHIVO LLAMADO: resultado.json-->
<!--EJEMPLO 1-->
{
  "campo" : "email",
  "motivo" : "Formato invalido",
  "valorOriginal" : "bademail.com",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "telefono",
  "motivo" : "Formato invalido",
  "valorOriginal" : "1234",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "url",
  "motivo" : "Formato invalido",
  "valorOriginal" : "htt://fail.com",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "monto",
  "motivo" : "No es un número decimal válido",
  "valorOriginal" : "abc",
  "valorNormalizado" : "",
  "esValido" : false
}



<!---EJEMPLO 2-->
{
  "campo" : "email",
  "motivo" : "Formato invalido",
  "valorOriginal" : "user@@example.com",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "telefono",
  "motivo" : "Formato invalido",
  "valorOriginal" : "+50",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "url",
  "motivo" : "Formato invalido",
  "valorOriginal" : "https://wrong-url",
  "valorNormalizado" : "",
  "esValido" : false
}, {
  "campo" : "monto",
  "motivo" : "",
  "valorOriginal" : "12",
  "valorNormalizado" : "12",
  "esValido" : true
}

ESTO ES UNA BREVE DOCUMENTACION EN UN README.md sobre como se realizó y con ejemplos. :)


    
