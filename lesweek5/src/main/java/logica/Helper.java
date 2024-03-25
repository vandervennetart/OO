package logica;

public class Helper {
    public static double afronden(double getal, int aantalAchterComma){
        return (Math.round(getal*Math.pow(10, aantalAchterComma)) / Math.pow(10, aantalAchterComma));
    }
}
