package models;

public abstract class Marca {

    //Los numeros enteros representan en mayor o en menor medida
    // la capacidad de las distintas marcas para acelerar, frenar o doblar

    protected String nombre;

    public abstract int acelerar();

    public abstract int frenar();

    public abstract int doblar();

    public String getNombre(){
        return this.nombre;
    }
}  
    


