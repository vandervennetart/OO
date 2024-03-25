package logica;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @version 18-03-2024
 */
class VakTest {
    private Class myClass;
    private Vak vak1, vak2;

    public VakTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Vak");
        vak1 = new Vak("codeWi", "Wiskunde", 3, Semester.SEM1);
        vak2 = new Vak("codeFy", "Fysica", 4, Semester.SEM2);
    }

    @Test
    public void testCopyConstructor() {
        Vak vakCopy = new Vak(vak1);
        assertEquals("codeWi", vakCopy.getCode());
        assertEquals("Wiskunde", vakCopy.getNaam());
        assertEquals(3, vakCopy.getStp());
        assertEquals(0, vakCopy.getScore());
        vak1.setScore(12);
        vakCopy.setScore(14);
        assertEquals(12, vak1.getScore());
        assertEquals(14, vakCopy.getScore());
    }

    @Test
    public void testConstructorNOK() {
        assertThrows(IllegalArgumentException.class, () -> new Vak((String) null, (String) null, 2));
        assertThrows(IllegalArgumentException.class, () -> new Vak("", "", 3));
        assertThrows(IllegalArgumentException.class, () -> new Vak("codeWi", "", 3));
        assertThrows(IllegalArgumentException.class, () -> new Vak("codeWi", "Wiskunde", -2));
        assertThrows(IllegalArgumentException.class, () -> new Vak("codeWi", "Wiskunde", 22));
        new Vak("xxxxxx", "x", 2);
    }

    @Test
    void testGetters() {
        assertEquals("codeWi", vak1.getCode());
        assertEquals("Wiskunde", vak1.getNaam());
        assertEquals(3, vak1.getStp());
        assertEquals(0, vak1.getScore());

        assertEquals("codeFy", vak2.getCode());
        assertEquals("Fysica", vak2.getNaam());
        assertEquals(4, vak2.getStp());
        assertEquals(0, vak2.getScore());
    }

    @Test
    void testSetters() {
        assertEquals(0, vak1.getScore());
        vak1.setScore(10);
        assertEquals(10, vak1.getScore());
    }

    @Test
    void testSettersExtra() {
        assertEquals(0, vak1.getScore());
        assertThrows(IllegalArgumentException.class, () -> vak1.setScore(-10));
        assertEquals(0, vak1.getScore());
    }

    @Test
    void testEquals() {
        assertNotEquals(vak1, vak2);

        assertEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam(), vak1.getStp(), vak1.getSem()));
        assertNotEquals(vak1, new Vak("_" + vak1.getCode().substring(1), vak1.getNaam(), vak1.getStp(), vak1.getSem()));
        assertNotEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam() + " ", vak1.getStp(), vak1.getSem()));
        assertNotEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam(), vak1.getStp() + 1, vak1.getSem()));
        assertNotEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam(), vak1.getStp(), null));
        assertEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam().toLowerCase(), vak1.getStp(), vak1.getSem()));
        assertEquals(vak1, new Vak(vak1.getCode(), vak1.getNaam().toUpperCase(), vak1.getStp(), vak1.getSem()));

        for (Method f : myClass.getDeclaredMethods()) {
            if (f.getName().equals("equals")) {
                assertTrue((f.getModifiers() & Modifier.PUBLIC) != 0);
                assertEquals(1, f.getParameterCount());
                assertEquals("java.lang.Object", f.getParameterTypes()[0].getTypeName());
            }
        }
    }

    @Test
    void testToString() {
        assertEquals("Wiskunde (3stp)", vak1.toString().trim());
        assertEquals("Fysica (4stp)", vak2.toString().trim());
    }

    @Test
    public void testVelden() {
        int countFields = 0;
        int countPrivateFields = 0;
        int countPublicStaticFinal = 0;

        for (Field f : myClass.getDeclaredFields()) {
            countFields++;
            if ((f.getModifiers() & Modifier.PUBLIC) != 0 && (f.getModifiers() & Modifier.STATIC) != 0 && (f.getModifiers() & Modifier.FINAL) != 0) {
                countPublicStaticFinal++;
            } else if ((f.getModifiers() & Modifier.PRIVATE) != 0) {
                countPrivateFields++;
            }
        }
        System.out.println("-->> Je hebt " + countFields + " velden gedefinieerd, waarvan "
                + countPrivateFields + " private en "
                + countPublicStaticFinal + " publieke " + (countPublicStaticFinal == 1 ? "constante." : "constanten."));
        assertEquals(5, countPrivateFields);
        assertEquals(1, countPublicStaticFinal);
    }

    @Test
    public void testMethoden() {
        int countMeth = myClass.getDeclaredMethods().length;

        int countMethPriv = 0;
        for (Method f : myClass.getDeclaredMethods()) {
            if ((f.getModifiers() & Modifier.PRIVATE) != 0) {
                countMethPriv++;
            }
        }
        System.out.println("-->> Je hebt " + countMeth + " methoden gedefinieerd, waarvan " + countMethPriv + " private.");
        assertTrue(countMeth >= 7);
        assertTrue(countMethPriv >= 0);
    }


    @Test
    public void testConstructor() {
        assertTrue(myClass.getDeclaredConstructors().length >= 3);

        for (Constructor<Vak> constr : myClass.getDeclaredConstructors()) {
            Class[] parameterTypes = constr.getParameterTypes();
            switch (constr.getParameterCount()) {
                case 1 -> {
                    assertTrue(parameterTypes[0].getTypeName().equals("logica.Vak"));
                }
                case 3 -> {
                    assertEquals("java.lang.String", parameterTypes[0].getTypeName());
                    assertEquals("java.lang.String", parameterTypes[1].getTypeName());
                    assertEquals("int", parameterTypes[2].getTypeName());
                }
                case 4 -> {
                    assertEquals("java.lang.String", parameterTypes[0].getTypeName());
                    assertEquals("java.lang.String", parameterTypes[1].getTypeName());
                    assertEquals("int", parameterTypes[2].getTypeName());
                    assertEquals("logica.Semester", parameterTypes[3].getTypeName());
                }
            }
        }
    }
}