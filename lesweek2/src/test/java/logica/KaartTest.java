package logica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * KaartTest
 *
 * @author kristien.vanassche
 * @version 25/2/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KaartTest {
    private Kaart kaart1, kaart2, kaart3, kaart4, kaart5, kaart6;

    @BeforeAll
    void init() {
        kaart1 = new Kaart('H', '7');
        kaart2 = new Kaart("H8");
        kaart3 = new Kaart('H', '8');
        kaart4 = new Kaart('S', '8');
        kaart5 = new Kaart("Xx");
        kaart6 = new Kaart("Hallo");
    }

    @Test
    void isGeldigeSoort() {
        String geldigeSoorten = "";
        for (char c = 'A'; c <= 'Z'; c++) {
            if (Kaart.isGeldigeSoort(c)) {
                geldigeSoorten += c;
            }
        }
        assertEquals("HKRS", geldigeSoorten);
    }

    @Test
    void isGeldigeWaarde1() {
        String geldigeWaarden = "";
        for (char c = '0'; c <= 'Z'; c++) {
            if (Kaart.isGeldigeWaarde(c)) {
                geldigeWaarden += c;
            }
        }
        assertEquals("23456789ABDHT", geldigeWaarden);
    }

    @Test
    void testIsGeldigeWaarde2() {
        assertFalse(Kaart.isGeldigeWaarde('1'));
        for (int i = 2; i <= 9; i++) {
            assertTrue(Kaart.isGeldigeWaarde((char)('0'+i)));
        }
        assertTrue(Kaart.isGeldigeWaarde('T'));
        assertTrue(Kaart.isGeldigeWaarde('B'));
        assertTrue(Kaart.isGeldigeWaarde('D'));
        assertTrue(Kaart.isGeldigeWaarde('H'));
        assertTrue(Kaart.isGeldigeWaarde('A'));
        assertFalse(Kaart.isGeldigeWaarde('X'));
    }

    @Test
    void testIsGeldigeKaart1() {
        assertFalse(Kaart.isGeldigeKaart("A1"));
        assertFalse(Kaart.isGeldigeKaart("H1"));
        assertFalse(Kaart.isGeldigeKaart("HJ"));
        assertTrue(Kaart.isGeldigeKaart("HT"));
        assertTrue(Kaart.isGeldigeKaart("H2"));
        assertTrue(Kaart.isGeldigeKaart("HA"));
        assertFalse(Kaart.isGeldigeKaart("H*"));
        assertFalse(Kaart.isGeldigeKaart("H"));
        assertFalse(Kaart.isGeldigeKaart(""));
    }

    @Test
    void testIsGeldigeKaart2() {
        assertTrue(Kaart.isGeldigeKaart(kaart1.toString()));
        assertTrue(Kaart.isGeldigeKaart(kaart2.toString()));
        assertTrue(Kaart.isGeldigeKaart(kaart3.toString()));
        assertFalse(Kaart.isGeldigeKaart(kaart5.toString()));
        assertFalse(Kaart.isGeldigeKaart(kaart6.toString()));
    }

    @Test
    void testGetSoort() {
        assertEquals('H', kaart1.getSoort());
        assertEquals('H', kaart2.getSoort());
        assertEquals('S', kaart4.getSoort());
    }

    @Test
    void testGetWaarde() {
        assertEquals('7', kaart1.getWaarde());
        assertEquals('8', kaart2.getWaarde());
        assertEquals('8', kaart3.getWaarde());
    }

    @Test
    void geefHoogsteKaart1() {
        assertEquals(kaart2.toString(), Kaart.geefHoogsteKaart(kaart1.toString(), kaart2.toString()));
        assertEquals(kaart4.toString(), Kaart.geefHoogsteKaart(kaart3.toString(), kaart4.toString()));
        assertEquals("gelijk", Kaart.geefHoogsteKaart(kaart2.toString(), kaart3.toString()));
        assertEquals("gelijk", Kaart.geefHoogsteKaart(kaart3.toString(), kaart3.toString()));
    }

    @Test
    void geefHoogsteKaart2() {
        String soorten = "SRHK";
        String waarden = "AHDBT98765432";
        String[] kaarten = {
                "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "HT", "HB", "HD", "HH", "HA",
                "K2", "K3", "K4", "K5", "K6", "K7", "K8", "K9", "KT", "KB", "KD", "KH", "KA",
                "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "RT", "RB", "RD", "RH", "RA",
                "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "ST", "SB", "SD", "SH", "SA"
        };

        String kaart1;
        String kaart2;
        int aantalHoger;

        for (int w = 0; w < waarden.length(); w++) {
            kaart1 = "S" + waarden.charAt(w);
            aantalHoger = 0;
            for (int i = 0; i < kaarten.length; i++) {
                kaart2 = kaarten[i];
                if (!kaart1.equals(kaart2) && Kaart.geefHoogsteKaart(kaart1, kaart2).equals(kaart2)) {
                    aantalHoger++;
                }
            }
            System.out.printf("k1=%s aantalHoger=%d\n", kaart1, aantalHoger);
            assertEquals(w, aantalHoger);
        }

        for (int s = 1; s < soorten.length(); s++) {
            for (int w = 0; w < waarden.length(); w++) {
                kaart1 = "" + soorten.charAt(s) + waarden.charAt(w);
                aantalHoger = 0;
                for (int i = 0; i < kaarten.length; i++) {
                    kaart2 = kaarten[i];
                    if (!kaart1.equals(kaart2) &&
                            Kaart.geefHoogsteKaart(kaart1, kaart2).equals(kaart2)) {
                        aantalHoger++;
                    }
                }
                assertEquals(13 + 3*w, aantalHoger);
            }
        }
    }

    @Test
    void testToString() {
        assertEquals(kaart1.toString(), "H7");
        assertNotEquals(kaart1.toString(), kaart2.toString());
        assertEquals(kaart2.toString(), kaart3.toString());
    }

    @Test
    void testIsGeldig() {
        assertTrue(kaart1.isGeldig());
        assertTrue(kaart2.isGeldig());
        assertTrue(kaart3.isGeldig());
        assertFalse(kaart5.isGeldig());
        assertFalse(kaart6.isGeldig());
    }


}