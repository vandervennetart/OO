package logica;

import java.util.Objects;

public class KaartSpel {

    private Speler speler1;
    private Speler speler2;
    private int aantalRondes;
    private Speler spelerAanZet;
    private KaartBoek kaartBoek;

    public KaartSpel(){
        this.speler1 = new Speler("dummy1");
        this.speler2 = new Speler("dummy2");
        this.aantalRondes = 10;
    }

    public KaartSpel(String speler1, String speler2, int aantalRondes){
        this.speler1 = new Speler(speler1);
        this.speler2 = new Speler(speler2);
        this.aantalRondes = aantalRondes;
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
        Kaart kaartSpeler1;
        Kaart kaartSpeler2;

        do {
            if(spelerAanZet == speler1){
                kaartSpeler1 = speler1.kiesRandomKaart();
                spelerAanZet = speler2;
            }else{
                kaartSpeler2 = speler2.kiesRandomKaart();
            }

        } while (speler1.getKaartInHand() == speler2.getKaartInHand());

        if (Objects.equals(Kaart.geefHoogsteKaart(speler1.getKaartInHand().toString(), speler2.getKaartInHand().toString()), speler1.getKaartInHand().toString())){
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

    public String bepaalWinnaar(){


        if (speler1.getScore() > speler2.getScore()){
            return speler1.getNaam();
        }else if (speler1.getScore() < speler2.getScore()){
            return speler2.getNaam();
        }else{
            return "gelijkspel";
        }
    }




}
