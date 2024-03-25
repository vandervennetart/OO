package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KaartSpelTest
 *
 * @author kristien.vanassche
 * @version 25/02/2024
 */
class KaartSpelTest {
    private KaartSpel kaartspel, kaartspelDummyNames;
    private static final int AANTAL_RONDES = 5;

    public KaartSpelTest() {
        kaartspelDummyNames = new KaartSpel();
        kaartspel = new KaartSpel("Kristien", "Evert-Jan", AANTAL_RONDES);
    }

    @Test
    void getSpelernamen() {
        assertEquals("dummy1", kaartspelDummyNames.getSpeler1().getNaam());
        assertEquals("dummy2", kaartspelDummyNames.getSpeler2().getNaam());

        assertEquals("Kristien", kaartspel.getSpeler1().getNaam());
        assertEquals("Evert-Jan", kaartspel.getSpeler2().getNaam());
        assertEquals(0, kaartspel.getSpeler1().getScore());
        assertEquals(0, kaartspel.getSpeler2().getScore());
    }

    @Test
    void bepaalWieMagStartenDummyNames() {
        kaartspelDummyNames.bepaalWieMagStarten();
        Speler speler = kaartspelDummyNames.getSpelerAanZet();
        assertTrue(speler.getNaam().startsWith("dummy"));
    }

    @Test
    void bepaalWieMagStarten() {
        kaartspel.bepaalWieMagStarten();
        Speler speler = kaartspel.getSpelerAanZet();
        assertTrue(speler.getNaam().equals("Kristien")
                || speler.getNaam().equals("Evert-Jan"));
    }

    @Test
    void speelEenRonde() {
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        kaartspel.speelRonde();
        assertTrue(s1.getScore() == 1 || s2.getScore() == 1);
    }

    @Test
    void speelRondes() {
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        for (int i = 0; i < AANTAL_RONDES; i++) {
            kaartspel.speelRonde();
            assertEquals(i+1, s1.getScore() + s2.getScore());
        }
    }

    @Test
    void speelRondesBis() {
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        int score1=0, score2=0;

        for (int i = 0; i < AANTAL_RONDES; i++) {
            kaartspel.speelRonde();
            String hoogsteKaart = Kaart.geefHoogsteKaart(s1.getKaartInHand().toString(), s2.getKaartInHand().toString());
            if (s1.getKaartInHand().toString().equals(hoogsteKaart)) score1++;
            else if (s2.getKaartInHand().toString().equals(hoogsteKaart)) score2++;
            assertEquals(score1, s1.getScore());
            assertEquals(score2, s2.getScore());
        }
    }

    @Test
    void speelRondesTris() {
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        s1.resetScore();
        s2.resetScore();

        int count = 0;
        do {
            count++;
            kaartspel.speelRonde();
            if (count == 100) break;
        } while(s1.getScore() == 0 || s2.getScore() == 0);
        System.out.println("Na " + count + " rondes heeft elke speler minstens 1 punt behaald");
        assertNotEquals(100, count);
    }

    @Test
    void speelSpel() {
        kaartspel.speelSpel();
        assertEquals(AANTAL_RONDES,
                kaartspel.getSpeler1().getScore() + kaartspel.getSpeler2().getScore());
    }

    @Test
    void bepaalWinnaar() {
        String winnaar = kaartspel.bepaalWinnaar();
        assertEquals("gelijkspel", winnaar);

        kaartspel.speelSpel();
        winnaar = kaartspel.bepaalWinnaar();
        assertTrue(winnaar.equals(kaartspel.getSpeler1().getNaam())
                || winnaar.equals(kaartspel.getSpeler2().getNaam())
                || winnaar.equals("gelijkspel"));
    }
}