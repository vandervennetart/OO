package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KaartSpelTest
 *
 * @author kristien.vanassche
 * @version 03/03/2024
 */
class KaartSpelTestLabo3 {
    private KaartSpel kaartspel, kaartspelDummyNames;
    private static final int AANTAL_RONDES = 5;

    public KaartSpelTestLabo3() {
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
        assertEquals(52, kaartspel.getKaarten().size());
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        kaartspel.speelRonde();
        assertTrue(s1.getScore() == 1 || s2.getScore() == 1);
        assertEquals(50, kaartspel.getKaarten().size());
    }

    @Test
    void speelRondes() {
        int aantal = kaartspel.getKaarten().size();
        kaartspel.bepaalWieMagStarten();
        Speler s1 = kaartspel.getSpeler1();
        Speler s2 = kaartspel.getSpeler2();
        for (int i = 0; i < AANTAL_RONDES; i++) {
            kaartspel.speelRonde();
            assertEquals(i+1, s1.getScore() + s2.getScore());
            assertEquals(aantal - 2*(i+1), kaartspel.getKaarten().size());
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
            Kaart hoogsteKaart = Kaart.geefHoogsteKaart(s1.getKaartInHand(), s2.getKaartInHand());
            if (s1.getKaartInHand().getSoort().equals(hoogsteKaart.getSoort()) &&
                    s1.getKaartInHand().getWaarde().equals(hoogsteKaart.getWaarde())) {
                score1++;
            }
            else if (s2.getKaartInHand().getSoort().equals(hoogsteKaart.getSoort()) &&
                    s2.getKaartInHand().getWaarde().equals(hoogsteKaart.getWaarde())) {
                score2++;
            }
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
    void bepaalWinnaar1() {
        Speler winnaar = kaartspel.bepaalWinnaar();
        assertEquals(null, winnaar);

        kaartspel.speelSpel();
        winnaar = kaartspel.bepaalWinnaar();
        assertTrue(winnaar.equals(kaartspel.getSpeler1())
                || winnaar.equals(kaartspel.getSpeler2())
                || winnaar == null);
    }

    @Test
    void bepaalWinnaar2() {
        assertNull(kaartspel.bepaalWinnaar());
        kaartspel.getSpeler1().incrementScore();
        assertEquals(kaartspel.getSpeler1(), kaartspel.bepaalWinnaar());
        kaartspel.getSpeler2().incrementScore();
        assertNull(kaartspel.bepaalWinnaar());
        kaartspel.getSpeler2().incrementScore();
        assertEquals(kaartspel.getSpeler2(), kaartspel.bepaalWinnaar());
    }
}