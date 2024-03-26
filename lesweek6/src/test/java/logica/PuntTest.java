package logica;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @version 17/03/2023
 */
public class PuntTest {
    private Class<?> myClass;
    private Punt punt1, punt2, punt3;

    public PuntTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Punt");
        punt1 = new Punt(12, 13);
        punt2 = new Punt(12, 13);
        punt3 = new Punt(12, 14);
    }

    @Test
    public void testFieldModifier() {
        Field[] fields = myClass.getDeclaredFields();
        assertEquals(2, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }
    }

    @Test
    public void testGetters() {
        assertEquals(12, punt1.getX());
        assertEquals(13, punt1.getY());
    }

    @Test
    public void testGettersSetters() {
        int delta = 9;
        assertEquals(12, punt1.getX());
        assertEquals(13, punt1.getY());
        punt1.setX(punt1.getX() + delta);
        punt1.setY(punt1.getY() + delta);
        assertEquals(21, punt1.getX());
        assertEquals(22, punt1.getY());
        punt1.setX(punt1.getX() - delta);
        punt1.setY(punt1.getY() - delta);
    }

    @Test
    public void testToString() {
        assertEquals(true, punt1.toString().equals(punt2.toString()));
        assertEquals(false, punt1.toString().equals(punt3.toString()));
        assertTrue(punt1.toString().replace(" ", "").equals("(12,13)"));
    }

    @Test
    public void testEquals() {
        assertTrue(punt1.equals(punt2));
        assertFalse(punt1.equals(punt3));
        assertFalse(punt1.equals(new Object()));
    }

    @Test
    public void testBerekenAfstand() {
        assertEquals(0, punt1.berekenAfstand(punt2));
        assertEquals(1, punt1.berekenAfstand(punt3));
        assertEquals(Math.sqrt(50), new Punt(0,0).berekenAfstand(new Punt(5,5)));
        assertEquals(Math.hypot(2, 6), new Punt(1,1).berekenAfstand(new Punt(3,7)));
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