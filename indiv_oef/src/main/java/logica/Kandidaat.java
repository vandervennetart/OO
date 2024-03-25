package logica;

public class Kandidaat {
    private String naam;
    private int aantalStemmen;

    public Kandidaat(String naam){
        if (naam.length() >= 3){
            this.naam = naam;
            this.aantalStemmen = 0;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void voegStemToe(){
        aantalStemmen++;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalStemmen() {
        return aantalStemmen;
    }

    @Override
    public String toString() {
        return naam + " (" + aantalStemmen +" stemmen" + ")";
    }
}
