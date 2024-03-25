package logica;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KaartBoekTest
 *
 * @author kristien.vanassche
 * @version 03/03/2024
 */
class KaartBoekTestLabo3 {
    private KaartBoek boek1, boek2, boek3, boek5, boek6;

    private KaartBoekTestLabo3() {
        boek1 = new KaartBoek();

        boek2 = new KaartBoek(new KaartSoort[] {KaartSoort.HARTEN, KaartSoort.RUITEN});
        boek3 = new KaartBoek(new KaartSoort[] {KaartSoort.HARTEN, KaartSoort.RUITEN, KaartSoort.SCHOPPEN});

        boek5 = new KaartBoek(new String[]{"H7", "KT" });
        boek6 = new KaartBoek(new String[]{"H7", "**" });

        Field[] velden = KaartBoek.class.getDeclaredFields();
        assertEquals(1, velden.length);
        assertTrue(Modifier.isPrivate(velden[0].getModifiers()));
        assertEquals("kaarten",(velden[0].getName()));
        //assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("kaarten") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(ArrayList.class)));
    }

    @Test
    void defaultConstructorBoek1() {
        assertNotNull(boek1);
        assertEquals(52, boek1.getKaarten().size());
    }

    @Test
    void nietDefaultConstructorBoek2() {
        assertNotNull(boek2);
        assertEquals(13 * 2, boek2.getKaarten().size());
    }

    @Test
    void nietDefaultConstructorBoek3() {
        assertNotNull(boek3);
        assertEquals(13 * 3, boek3.getKaarten().size());
    }

    @Test
    void nietDefaultConstructorBoek5() {
        assertNotNull(boek5);
        assertEquals(2, boek5.getKaarten().size());
    }

    @Test
    void bevatKaartUitBoek1() {
        for (KaartSoort soort : KaartSoort.values()) {
            for (KaartWaarde waarde : KaartWaarde.values()) {
                assertTrue(boek1.bevatKaart(new Kaart(soort, waarde)));
            }
        }
    }

    @Test
    void bevatKaartUitBoek2() {
        for (KaartWaarde waarde : KaartWaarde.values()) {
            assertTrue(boek2.bevatKaart(new Kaart(KaartSoort.HARTEN, waarde)));
            assertTrue(boek2.bevatKaart(new Kaart(KaartSoort.RUITEN, waarde)));
            assertFalse(boek2.bevatKaart(new Kaart(KaartSoort.SCHOPPEN, waarde)));
            assertFalse(boek2.bevatKaart(new Kaart(KaartSoort.KLAVEREN, waarde)));
        }
    }

    @Test
    void bevatKaartVanSoortUitBoek1() {
        assertTrue(boek1.bevatKaartVanSoort(KaartSoort.HARTEN));
        assertTrue(boek1.bevatKaartVanSoort(KaartSoort.KLAVEREN));
        assertTrue(boek1.bevatKaartVanSoort(KaartSoort.RUITEN));
        assertTrue(boek1.bevatKaartVanSoort(KaartSoort.SCHOPPEN));
    }

    @Test
    void bevatKaartVanSoortUitBoek2() {
        assertTrue(boek2.bevatKaartVanSoort(KaartSoort.HARTEN));
        assertFalse(boek2.bevatKaartVanSoort(KaartSoort.KLAVEREN));
        assertTrue(boek2.bevatKaartVanSoort(KaartSoort.RUITEN));
        assertFalse(boek2.bevatKaartVanSoort(KaartSoort.SCHOPPEN));
    }

    @Test
    void geefAlleKaartenVanSoortUitBoek1() {
        assertEquals(13, boek1.geefAlleKaartenVanSoort(KaartSoort.HARTEN).size());
        assertEquals(13, boek1.geefAlleKaartenVanSoort(KaartSoort.KLAVEREN).size());
        assertEquals(13, boek1.geefAlleKaartenVanSoort(KaartSoort.RUITEN).size());
        assertEquals(13, boek1.geefAlleKaartenVanSoort(KaartSoort.SCHOPPEN).size());

        for (KaartSoort soort : KaartSoort.values()) {
            for (Kaart k : boek1.geefAlleKaartenVanSoort(soort)) {
                assertEquals(soort, k.getSoort());
                assertTrue(Arrays.toString(KaartWaarde.values()).contains(k.getWaarde().toString()));
                assertTrue(k.getWaarde().toString().length() >= 3);
            }
        }
    }

    @Test
    void geefAlleKaartenVanSoortUitBoek2() {
        assertEquals(13, boek2.geefAlleKaartenVanSoort(KaartSoort.HARTEN).size());
        assertEquals(0, boek2.geefAlleKaartenVanSoort(KaartSoort.KLAVEREN).size());
        assertEquals(13, boek2.geefAlleKaartenVanSoort(KaartSoort.RUITEN).size());
        assertEquals(0, boek2.geefAlleKaartenVanSoort(KaartSoort.SCHOPPEN).size());
    }

    @Test
    void geefAlleKaartenVanSoortUitBoek5() {
        assertEquals(1, boek5.geefAlleKaartenVanSoort(KaartSoort.HARTEN).size());
        assertEquals(1, boek5.geefAlleKaartenVanSoort(KaartSoort.KLAVEREN).size());
        assertEquals(0, boek5.geefAlleKaartenVanSoort(KaartSoort.RUITEN).size());
        assertEquals(0, boek5.geefAlleKaartenVanSoort(KaartSoort.SCHOPPEN).size());
    }

    @Test
    void geefRandomKaartUitBoek5() {
        Kaart randomKaart;
        for (int i = 0; i < 1000; i++) {
            randomKaart = boek5.geefRandomKaart();
            assertTrue(randomKaart.getSoort() == KaartSoort.HARTEN
                    || randomKaart.getSoort() == KaartSoort.KLAVEREN);
            assertTrue(randomKaart.getWaarde() == KaartWaarde.ZEVEN
                    || randomKaart.getWaarde() == KaartWaarde.TIEN);
        }
    }

    @Test
    void geefRandomKaartUitBoek1() {
        Kaart randomKaart;

        for (int i = 0; i < 1000; i++) {
            assertEquals(52, boek1.getKaarten().size());
            randomKaart = boek2.geefRandomKaart();
            assertTrue(randomKaart.getSoort() == KaartSoort.HARTEN || randomKaart.getSoort() == KaartSoort.RUITEN);
            assertTrue(Arrays.toString(KaartWaarde.values()).contains(randomKaart.getWaarde().toString()));
            assertTrue(randomKaart.getWaarde().toString().length() >= 3);
        }
    }

    @Test
    void geefRandomKaartVanSoortUitBoek1() {
        Kaart kaart = boek1.geefRandomKaartVanSoort(KaartSoort.HARTEN);
        assertEquals(KaartSoort.HARTEN, kaart.getSoort());
        assertTrue(kaart.getWaarde().toString().length() >= 3);
    }

    @Test
    void geefRandomKaartVanSoortUitBoek2() {
        assertNotNull(boek2.geefRandomKaartVanSoort(KaartSoort.HARTEN));
        assertNotNull(boek2.geefRandomKaartVanSoort(KaartSoort.RUITEN));
        assertNull(boek2.geefRandomKaartVanSoort(KaartSoort.SCHOPPEN));
        assertNull(boek2.geefRandomKaartVanSoort(KaartSoort.KLAVEREN));
    }

    @Test
    void controleBoek6() {
        assertNotNull(boek6);
        assertEquals(1, boek6.getKaarten().size());
        assertEquals("HARTEN ZEVEN", boek6.getKaarten().get(0).toString());

        assertEquals(1, boek6.geefAlleKaartenVanSoort(KaartSoort.HARTEN).size());
        assertEquals("HARTEN ZEVEN", boek6.geefRandomKaartVanSoort(KaartSoort.HARTEN).toString());

        Kaart kaart = boek6.getKaarten().get(0);
        assertEquals("HARTEN ZEVEN", kaart.toString());
        assertTrue(boek6.bevatKaart(kaart));
        assertTrue(boek6.bevatKaart(new Kaart(KaartSoort.HARTEN, KaartWaarde.ZEVEN)));
    }

    @Test
    void haalAlleKaartenVanSoortUitBoek1() {
        assertEquals(52, boek1.getKaarten().size());

        int i = 0;
        for (KaartSoort soort : KaartSoort.values()) {
            assertEquals(52 - (i++)*13, boek1.getKaarten().size());
            assertEquals(13, boek1.haalAlleKaartenVanSoort(soort).size());
            assertEquals(0, boek1.geefAlleKaartenVanSoort(soort).size());
        }
        assertEquals(0, boek1.getKaarten().size());
    }

    @Test
    void haalRandomKaartUitBoek1() {
        for (int i = 0; i < 52; i++) {
            assertEquals(52-i, boek1.getKaarten().size());
            boek1.haalRandomKaart();
        }
        assertEquals(0, boek1.getKaarten().size());
        assertNull(boek1.geefRandomKaart());
    }

    @Test
    void haalRandomKaartVanSoortUitBoek5() {
        assertEquals(2, boek5.getKaarten().size());
        assertEquals("HARTEN ZEVEN", boek5.haalRandomKaartVanSoort(KaartSoort.HARTEN).toString());
        assertEquals(1, boek5.getKaarten().size());
        assertEquals("KLAVEREN TIEN", boek5.haalRandomKaartVanSoort(KaartSoort.KLAVEREN).toString());
        assertEquals(0, boek5.getKaarten().size());

        for (KaartSoort soort: KaartSoort.values()) {
            assertNull(boek5.haalRandomKaartVanSoort(soort));
        }
    }
}