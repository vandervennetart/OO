package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KaartBoekTest
 *
 * @author kristien.vanassche
 * @version 25/02/2024
 */
class KaartBoekTest {
    private KaartBoek boek1, boek2, boek3, boek4, boek5, boek6;

    private KaartBoekTest() {
        boek1 = new KaartBoek();

        boek2 = new KaartBoek(new char[]{'H', 'R'});
        boek3 = new KaartBoek(new char[]{'H', 'R', 'S'});
        boek4 = new KaartBoek(new char[]{'H', '*'});

        boek5 = new KaartBoek(new String[]{"H7", "KT" });
        boek6 = new KaartBoek(new String[]{"H7", "**" });
    }

    @Test
    void defaultConstructorBoek1() {
        assertNotNull(boek1);
        assertEquals(52, boek1.AANTAL);
        assertEquals(52, boek1.kaarten.length);
    }

    @Test
    void nietDefaultConstructorBoek2() {
        assertNotNull(boek2);
        assertEquals(13 * 2, boek2.AANTAL);
        assertEquals(13 * 2, boek2.kaarten.length);
    }

    @Test
    void nietDefaultConstructorBoek3() {
        assertNotNull(boek3);
        assertEquals(13 * 3, boek3.AANTAL);
        assertEquals(13 * 3, boek3.kaarten.length);
    }

    @Test
    void nietDefaultConstructorBoek4() {
        assertNotNull(boek4);
        assertEquals(13, boek4.AANTAL);
        assertEquals(13, boek4.kaarten.length);
    }

    @Test
    void nietDefaultConstructorBoek5() {
        assertNotNull(boek5);
        assertEquals(2, boek5.kaarten.length);
    }

    @Test
    void bevatKaartBoek1() {
        String soorten = "HSKR";
        String waarden = "23456789TBDHA";

        for (int i = 0; i < soorten.length(); i++) {
            for (int j = 0; j < waarden.length(); j++) {
                assertTrue(boek1.bevatKaart("" + soorten.charAt(i) + waarden.charAt(j)));
            }
        }
        assertFalse(boek1.bevatKaart("H10"));
    }

    @Test
    void bevatKaartBoek2() {
        String waarden = "23456789TBDHA";
        char waarde;
        for (int j = 0; j < waarden.length(); j++) {
            waarde = waarden.charAt(j);
            assertTrue(boek2.bevatKaart("H" + waarde));
            assertTrue(boek2.bevatKaart("R" + waarde));
            assertFalse(boek2.bevatKaart("S" + waarde));
            assertFalse(boek2.bevatKaart("K" + waarde));
        }
    }

    @Test
    void bevatKaartVanSoortBoek1() {
        assertTrue(boek1.bevatKaartVanSoort('H'));
        assertTrue(boek1.bevatKaartVanSoort('K'));
        assertTrue(boek1.bevatKaartVanSoort('R'));
        assertTrue(boek1.bevatKaartVanSoort('S'));
        assertFalse(boek1.bevatKaartVanSoort('*'));
    }

    @Test
    void bevatKaartVanSoortBoek2() {
        assertTrue(boek2.bevatKaartVanSoort('H'));
        assertFalse(boek2.bevatKaartVanSoort('K'));
        assertTrue(boek2.bevatKaartVanSoort('R'));
        assertFalse(boek2.bevatKaartVanSoort('S'));
        assertFalse(boek2.bevatKaartVanSoort('*'));
    }

    @Test
    void geefAlleKaartenVanSoortBoek1() {
        assertEquals(13, boek1.geefAlleKaartenVanSoort('H').length);
        assertEquals(13, boek1.geefAlleKaartenVanSoort('K').length);
        assertEquals(13, boek1.geefAlleKaartenVanSoort('R').length);
        assertEquals(13, boek1.geefAlleKaartenVanSoort('S').length);
        assertEquals(0, boek1.geefAlleKaartenVanSoort('*').length);

        int sumIdx = 0;
        for (Kaart k : boek1.geefAlleKaartenVanSoort('H')) {
            assertEquals('H', k.getSoort());
            assertTrue("23456789TBDHA".indexOf(k.getWaarde()) >= 0);
            sumIdx += "23456789TBDHA".indexOf(k.getWaarde());
        }
        assertEquals(78, sumIdx);
    }

    @Test
    void geefAlleKaartenVanSoortBoek2() {
        assertEquals(13, boek2.geefAlleKaartenVanSoort('H').length);
        assertEquals(0, boek2.geefAlleKaartenVanSoort('K').length);
        assertEquals(13, boek2.geefAlleKaartenVanSoort('R').length);
        assertEquals(0, boek2.geefAlleKaartenVanSoort('S').length);
        assertEquals(0, boek2.geefAlleKaartenVanSoort('*').length);
    }

    @Test
    void geefAlleKaartenVanSoortBoek5() {
        assertEquals(1, boek5.geefAlleKaartenVanSoort('H').length);
        assertEquals(1, boek5.geefAlleKaartenVanSoort('K').length);
        assertEquals(0, boek5.geefAlleKaartenVanSoort('R').length);
        assertEquals(0, boek5.geefAlleKaartenVanSoort('S').length);
        assertEquals(0, boek5.geefAlleKaartenVanSoort('*').length);
    }

    @Test
    void geefRandomKaartBoek1() {
        Kaart randomKaart;

        for (int i = 0; i < 1000; i++) {
            randomKaart = boek1.geefRandomKaart();
            assertTrue("HKRS".indexOf(randomKaart.getSoort()) >= 0);
            assertTrue("23456789TBDHA".indexOf(randomKaart.getWaarde()) >= 0);
        }
    }

    @Test
    void geefRandomKaartBoek2() {
        Kaart randomKaart;

        for (int i = 0; i < 1000; i++) {
            randomKaart = boek2.geefRandomKaart();
            assertTrue("HR".indexOf(randomKaart.getSoort()) >= 0);
            assertTrue("23456789TBDHA".indexOf(randomKaart.getWaarde()) >= 0);
        }
    }

    @Test
    void geefRandomKaartBoek4() {
        Kaart randomKaart;
        for (int i = 0; i < 1000; i++) {
            randomKaart = boek4.geefRandomKaart();
            assertEquals('H', randomKaart.getSoort());
            assertTrue("23456789TBDHA".indexOf(randomKaart.getWaarde()) >= 0);
        }
    }

    @Test
    void geefRandomKaartBoek5() {
        Kaart randomKaart;
        for (int i = 0; i < 1000; i++) {
            randomKaart = boek5.geefRandomKaart();
            assertTrue(randomKaart.toString().equals("H7") || randomKaart.toString().equals("KT"));
        }
    }

    @Test
    void geefRandomKaartVanSoortBoek1() {
        Kaart kaart = boek1.geefRandomKaartVanSoort('H');
        assertEquals('H', kaart.getSoort());
        assertTrue("23456789TBDHA".indexOf(kaart.getWaarde()) >= 0);
    }

    @Test
    void geefRandomKaartVanSoortBoek2() {
        Kaart kaart = boek2.geefRandomKaartVanSoort('S');
        assertEquals(null, kaart);
    }

    @Test
    void geefRandomKaartVanSoortBoek5() {
        assertEquals("H7", boek5.geefRandomKaartVanSoort('H').toString());
        assertEquals("KT", boek5.geefRandomKaartVanSoort('K').toString());
        assertEquals(null, boek5.geefRandomKaartVanSoort('R'));
        assertEquals(null, boek5.geefRandomKaartVanSoort('S'));
    }

    @Test
    void controleBoek6() {
        assertNotNull(boek6);
        assertEquals(1, boek6.kaarten.length);
        assertEquals(1, boek6.geefAlleKaartenVanSoort('H').length);
        assertEquals("H7", boek6.geefRandomKaartVanSoort('H').toString());
        assertTrue(boek6.bevatKaart("H7"));
    }
}