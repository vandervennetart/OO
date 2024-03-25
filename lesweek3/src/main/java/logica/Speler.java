package logica;

public class Speler {
    private String naam;
    private KaartBoek kaartboek = new KaartBoek();
    private Kaart kaartInHand;
    private int score = 0;

    public Speler(String naam){
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setKaartboek(KaartBoek kaartboek) {
        this.kaartboek = kaartboek;
    }

    public Kaart getKaartInHand() {
        return kaartInHand;
    }

    public int getScore() {
        return score;
    }

    public void resetScore(){
        score = 0;
    }

    public void incrementScore(){
        score++;
    }

    public int gooiDobbelsteen(){
        Dobbelsteen dobbel = new Dobbelsteen();

        dobbel.gooi();
        return dobbel.get();
    }

    public Kaart kiesRandomKaart(){
        kaartInHand = kaartboek.haalRandomKaart();
        return kaartInHand;
    }

    public String toString(){
        return naam + ": " + score;
    }

    public Kaart trekRandomKaartUitKaartboek(){
        return kaartboek.haalRandomKaart();
    }
}
