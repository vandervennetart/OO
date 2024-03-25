package logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * VerkiezingTest
 *
 * @author kristien.vanassche
 * @version 11/03/2024
 */
class VerkiezingTest {
    private Verkiezing verkiezing;

    @BeforeEach
    void setUp() {
        String[] kandidaten = new String[] {"kand1", "kand2", "kand3"};
        verkiezing = new Verkiezing("titel verkiezing", kandidaten);
    }

    @Test
    void testConstructor() {
        assertEquals(Status.OPEN, verkiezing.getStatus());
        assertEquals(0, verkiezing.getDeelnemers().size());
        assertEquals(4, verkiezing.getKandidaten().length);
        for (Kandidaat kandidaat : verkiezing.getKandidaten()) {
            String naam = kandidaat.getNaam();
            assertTrue(naam.startsWith("kand") || naam.equals("ongeldig"));
            assertEquals(0, kandidaat.getAantalStemmen());
        }

        assertEquals(2, new Verkiezing("titel", new String[]{"kandidaat"}).getKandidaten().length);
    }

    @Test
    void testConstructorBis() {
        String[] namen = {"A", "B", "C", "D"};
        assertThrows(IllegalArgumentException.class,
                () -> new Verkiezing("titel verkiezing", namen));
        assertThrows(IllegalArgumentException.class,
                () -> new Verkiezing(LocalDate.of(2024, 2, 2), "titel verkiezing", namen));
    }

    @Test
    void testDatavelden() {
        Field[] velden = Verkiezing.class.getDeclaredFields();
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("DATUM") && Modifier.isPrivate(v.getModifiers()) && Modifier.isFinal(v.getModifiers()) && v.getType().equals(LocalDate.class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("TITEL") && Modifier.isPrivate(v.getModifiers()) && Modifier.isFinal(v.getModifiers()) && v.getType().equals(String.class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("status") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(Status.class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("kandidaten") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(Kandidaat[].class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("deelnemers") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(ArrayList.class)));
    }

    @Test
    void geefNaamKandidaat() {
        for (int i = 0; i < 10; i++) {
            switch(i) {
                case 0,1,2 -> assertEquals("kand"+(i+1), verkiezing.geefNaamKandidaat(i));
                case 3 -> assertEquals("ongeldig", verkiezing.geefNaamKandidaat(i));
                default -> assertNull(verkiezing.geefNaamKandidaat(i));
            }
        }
    }

    @Test
    void geefStem() {
        doeEenStemRonde();
        assertEquals(10,verkiezing.getDeelnemers().size());

        for (int i = 0; i < verkiezing.getKandidaten().length; i++) {
            assertEquals(i < verkiezing.getKandidaten().length - 1 ? 1 : 7,
                    verkiezing.getKandidaten()[i].getAantalStemmen());
        }

        assertFalse(verkiezing.geefStem("deeln1", "kand1"));
        assertTrue(verkiezing.geefStem("deeln11", "kand1"));
        assertTrue(verkiezing.geefStem("deeln12", "kand11")); //ongeldige stem telt mee als stem
        assertEquals(12,verkiezing.getDeelnemers().size());
        assertEquals(2,verkiezing.getKandidaten()[0].getAantalStemmen());
        assertEquals(8,verkiezing.getKandidaten()[3].getAantalStemmen());
    }

    private void doeEenStemRonde() {
        for (int i = 0; i < 10; i++) {
            assertTrue(verkiezing.geefStem("deeln"+(i+1), "kand"+(i+1)));
        }
    }

    private void doeEenSemiRandomStemRonde() {
        for (int i = 0; i < 100; i++) {
            String deelnemer = "Deelnemer"+(i+1);
            boolean res;
            if (i % 20 == 0) {
                res = verkiezing.geefStem(deelnemer, "xxx");
            }
            else {
                res = verkiezing.geefStem(deelnemer,
                        verkiezing.geefNaamKandidaat((int) (Math.random() * verkiezing.getKandidaten().length - 1)));
            }
            assertTrue(res);
        }
    }

    @Test
    void sluitVerkiezing() {
        assertEquals(Status.OPEN, verkiezing.getStatus());
        assertTrue(verkiezing.geefStem("deeln1", "kand1"));
        assertEquals(1,verkiezing.getDeelnemers().size());
        assertEquals(1,verkiezing.getKandidaten()[0].getAantalStemmen());

        verkiezing.sluitVerkiezing();
        assertEquals(Status.GESLOTEN, verkiezing.getStatus());
        assertFalse(verkiezing.geefStem("deeln2", "kand1"));
        assertEquals(1,verkiezing.getDeelnemers().size());
        assertEquals(1,verkiezing.getKandidaten()[0].getAantalStemmen());
    }

    @Test
    void geefWinnaarsSpeciaal() {
        assertNull(verkiezing.geefWinnaars());
        assertEquals(0, verkiezing.sluitVerkiezing().size());
    }

    @Test
    void geefWinnaars() {
        doeEenStemRonde();
        assertNull(verkiezing.geefWinnaars());
        assertEquals(3, verkiezing.sluitVerkiezing().size());

        for(int i = 0; i < verkiezing.geefWinnaars().size(); i++) {
            assertEquals("kand"+(i+1), verkiezing.geefWinnaars().get(i));
        }
    }

    @Test
    void geefWinnaarsSemiRandom() {
        doeEenSemiRandomStemRonde();
        assertEquals(5, verkiezing.getKandidaten()[3].getAantalStemmen());
        assertNull(verkiezing.geefWinnaars());
        assertFalse(verkiezing.sluitVerkiezing().isEmpty());

        for(int i = 0; i < verkiezing.geefWinnaars().size(); i++) {
            assertTrue(verkiezing.geefWinnaars().get(i).startsWith("kand"));
        }
    }

    @Test
    void testToString() {
        assertTrue(verkiezing.toString().contains("titel verkiezing"));
        assertTrue(verkiezing.toString().contains(LocalDate.now().toString()));
        assertTrue(verkiezing.toString().contains("OPEN"));
        for (Kandidaat kandidaat : verkiezing.getKandidaten()) {
            assertTrue(verkiezing.toString().contains(kandidaat.toString()));
            assertTrue(verkiezing.toString().contains(kandidaat.getNaam() + " (0 stem"));
        }

        doeEenStemRonde();

        for (Kandidaat kandidaat : verkiezing.getKandidaten()) {
            if (kandidaat.getNaam().equals("ongeldig")) {
                assertTrue(verkiezing.toString().contains("ongeldig (7 stem"));
            }
            else {
                assertTrue(verkiezing.toString().contains(kandidaat.getNaam() + " (1 stem"));
            }
        }

        verkiezing.sluitVerkiezing();
        assertFalse(verkiezing.toString().contains("OPEN"));
        assertTrue(verkiezing.toString().contains("GESLOTEN"));
    }
}