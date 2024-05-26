package models;

public class Williams extends Marca{

    @Override
    public int acelerar() {
        return 5;
    }

    @Override
    public int frenar() {
        return 10;
    }

    @Override
    public int doblar() {
        return 1;
    }

    public Williams(){
        this.nombre="Williams";
    }
    
}
