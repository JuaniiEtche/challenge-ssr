package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Factura {


    public String verFecha(){
       // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        
        // Definir el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Formatear la fecha
        String fechaFormateada = fechaActual.format(formato);
        
        return fechaFormateada;

    }
    
}
