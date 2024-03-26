package logica;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @author tim.vermeulen
 * @version 22/03/2023
 */
public class CirkelTest {
    private Class<?> myClass;
    private Cirkel instance;
    private Cirkel c1, c2;

    public CirkelTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Cirkel");
        instance = new Cirkel(new Punt(1,2), 2.0);
    }

    @Test
    public void testFieldModifier() throws ClassNotFoundException {
        Field[] fields = myClass.getDeclaredFields();
        assertEquals(1, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }
    }

    @Test
    public void testConstructorNok1() {
        assertThrows(IllegalArgumentException.class, () -> new Cirkel(-3));
        assertThrows(IllegalArgumentException.class, () -> new Cirkel(new Punt(0,0), -3));
        assertThrows(IllegalArgumentException.class, () -> new Cirkel(-3, Kleur.BLAUW, Kleur.BLAUW, 2));
        assertThrows(IllegalArgumentException.class, () -> new Cirkel(new Punt(0,0), -3, Kleur.BLAUW, Kleur.BLAUW, 2));
        assertThrows(IllegalArgumentException.class, () -> new Cirkel(new Punt(-1,-1), 3, Kleur.BLAUW, Kleur.BLAUW, -2));
    }

    @Test
    public void testConstructor() {
        new Cirkel(new Punt(0, 0), 0);
        new Cirkel(new Punt(-1, -1), 0);
    }

    @Test
    public void testConstructorExtraParDefault() {
        assertNotNull(c1 = new Cirkel(new Punt(1,2), 3));
        assertEquals(Kleur.WIT, c1.getKleur());
        assertEquals(Kleur.ZWART, c1.getKleurRand());
        assertEquals(1, c1.getDikteRand());
        assertEquals(3, c1.getStraal());
    }

    @Test
    public void testConstructorExtraPar() {
        assertNotNull(c2 = new Cirkel(new Punt(3,4), 5, Kleur.BLAUW, Kleur.GEEL, 12));
        assertEquals(Kleur.BLAUW, c2.getKleur());
        assertEquals(Kleur.GEEL, c2.getKleurRand());
        assertEquals(12, c2.getDikteRand());
        assertEquals(5, c2.getStraal());
    }

    @Test
    public void testConstructorenDeel1Deel2Revised() {
        assertNotNull(c1 = new Cirkel(new Punt(-1,-5), 13));
        assertEquals(Kleur.WIT, c1.getKleur());
        assertEquals(Kleur.ZWART, c1.getKleurRand());
        assertEquals(1, c1.getDikteRand());
        assertEquals(13, c1.getStraal());
        assertEquals(new Punt(-1,-5), c1.getMiddelpunt());

        assertNotNull(c2 = new Cirkel(new Punt(10,11), 12, Kleur.BLAUW, Kleur.GEEL, 13));
        assertEquals(Kleur.BLAUW, c2.getKleur());
        assertEquals(Kleur.GEEL, c2.getKleurRand());
        assertEquals(13, c2.getDikteRand());
        assertEquals(12, c2.getStraal());
        assertEquals(new Punt(10,11), c2.getMiddelpunt());

        assertNotEquals(c1, c2);
    }

    @Test
    public void testConstructorDefault() {
        assertNotNull(c1 = new Cirkel());
        assertEquals(Kleur.WIT, c1.getKleur());
        assertEquals(Kleur.ZWART, c1.getKleurRand());
        assertEquals(1, c1.getDikteRand());
        assertEquals(50, c1.getStraal());
        assertEquals(new Punt(0,0), c1.getMiddelpunt());
    }

    @Test
    public void testConstructorenExtra() {
        assertEquals(new Cirkel(50),
                new Cirkel());

        //assertEquals(new Cirkel(123, Kleur.blauw, Kleur.geel, 12),
        //             new Cirkel(new Punt(0,0), 123, Kleur.blauw, Kleur.geel, 12));
    }

    @Test
    public void testGetterSetterMiddelpunt() {
        assertNotNull(c1 = new Cirkel());
        c1.setMiddelpunt(new Punt(1,2));
        assertEquals(new Punt(1,2), c1.getMiddelpunt());
    }

    @Test
    public void testBerekenAfstand() {
        Punt punt1 = new Punt(1, 0);
        Punt punt2 = new Punt(5, 2);
        assertEquals(2.0, instance.berekenAfstand(punt1));
        assertEquals(4.0, instance.berekenAfstand(punt2));
    }

    @Test
    public void testBerekenAfstandBis() {
        c1 = new Cirkel();
        c2 = new Cirkel();
        c2.setMiddelpunt(new Punt(1,2));

        assertEquals(Math.sqrt(5), c1.getMiddelpunt().berekenAfstand(c2.getMiddelpunt()));
        assertEquals(Math.sqrt(5), c1.berekenAfstand(c2));
        assertEquals(Math.sqrt(5), c1.berekenAfstand(c2.getMiddelpunt()));

        c1.setMiddelpunt(new Punt(10,2));
        assertEquals(9, c1.berekenAfstand(c2));
    }

    @Test
    public void testBerekenOmtrek() {
        double expResultOmtrek = 12.566370614359172;
        double resultOmtrek = instance.berekenOmtrek();
        assertEquals(expResultOmtrek, resultOmtrek);
    }

    @Test
    public void testBerekenOppervlakte() {
        double expResultOpp = 12.566370614359172;
        double resultOpp = instance.berekenOppervlakte();
        assertEquals(expResultOpp, resultOpp);
    }

    @Test
    public void testToString() {
        Cirkel c1 = new Cirkel(new Punt(1,2), 7.0);
        assertTrue(c1.toString().startsWith("Cirkel"));
        assertTrue(c1.toString().contains("WIT/ZWART"));
        assertTrue(c1.toString().contains("1cm"));
        assertTrue(c1.toString().contains("R=7.0cm"));
        assertTrue(c1.toString().contains("(1,2)"));

        assertNotEquals(instance.toString(), c1.toString());

        Cirkel c2 = new Cirkel(new Punt(1,2), 7.0);
        assertEquals(c1.toString(), c2.toString());
    }

    @Test
    public void testEquals() {
        c1 = new Cirkel(new Punt(1,2), 7.0);

        c2 = new Cirkel(new Punt(1,2), 7.0);
        assertEquals(c1, c2);

        c2 = new Cirkel(new Punt(1,2), 2.0);
        assertNotEquals(c1, c2);

        assertNotEquals(c2, new Object());
    }

    @Test
    public void testEqualsBis() {
        c1 = new Cirkel(new Punt(3,4), 5, Kleur.BLAUW, Kleur.GEEL, 12);

        c2 = new Cirkel(new Punt(3,4), 5, Kleur.WIT, Kleur.WIT, 12);
        assertNotEquals(c2, c1);

        c2 = new Cirkel(new Punt(3,4), 5, Kleur.BLAUW, Kleur.GEEL, 111);
        assertNotEquals(c2, c1);

        c2 = new Cirkel(new Punt(3,4), 5, Kleur.BLAUW, Kleur.GEEL, 12);
        assertEquals(c2, c1);

        c2 = new Cirkel(new Punt(3,4), 555, Kleur.BLAUW, Kleur.GEEL, 12);
        assertNotEquals(c2, c1);
    }

    @Test
    public void testEqualsDeel2() {
        assertEquals(new Cirkel(7), new Cirkel(new Punt(0, 0), 7.0));
        assertEquals(new Cirkel(7), new Cirkel(7));
        assertNotEquals(new Cirkel(7), new Cirkel(8));

        c1 = new Cirkel(5, Kleur.BLAUW, Kleur.GEEL, 12);
        c2 = new Cirkel(5, Kleur.BLAUW, Kleur.GEEL, 12);
        assertEquals(c1, c2);

        c2.setMiddelpunt(new Punt(1,3));
        assertNotEquals(c1, c2);
    }

    @Test
    public void testMethodSignatureEquals() throws ClassNotFoundException, NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("equals", Class.forName("java.lang.Object"));
        assertEquals(1, m.getParameterCount());
        assertEquals(Boolean.TYPE, m.getReturnType());
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertNotNull(m);
    }

    @Test
    public void testMethodSignatureToString() throws ClassNotFoundException, NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("toString");
        assertEquals(0, m.getParameterCount());
        assertEquals(Class.forName("java.lang.String"), m.getReturnType());
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertNotNull(m);
    }
}