package logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
/**
 * KandidaatTest
 *
 * @author kristien.vanassche
 * @version 11/03/2024
 */
class KandidaatTest {
    Kandidaat kandidaat;

    @BeforeEach
    void setUp() {
        kandidaat = new Kandidaat("Jan Janssens");
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Kandidaat(""));
    }

    @Test
    void testDatavelden() {
        Field[] velden = Kandidaat.class.getDeclaredFields();
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("naam") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(String.class)));
        assertTrue(Arrays.stream(velden).anyMatch(v->v.getName().equals("aantalStemmen") && Modifier.isPrivate(v.getModifiers()) && v.getType().equals(int.class)));
    }

    @Test
    void getters() {
        assertEquals("Jan Janssens", kandidaat.getNaam());
        assertEquals(0, kandidaat.getAantalStemmen());
    }

    @Test
    void voegStemToe() {
        assertEquals(0, kandidaat.getAantalStemmen());
        for (int i = 1; i < 10; i++) {
            kandidaat.voegStemToe();
            assertEquals(i, kandidaat.getAantalStemmen());
        }
    }

    @Test
    void testToString() {
        assertTrue(kandidaat.toString().startsWith("Jan Janssens (0 stem"));
        kandidaat.voegStemToe();
        assertTrue(kandidaat.toString().startsWith("Jan Janssens (1 stem"));
        kandidaat.voegStemToe();
        assertEquals("Jan Janssens (2 stemmen)", kandidaat.toString());
    }
}