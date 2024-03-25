package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Verkiezing {

    private final LocalDate DATUM;
    private final String TITEL;
    private Status status = Status.OPEN;
    private Kandidaat[] kandidaten;
    private ArrayList<String> deelnemers = new ArrayList<>();

    public Verkiezing(LocalDate datum, String titel, String[] kandidaatenNamen) {
        this.DATUM = datum;
        this.TITEL = titel;
        this.kandidaten = new Kandidaat[kandidaatenNamen.length+1];

        int i = 0;
        for (String s : kandidaatenNamen) {
            this.kandidaten[i] = new Kandidaat(s);
            i++;
        }
        this.kandidaten[i] = new Kandidaat("ongeldig");
    }

    public Verkiezing(String titel, String[] kandidaatenNamen) {
        this.DATUM = LocalDate.now();
        this.TITEL = titel;
        this.kandidaten = new Kandidaat[kandidaatenNamen.length+1];

        int i = 0;
        for (String s : kandidaatenNamen) {
            this.kandidaten[i] = new Kandidaat(s);
            i++;
        }
        this.kandidaten[i] = new Kandidaat("ongeldig");
    }

    public Status getStatus() {
        return status;
    }

    public Kandidaat[] getKandidaten() {
        return kandidaten;
    }

    public ArrayList<String> getDeelnemers() {
        return deelnemers;
    }
    
    public String geefNaamKandidaat(int i){
        if (0 <= i && i < kandidaten.length){
            return kandidaten[i].getNaam();
        }
        else{
            return null;
        }

    }

    public boolean geefStem(String naamDeelnemer, String naamKandidaat){
        if ((!deelnemers.contains(naamDeelnemer)) && status == Status.OPEN){
            deelnemers.add(naamDeelnemer);
            for (Kandidaat kandidaat : kandidaten) {
                if (Objects.equals(kandidaat.getNaam(), naamKandidaat)) {
                    kandidaat.voegStemToe();
                    return true;
                }

            }
            kandidaten[kandidaten.length-1].voegStemToe();
            return true;

        }
        return false;
    }

    public ArrayList<String> sluitVerkiezing(){
        status = Status.GESLOTEN;
        return geefWinnaars();
    }

    public ArrayList<String> geefWinnaars(){

        if (status == Status.GESLOTEN){
            ArrayList<String> winnaars = new ArrayList<>();


            for (int j = 0; j < kandidaten.length-1; j++) {
                int i = 0;
                for (int k = 0; k < kandidaten.length-1; k++) {
                    if (kandidaten[j].getAantalStemmen() >= kandidaten[k].getAantalStemmen() && kandidaten[j].getAantalStemmen() > 0){
                        i++;
                    }
                }
                if (i == kandidaten.length-1){
                    if (!Objects.equals(kandidaten[j].getNaam(), "ongeldig")){
                        winnaars.add(kandidaten[j].getNaam());
                    }

                }
            }
            return winnaars;


        }else{
            return null;
        }




    }

    @Override
    public String toString() {
        String s = TITEL + " op " + DATUM + " (" + status + ")";
        for (Kandidaat kandidaat : kandidaten) {
            s += "\n" + kandidaat ;
        }



        return s;
    }
}
