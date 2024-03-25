package logica;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @version 18-03-2024
 */
public class RapportTest {
    private Class myClass;

    private Rapport r;
    private Rapport r1;
    private Rapport r2;

    public RapportTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Rapport");
        r = new Rapport();
        r1 = new Rapport(new Vak[]{new Vak("code_a", "a", 2), new Vak("code_b", "b", 2), new Vak("code_c", "c", 2)});
        r2 = new Rapport(new Vak[]{new Vak("code_a", "a", 4), new Vak("code_b", "b",2), new Vak("code_c", "c",2)});
    }

    @Test
    public void testEnumeratie() {
        assertEquals(6, Graad.values().length);
        assertDoesNotThrow(() -> Graad.valueOf("ONBEPAALD"));
    }

    @Test
    public void testDefaultConstr() {
        assertEquals(0, r.getVakken().length);
        assertEquals(0, r.geefGewogenResultaatProcent());
        assertEquals(Graad.NIET_GESLAAGD, r.geefGraad());

        r.setVakken(new Vak[6]);
        assertEquals(6, r.getVakken().length);
        Arrays.stream(r.getVakken()).forEach(Assertions::assertNull);

        Vak[] concreteRij = new Vak[10];
        Arrays.fill(concreteRij, new Vak("code_x", "x", 4));
        r.setVakken(concreteRij);
        assertEquals(10, r.getVakken().length);
        Arrays.stream(r.getVakken()).forEach(v -> assertEquals("x", v.getNaam()));
    }

    @Test
    public void test1() {
        Vak[] vakken = r1.getVakken();
        vakken[0].setScore(12);
        vakken[1].setScore(12);
        vakken[2].setScore(12);
        assertEquals(60, r1.geefGewogenResultaatProcent());

        vakken[0].setScore(15);
        assertEquals(65, r1.geefGewogenResultaatProcent());
    }

    @Test
    public void test2() {
        Vak[] vakken = r1.getVakken();
        vakken[0].setScore(19);
        vakken[1].setScore(12);
        vakken[2].setScore(13);
        double expectedResult = 73.33;
        assertEquals(expectedResult, r1.geefGewogenResultaatProcent(), 0.01);
        assertEquals(expectedResult, r1.geefGewogenResultaatProcent(), 0.001);
    }

    @Test
    public void test3() {
        Vak[] vakken = r1.getVakken();
        assertEquals(3, vakken.length);
        for (Vak v : vakken) {
            assertEquals(0, v.getScore());
        }
    }

    @Test
    public void test4() {
        Vak[] vakken = r1.getVakken();
        vakken[0].setScore(15);
        vakken[1].setScore(12);
        vakken[2].setScore(12);
        assertEquals(65, r1.geefGewogenResultaatProcent());

        vakken = r2.getVakken();
        vakken[0].setScore(15);
        vakken[1].setScore(12);
        vakken[2].setScore(12);
        assertEquals(67.5, r2.geefGewogenResultaatProcent());
    }

    @Test
    public void test5() {
        Vak[] vakken = r1.getVakken();
        for (Vak v : vakken) {
            v.setScore(10);
        }
        assertEquals(Graad.VOLDOENING, r1.geefGraad());
        vakken[0].setScore(3);
        assertEquals(Graad.NIET_GESLAAGD, r1.geefGraad());
    }

    @Test
    public void test6() {
        Vak[] vakken = r1.getVakken();

        for (int score = 0; score <= 20; score++) {
            for (Vak v : vakken) {
                v.setScore(score);
            }
            assertEquals(bepaalGraad(score), r1.geefGraad());
        }
    }


    @Test
    public void test7() {
        Vak[] vakken = r1.getVakken();
        vakken[0].setScore(10);
        vakken[1].setScore(20);
        assertThrows(IllegalArgumentException.class, () -> vakken[2].setScore(30));
    }

    @Test
    public void test8() {
        Vak[] vakken = r1.getVakken();

        vakken[0].setScore(12);
        assertEquals(20, r1.geefGewogenResultaatProcent());
        vakken[1].setScore(6);
        assertEquals(30, r1.geefGewogenResultaatProcent());
        vakken[2].setScore(12);
        assertEquals(50, r1.geefGewogenResultaatProcent());
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
        System.out.println("-->> Je hebt " + countFields + " velden gedefinieerd, waarvan " + countPrivateFields + " private en " + countPublicStaticFinal + " publieke constanten.");
        assertEquals(1, countPrivateFields);
        assertEquals(0, countPublicStaticFinal);
    }

    @Test
    public void testMethoden() {
        int countMeth = myClass.getDeclaredMethods().length;

        int countMethPriv = 0;
        int countMethPub = 0;
        for (Method f : myClass.getDeclaredMethods()) {
            if ((f.getModifiers() & Modifier.PRIVATE) != 0) {
                countMethPriv++;
            } else if ((f.getModifiers() & Modifier.PUBLIC) != 0) {
                countMethPub++;
            }
        }
        System.out.println("-->> Je hebt " + countMeth + " methoden gedefinieerd, waarvan " + countMethPriv + " private.");
        assertTrue(countMeth >= 6);
        assertTrue(countMethPriv >= 1);
        assertEquals( 5, countMethPub);
    }

    @Test
    public void testConstructor() {
        assertTrue(myClass.getDeclaredConstructors().length >= 1);

        for (Constructor constr : myClass.getDeclaredConstructors()) {
            if (constr.getParameterCount() == 1) {
                Class[] parameterTypes = constr.getParameterTypes();
                assertEquals(1, parameterTypes.length);
                assertEquals("logica.Vak[]", parameterTypes[0].getTypeName());
            }
        }
    }

    @Test
    void testToString() {
        assertEquals("0.0% - NIET GESLAAGD", r1.toString().trim());
        for (Vak v : r1.getVakken()) v.setScore(12);
        assertEquals("60.0% - VOLDOENING", r1.toString().trim());
        for (Vak v : r1.getVakken()) v.setScore(14);
        assertEquals("70.0% - ONDERSCHEIDING", r1.toString().trim());
        for (Vak v : r1.getVakken()) v.setScore(16);
        assertEquals("80.0% - GROTE ONDERSCHEIDING", r1.toString().trim());
        for (Vak v : r1.getVakken()) v.setScore(18);
        assertEquals("90.0% - GROOTSTE ONDERSCHEIDING", r1.toString().trim());
    }

    private Graad bepaalGraad(int score) {
        return  score < 10 ? Graad.NIET_GESLAAGD :
                score < 13 ? Graad.VOLDOENING :
                        score < 15 ? Graad.ONDERSCHEIDING :
                                score < 17 ? Graad.GROTE_ONDERSCHEIDING :
                                        Graad.GROOTSTE_ONDERSCHEIDING;
    }
}