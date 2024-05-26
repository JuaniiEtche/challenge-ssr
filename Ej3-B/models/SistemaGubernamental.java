package models;

public class SistemaGubernamental {
    
    private InterfaceClient interfaceClient;

    public SistemaGubernamental (InterfaceClient interfaceClient){
        this.interfaceClient=interfaceClient;
    }

    
    public String verFechaFactura(){
        String fechaFormateada= interfaceClient.verFecha();
        return fechaFormateada;
    };


}
