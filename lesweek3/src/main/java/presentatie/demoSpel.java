package presentatie;


import logica.KaartSpel;

import java.util.ArrayList;

public class demoSpel {
    public static void main(String[] args) {
        KaartSpel spel = new KaartSpel("Piet", "Jan", 3);

        KaartSpel[] a = new KaartSpel[1];


        System.out.println("==Start spel==");

        spel.getSpeler1().resetScore();
        spel.getSpeler2().resetScore();
        spel.bepaalWieMagStarten();

        System.out.println(spel.getSpelerAanZet().getNaam() + " mag beginnen");
        System.out.println("Het spel wordt in " + spel.getAantalRondes() + " rondes gespeeld");

        for (int i = 0; i < spel.getAantalRondes(); i++) {
            spel.speelRonde();
            System.out.println(spel.getSpeler1().getNaam() + " gooit " + spel.getSpeler1().getKaartInHand());
            System.out.println(spel.getSpeler2().getNaam() + " gooit " + spel.getSpeler2().getKaartInHand());

            System.out.println("Sorebord: " + spel.getSpeler1() +" ; "+ spel.getSpeler2());
        }

        System.out.println("En de winnaar is..." + spel.bepaalWinnaar().getNaam());

        System.out.println("==Einde spel==");

    }
}
