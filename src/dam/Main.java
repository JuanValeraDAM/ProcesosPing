package dam;
/*
Leer de un fichero que contiene una dirección de Internet en cada línea.
Lanzar un proceso PING para cada una, guardando los resultados en un fichero
diferente para cada una, nombrando a estos ficheros "ping-X.txt", donde X es un
contador. El programa acabará cuando terminen su ejecución todos los procesos
PING, y entonces mostrará el tiempo promedio que han tardado todos ellos.


localhost
www.ieschirinos.com
www.twitter.com

 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {

    Path fichero = Paths.get("direcciones.txt");
    List<String> lineas;
       lineas= Files.readAllLines(fichero);
        long tiempoInicial;
        long tiempoTotal=0;
        long tiempoFinal;
        int contador = 0;

        for (String linea :
                lineas) {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ping", linea);
            String nombreFicheroDestino = "ping-"+contador++ +".txt";
            pb.redirectOutput(ProcessBuilder.Redirect.to(new File(nombreFicheroDestino)));
            tiempoInicial=System.currentTimeMillis();
            pb.start();
            tiempoFinal=System.currentTimeMillis();
            tiempoTotal+= tiempoFinal-tiempoInicial;

        }
        long tiempoPromedio= tiempoTotal/lineas.size();
        System.out.println("El tiempo promedio que han tardado los procesos es de:"+tiempoPromedio);


    }
}