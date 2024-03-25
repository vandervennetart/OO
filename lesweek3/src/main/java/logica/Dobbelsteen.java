package logica;

public class Dobbelsteen {
    int min;
    int max;
    int laatsteWorp = -1;

    public Dobbelsteen(int min, int max){
        this.min = min;
        this.max = max;
    }

    public Dobbelsteen(){
        this(1,6);
    }

    public void gooi(){
        this.laatsteWorp = (int) (Math.floor(Math.random() * (this.max - this.min + 1) ) + this.min);
    }

    public int get() {
        return this.laatsteWorp;
    }

    @Override
    public String toString() {
        return Integer.toString(get());
    }

}
