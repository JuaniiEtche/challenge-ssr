import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import models.Auto;
import models.Ferrari;
import models.McClaren;
import models.Williams;

class Main{

    public static void main(String[] args) {
        
         // Creamos instancias de las 3 marcas de auto
        Ferrari marcaFerrari = new Ferrari();
        McClaren marcaMcClaren = new McClaren();
        Williams marcaWilliams = new Williams();
        
        // Creamos un auto por cada marca
        List<Auto> autos = List.of(
            new Auto("A1234", marcaFerrari),
            new Auto("B1234", marcaMcClaren),
            new Auto("C1234", marcaWilliams)
        );

        StringBuilder informacionAutos= new StringBuilder();
        informacionAutos.append("\nInformacion de autos:");
        autos.stream().forEach(
            auto->{
                informacionAutos.append("\nPatente: "+auto.getPatente()).append("\n");
                informacionAutos.append("Marca: " + auto.getMarca().getNombre()).append("\n");
                informacionAutos.append("Niveles: ").append("\n");
                informacionAutos.append("- Acelerar: "+ auto.getMarca().acelerar()).append("\n");
                informacionAutos.append("- Frenar: "+ auto.getMarca().frenar()).append("\n");
                informacionAutos.append("- Doblar: "+ auto.getMarca().doblar()).append("\n");
                });

        System.out.println(informacionAutos);       
    }
}