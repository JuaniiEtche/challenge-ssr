package models;

public class McClaren extends Marca {

    @Override
    public int acelerar() {
        return 10;
    }

    @Override
    public int frenar() {
        return 1;
    }

    @Override
    public int doblar() {
        return 5;
    }

    public McClaren(){
        this.nombre="McClaren";
    }
    
}
