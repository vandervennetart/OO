package logica;

import java.util.ArrayList;
import java.util.Objects;

public class KaartSpel {

    private Speler speler1;
    private Speler speler2;
    private int aantalRondes;
    private Speler spelerAanZet;
    private KaartBoek kaartBoek = new KaartBoek();

    public KaartSpel(){
        this("dummy1", "dummy2", 10);
    }

    public KaartSpel(String speler1, String speler2, int aantalRondes){
        this.speler1 = new Speler(speler1);
        this.speler2 = new Speler(speler2);
        this.aantalRondes = aantalRondes;
        this.speler1.setKaartboek(kaartBoek);
        this.speler2.setKaartboek(kaartBoek);
    }

    public Speler getSpeler1() {
        return speler1;
    }

    public Speler getSpeler2() {
        return speler2;
    }

    public Speler getSpelerAanZet() {
        return spelerAanZet;
    }

    public int getAantalRondes() {
        return aantalRondes;
    }

    public void bepaalWieMagStarten(){
        int worp1;
        int worp2;
        do {
            worp1 = speler1.gooiDobbelsteen();
            worp2 = speler2.gooiDobbelsteen();
        }while (worp1 == worp2);

        spelerAanZet = (worp1 > worp2) ? speler1 : speler2;
    }

    public void speelRonde(){

        do {
            for (int i = 0; i < 2; i++) {
                if(spelerAanZet == speler1){
                    speler1.kiesRandomKaart();
                    spelerAanZet = speler2;
                }else{
                    speler2.kiesRandomKaart();
                    spelerAanZet = speler1;
                }
            }


        } while (speler1.getKaartInHand() == speler2.getKaartInHand());

        if (Objects.equals(Kaart.geefHoogsteKaart(speler1.getKaartInHand(), speler2.getKaartInHand()), speler1.getKaartInHand())){
            speler1.incrementScore();
        }else{
            speler2.incrementScore();
        }

    }

    public void speelSpel(){
        speler1.resetScore();
        speler2.resetScore();
        bepaalWieMagStarten();
        for (int i = 0; i < aantalRondes; i++) {
            speelRonde();
        }
        bepaalWinnaar();
    }

    public Speler bepaalWinnaar(){


        if (speler1.getScore() > speler2.getScore()){
            return speler1;
        }else if (speler1.getScore() < speler2.getScore()){
            return speler2;
        }else{
            return null;
        }
    }

    public ArrayList<Kaart> getKaarten(){
        return kaartBoek.getKaarten();
    }



}
