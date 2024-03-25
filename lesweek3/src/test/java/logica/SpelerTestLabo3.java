package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SpelerTest
 *
 * @author kristien.vanassche
 * @version 03/03/2024
 */
class SpelerTestLabo3 {
    private Speler speler;

    public SpelerTestLabo3() {
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
        Kaart kaart = speler.kiesRandomKaart();
        assertEquals(kaart, speler.getKaartInHand());
    }

    @Test
    void getKaartInHandBis() {
        speler.setKaartboek(new KaartBoek(new KaartSoort[]{KaartSoort.RUITEN}));
        Kaart kaart = speler.kiesRandomKaart();
        assertEquals(KaartSoort.RUITEN, kaart.getSoort());
        assertEquals(kaart, speler.getKaartInHand());

        KaartSoort[] soorten = KaartSoort.values();
        for (int i = 0; i < soorten.length; i++) {
            speler.setKaartboek(new KaartBoek(new KaartSoort[]{soorten[i]}));
            kaart = speler.kiesRandomKaart();
            assertEquals(soorten[i], kaart.getSoort());
            assertEquals(kaart, speler.getKaartInHand());
        }
    }

    @Test
    void toStringTest() {
        Speler speler = new Speler("Kristien");
        assertEquals("Kristien: 0", speler.toString());
        for (int i = 0; i < 10; i++) {
            speler.incrementScore();
            assertEquals("Kristien: " + (i+1), speler.toString());
        }
    }
}