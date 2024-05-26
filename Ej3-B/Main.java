import models.AdapterFechaFactura;
import models.Factura;
import models.SistemaGubernamental;

class Main{
    
    public static void main(String[] args) {
        
        //Creamos una Factura
        Factura nuevaFactura= new Factura();

        System.out.println("\nFecha factura: "+ nuevaFactura.verFecha());

        AdapterFechaFactura adapterFechaFactura= new AdapterFechaFactura(nuevaFactura);

        System.out.println("Fecha formateada por adaptador de fecha: "+ adapterFechaFactura.verFecha());

        // Creamos un sistema gubernamental

        SistemaGubernamental sistemaGubernamental= new SistemaGubernamental(adapterFechaFactura);

        // Fecha obtenida por el sistema gubernamental

        System.out.println("Fecha obtenida por el sistema gubernamental:" + sistemaGubernamental.verFechaFactura());

    }
}