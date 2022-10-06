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


    public static void main(String[] args) throws IOException, InterruptedException {

        List<String> direcciones = Files.readAllLines(new File("direcciones.txt").toPath());
        int contador = 0;
        long tiempoInicial;
        long tiempoFinal;
        long tiempoTotal=0;
        for (String direccion :
                direcciones) {
            ProcessBuilder pb = new ProcessBuilder("ping", direccion);
            pb.redirectOutput(new File("ping-" + contador++ + ".txt"));
            tiempoInicial=System.currentTimeMillis();
            Process proceso = pb.start();
            proceso.waitFor();
            tiempoFinal=System.currentTimeMillis();
            tiempoTotal+=(tiempoFinal-tiempoInicial);
        }
        long tiempoMedio=tiempoTotal/direcciones.size();
        System.out.println("El tiempo promedio ha sido: "+tiempoMedio);

    }
}