package logica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * KaartTestLabo3
 *
 * @author kristien.vanassche
 * @version 03/03/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KaartTestLabo3 {
    private Kaart kaart1, kaart2, kaart3, kaart4;

    @BeforeAll
    void init() {
        kaart1 = new Kaart(KaartSoort.HARTEN, KaartWaarde.ZEVEN);
        kaart2 = new Kaart("H8");
        kaart3 = new Kaart(KaartSoort.HARTEN, KaartWaarde.ACHT);
        kaart4 = new Kaart(KaartSoort.SCHOPPEN, KaartWaarde.ACHT);

        Field[] velden = Kaart.class.getDeclaredFields();
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("waarde") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(KaartWaarde.class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("soort") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(KaartSoort.class)));
    }

    @Test
    void ongeldigeKaart() {
        assertThrows(IllegalArgumentException.class, () -> new Kaart("Xx"));
        assertThrows(IllegalArgumentException.class, () -> new Kaart("Hallo"));

        assertThrows(IllegalArgumentException.class, () -> new Kaart("H1"));
        assertThrows(IllegalArgumentException.class, () -> new Kaart("AB"));
        assertThrows(Exception.class, () -> new Kaart("H"));
    }

    @Test
    void testGetSoort() {
        assertEquals(KaartSoort.HARTEN, kaart1.getSoort());
        assertEquals(KaartSoort.HARTEN, kaart2.getSoort());
        assertEquals(KaartSoort.SCHOPPEN, kaart4.getSoort());
    }

    @Test
    void testGetWaarde() {
        assertEquals(KaartWaarde.ZEVEN, kaart1.getWaarde());
        assertEquals(KaartWaarde.ACHT, kaart2.getWaarde());
        assertEquals(KaartWaarde.ACHT, kaart3.getWaarde());
    }

    @Test
    void isGelijkwaardigAan() {
        Kaart kaart1 = new Kaart("H3");
        assertTrue(kaart1.isGelijkwaardigAan(new Kaart("R3")));
        assertFalse(kaart1.isGelijkwaardigAan(new Kaart("S3")));

        kaart1 = new Kaart(KaartSoort.RUITEN, KaartWaarde.TIEN);
        assertTrue(kaart1.isGelijkwaardigAan(new Kaart(KaartSoort.KLAVEREN, KaartWaarde.TIEN)));
        assertTrue(kaart1.isGelijkwaardigAan(new Kaart(KaartSoort.RUITEN, KaartWaarde.TIEN)));
        assertFalse(kaart1.isGelijkwaardigAan(new Kaart(KaartSoort.SCHOPPEN, KaartWaarde.TIEN)));
    }

    @Test
    void geefHoogsteKaart1() {
        assertEquals(kaart2, Kaart.geefHoogsteKaart(kaart1, kaart2));
        assertEquals(kaart4, Kaart.geefHoogsteKaart(kaart3, kaart4));

        Kaart hoogste23 = Kaart.geefHoogsteKaart(kaart2, kaart3);
        assertTrue(hoogste23.getSoort() == KaartSoort.HARTEN && hoogste23.getWaarde() == KaartWaarde.ACHT);

        Kaart hoogste33 = Kaart.geefHoogsteKaart(kaart3, kaart3);
        assertTrue(hoogste33.getSoort() == KaartSoort.HARTEN && hoogste33.getWaarde() == KaartWaarde.ACHT);
    }

    @Test
    void geefHoogsteKaart2() {
        Kaart[] kaarten = new Kaart[52];
        int idx = 0;
        for (KaartSoort soort : KaartSoort.values()) {
            for (KaartWaarde waarde : KaartWaarde.values()) {
                kaarten[idx++] = new Kaart(soort,waarde);
            }
        }

        Kaart schoppenAas = new Kaart(KaartSoort.SCHOPPEN, KaartWaarde.AAS);
        for (Kaart kaart : kaarten) {
            assertEquals(schoppenAas.getSoort(), Kaart.geefHoogsteKaart(kaart, schoppenAas).getSoort());
            assertEquals(schoppenAas.getWaarde(), Kaart.geefHoogsteKaart(kaart, schoppenAas).getWaarde());
        }

        Kaart ruitenTwee = new Kaart(KaartSoort.RUITEN, KaartWaarde.TWEE);
        for (Kaart kaart : kaarten) {
            assertEquals(kaart, Kaart.geefHoogsteKaart(kaart, ruitenTwee));
        }
    }

    @Test
    void testToString() {
        assertEquals("HARTEN ZEVEN", kaart1.toString());
        assertEquals("HARTEN ACHT", kaart2.toString());
        assertEquals("HARTEN ACHT", kaart3.toString());
        assertEquals("SCHOPPEN ACHT", kaart4.toString());
    }
}