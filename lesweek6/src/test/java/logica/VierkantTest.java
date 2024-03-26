package logica;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @author tim.vermeulen
 * @version 22/03/2023
 */
public class VierkantTest {
    private Class<?> c;
    private Vierkant v1, v2;

    public VierkantTest() throws ClassNotFoundException {
        c = Class.forName("logica.Vierkant");
    }

    @Test
    public void testFieldModifier() {
        Field[] fields = c.getDeclaredFields();
        assertEquals(1, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }
    }

    @Test
    public void testConstructorDefault() {
        assertNotNull(v1 = new Vierkant());
        assertEquals(Kleur.WIT, v1.getKleur());
        assertEquals(Kleur.ZWART, v1.getKleurRand());
        assertEquals(1, v1.getDikteRand());
        assertEquals(50, v1.getZijde());
        assertEquals(new Punt(0,0), v1.getMiddelpunt());
    }

    @Test
    public void testConstructorOrig() {
        assertNotNull(v1 = new Vierkant(new Punt(1,2), 3));
        assertEquals(Kleur.WIT, v1.getKleur());
        assertEquals(Kleur.ZWART, v1.getKleurRand());
        assertEquals(1, v1.getDikteRand());
        assertEquals(3, v1.getZijde());
        assertEquals(new Punt(1,2), v1.getMiddelpunt());
    }

    @Test
    public void testConstructorBis() {
        assertNotNull(v1 = new Vierkant(3));
        assertEquals(Kleur.WIT, v1.getKleur());
        assertEquals(Kleur.ZWART, v1.getKleurRand());
        assertEquals(1, v1.getDikteRand());
        assertEquals(3, v1.getZijde());
        assertEquals(new Punt(0,0), v1.getMiddelpunt());
    }

    @Test
    public void testConstructorTris() {
        assertNotNull(v1 = new Vierkant(13, Kleur.BLAUW, Kleur.GEEL, 12));
        assertEquals(Kleur.BLAUW, v1.getKleur());
        assertEquals(Kleur.GEEL, v1.getKleurRand());
        assertEquals(12, v1.getDikteRand());
        assertEquals(13, v1.getZijde());
        assertEquals(new Punt(0,0), v1.getMiddelpunt());
    }

    @Test
    public void testConstructorFull() {
        v1 = new Vierkant(new Punt(0,0), 10, Kleur.ZWART, Kleur.ZWART, 0);
    }

    @Test
    public void testConstructorNOK() {
        assertThrows(IllegalArgumentException.class, () -> new Vierkant(-13, Kleur.BLAUW, Kleur.GEEL, 12));
    }

    @Test
    public void testGetterSetter() {
        assertNotNull(v1 = new Vierkant());
        v1.setMiddelpunt(new Punt(1,2));
        assertEquals(new Punt(1,2), v1.getMiddelpunt());
    }

    @Test
    public void testEquals() {
        v1 = new Vierkant(7.0);
        v2 = new Vierkant(7.0);
        assertEquals(v1, v2);
        Vierkant instance = new Vierkant(2.0);
        assertNotEquals(instance, v1);

        assertNotEquals(instance, new Object());
    }

    @Test
    public void testEqualsBis() {
        Vierkant c1 = new Vierkant(5, Kleur.BLAUW, Kleur.GEEL, 12);

        Vierkant instance = new Vierkant(5, Kleur.WIT, Kleur.WIT, 12);
        assertNotEquals(instance, c1);
        instance = new Vierkant(5, Kleur.BLAUW, Kleur.GEEL, 111);
        assertNotEquals(instance, c1);
        instance = new Vierkant(5, Kleur.BLAUW, Kleur.GEEL, 12);
        assertEquals(instance, c1);
    }

    @Test
    public void testBerekenAfstand() {
        v1 = new Vierkant();
        v2 = new Vierkant();
        v2.setMiddelpunt(new Punt(2,3));

        assertEquals(Math.sqrt(13), v1.getMiddelpunt().berekenAfstand(v2.getMiddelpunt()));
        assertEquals(Math.sqrt(13), v1.berekenAfstand(v2));
        assertEquals(Math.sqrt(13), v1.berekenAfstand(v2.getMiddelpunt()));

        v1.setMiddelpunt(new Punt(-10,3));
        assertEquals(12, v1.berekenAfstand(v2));
    }

    @Test
    public void testBerekenOmtrek() {
        v1 = new Vierkant(new Punt(1,2), 3);
        assertEquals(12, v1.berekenOmtrek());
    }

    @Test
    public void testBerekenOppervlakte() {
        v1 = new Vierkant(new Punt(1,2), 3);
        assertEquals(9, v1.berekenOppervlakte());
    }

    @Test
    public void testToString() {
        v1 = new Vierkant(new Punt(1,2), 3);
        assertTrue(v1.toString().startsWith("Vierkant"));
        assertTrue(v1.toString().contains("WIT/ZWART"));
        assertTrue(v1.toString().contains("1cm"));
        assertTrue(v1.toString().contains("(1,2)"));
        assertTrue(v1.toString().contains("Z=3.0cm"));

        v2 = new Vierkant(new Punt(1,2), 3);
        assertEquals(v1.toString(), v2.toString());
    }
}