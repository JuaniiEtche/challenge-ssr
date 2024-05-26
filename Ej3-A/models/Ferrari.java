package models;

public class Ferrari extends Marca {

    @Override
    public int acelerar() {
        return 5;
    }

    @Override
    public int frenar() {
        return 1;
    }

    @Override
    public int doblar() {
        return 10;
    }

    public Ferrari(){
        this.nombre="Ferrari";
    }
    
    
}
