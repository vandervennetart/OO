package presentatie;

import logica.Kandidaat;
import logica.Verkiezing;

public class DemoVerkiezing {
    public static void main(String[] args) {

        String[] kandidaten = new String[]{"Jan Janssen", "Dirk Dirksen", "Robert Robertson", "Marie Marien", "Ilse Ilsen"};
        Verkiezing verkiezing = new Verkiezing("Verkiezing van het Vlaamse Parlement", kandidaten);

        for (int i = 1; i < 101; i++) {
            if(i%20 == 0){
                verkiezing.geefStem(String.valueOf(i), "a");
            }else{
                verkiezing.geefStem(String.valueOf(i), verkiezing.geefNaamKandidaat((int) Math.floor(Math.random() * verkiezing.getKandidaten().length) ));
            }
        }
        verkiezing.geefStem("1", "a");

        System.out.println(verkiezing);
        System.out.println("\nWinnaar(s):" + verkiezing.sluitVerkiezing());


    }
}
