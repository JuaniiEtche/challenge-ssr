package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdapterFechaFactura implements InterfaceClient{


    private Factura factura;


    public AdapterFechaFactura (Factura factura){
        this.factura=factura;
    }


    public String verFecha(){
        String fechaFactura= factura.verFecha();
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Analizar la fecha desde el string
        LocalDate fecha = LocalDate.parse(fechaFactura, formatoEntrada);
        // Definir el formato de salida
        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("yyyyMMdd");
        // Formatear la fecha al nuevo formato
        String nuevaFechaFormateada = fecha.format(formatoSalida);
        return nuevaFechaFormateada;
    }
    
}
