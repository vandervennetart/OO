package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SpelerTest
 *
 * @author kristien.vanassche
 * @version 25/02/2024
 */
class SpelerTest {
    private Speler speler;

    public SpelerTest() {
        speler = new Speler("Kristien");
    }

    @Test
    void getNaam() {
        assertEquals("Kristien", speler.getNaam());
    }

    @Test
    void getScore() {
        assertEquals(0, speler.getScore());
        for (int i = 0; i < 10; i++) speler.incrementScore();
        assertEquals(10, speler.getScore());
        speler.resetScore();
        assertEquals(0, speler.getScore());
    }

    @Test
    void gooiDobbelsteen() {
        int worp = speler.gooiDobbelsteen();
        assertTrue(worp >= 1 && worp <= 6);
    }

    @Test
    void kiesRandomKaart() {
        speler.setKaartboek(new KaartBoek());
        String kaart = speler.kiesRandomKaart().toString();
        assertTrue(kaart.equals("") || Kaart.isGeldigeKaart(kaart));
        assertEquals(kaart, speler.getKaartInHand().toString());
    }

    @Test
    void getKaartInHand() {
        speler.setKaartboek(new KaartBoek());
        Kaart kaart = speler.kiesRandomKaart();
        assertEquals(kaart.toString(), speler.getKaartInHand().toString());
    }

    @Test
    void getKaartInHandBis() {
        speler.setKaartboek(new KaartBoek(new char[]{'R'}));
        Kaart kaart = speler.kiesRandomKaart();
        assertEquals('R', kaart.getSoort());
        assertEquals(kaart.toString(), speler.getKaartInHand().toString());

        String soorten = "HKRS";
        for (int i = 0; i < soorten.length(); i++) {
            speler.setKaartboek(new KaartBoek(new char[]{soorten.charAt(i)}));
            kaart = speler.kiesRandomKaart();
            assertEquals(soorten.charAt(i), kaart.getSoort());
            assertEquals(kaart.toString(), speler.getKaartInHand().toString());
        }
    }
}