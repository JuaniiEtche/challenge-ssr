package models;

public class Auto {

    private Marca marca;

    private String patente;



    public Auto(String patente, Marca marca){
        this.marca=marca;
        this.patente=patente;
    }

    public Marca getMarca(){
        return this.marca;
    }

    public String getPatente(){
        return this.patente;
    }

    public void setMarca(Marca nuevaMarca){
        this.marca=nuevaMarca;
    }

    public void setPatente(String nuevaPatente){
        this.patente=nuevaPatente;
    }


    
}
