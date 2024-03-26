package logica;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @author tim.vermeulen
 * @version 24/03/2024
 */
public class FiguurTest {
    Class<?> myClass;

    public FiguurTest() throws ClassNotFoundException {
        myClass = Class.forName("logica.Figuur");
    }

    @Test
    public void testFieldModifier() throws ClassNotFoundException {
        Field[] fields = myClass.getDeclaredFields();
        assertEquals(4, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }
    }

    @Test
    public void testClassModifier() {
        assert((myClass.getModifiers() & Modifier.ABSTRACT) != 0);
    }

    @Test
    public void testConstructoren() throws ClassNotFoundException {
        int count = 0;
        for(Constructor constr : myClass.getDeclaredConstructors()) {
            if (constr.getParameterCount() == 0) {
                count++;
            }
            else if (constr.getParameterCount() == 3) {
                Class[] parameterTypes = constr.getParameterTypes();
                assertEquals(3, parameterTypes.length);
                assertEquals("logica.Kleur", parameterTypes[0].getTypeName());
                assertEquals("logica.Kleur", parameterTypes[1].getTypeName());
                assertEquals("int", parameterTypes[2].getTypeName());
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testGetterSetterMethodSignature() throws NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("getKleur");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertEquals(0, (m.getModifiers() & Modifier.ABSTRACT));
        assertEquals(0, m.getParameterCount());
        assertEquals(Kleur.class, m.getReturnType());

        m = myClass.getDeclaredMethod("getKleurRand");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertTrue((m.getModifiers() & Modifier.ABSTRACT) == 0);
        assertEquals(0, m.getParameterCount());
        assertEquals(Kleur.class, m.getReturnType());

        m = myClass.getDeclaredMethod("getDikteRand");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertEquals(0, (m.getModifiers() & Modifier.ABSTRACT));
        assertEquals(0, m.getParameterCount());
        assertEquals(Integer.TYPE, m.getReturnType());

        m = myClass.getDeclaredMethod("getMiddelpunt");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertEquals(0, (m.getModifiers() & Modifier.ABSTRACT));
        assertEquals(0, m.getParameterCount());
        assertEquals(Punt.class, m.getReturnType());

        m = myClass.getDeclaredMethod("setMiddelpunt", Punt.class);
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertEquals(0, (m.getModifiers() & Modifier.ABSTRACT));
        assertEquals(1, m.getParameterCount());
        assertEquals(void.class, m.getReturnType());
    }

    @Test
    public void testMethodSignatureToString() throws ClassNotFoundException, NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("toString");
        assertEquals(0, m.getParameterCount());
        assertEquals(Class.forName("java.lang.String"), m.getReturnType());
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertNotNull(m);
    }

    @Test
    public void testEqualsMethodSignature() throws ClassNotFoundException, NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("equals", Class.forName("java.lang.Object"));
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertNotNull(m);
    }

    @Test
    public void testBerekenOppervlakteMethodSignature() throws NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("berekenOppervlakte");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertTrue((m.getModifiers() & Modifier.ABSTRACT) != 0);
        assertEquals(0, m.getParameterCount());
        assertEquals(Double.TYPE, m.getReturnType());
    }

    @Test
    public void testBerekenOmtrekMethodSignature() throws NoSuchMethodException {
        Method m = myClass.getDeclaredMethod("berekenOmtrek");
        assertNotNull(m);
        assertTrue((m.getModifiers() & Modifier.PUBLIC) != 0);
        assertTrue((m.getModifiers() & Modifier.ABSTRACT) != 0);
        assertEquals(0, m.getParameterCount());
        assertEquals(Double.TYPE, m.getReturnType());
    }

    @Test
    public void testImplementatie1(){
        Figuur figuur=new Figuur() {
            @Override
            public double berekenOmtrek() {
                return 0;
            }

            @Override
            public double berekenOppervlakte() {
                return 0;
            }
        };
        assertEquals(Kleur.WIT, figuur.getKleur());
        assertEquals(Kleur.ZWART, figuur.getKleurRand());
        assertEquals(1,figuur.getDikteRand());
        assertEquals(new Punt(0,0),figuur.getMiddelpunt());
        figuur.setMiddelpunt(new Punt(-10,15));
        assertEquals(new Punt(-10,15),figuur.getMiddelpunt());
    }

    @Test
    public void testImplementatie2(){
        Figuur figuur=new Figuur(Kleur.ORANJE, Kleur.BLAUW, 15) {
            @Override
            public double berekenOmtrek() {
                return 0;
            }

            @Override
            public double berekenOppervlakte() {
                return 0;
            }
        };
        assertEquals(Kleur.ORANJE, figuur.getKleur());
        assertEquals(Kleur.BLAUW, figuur.getKleurRand());
        assertEquals(15,figuur.getDikteRand());
        assertEquals(new Punt(0,0),figuur.getMiddelpunt());
        figuur.setMiddelpunt(new Punt(-10,15));
        assertEquals(new Punt(-10,15),figuur.getMiddelpunt());
        assertEquals("ORANJE/BLAUW 15cm (-10,15)", figuur.toString());
    }

    @Test
    public void testEquals(){
        Figuur figuur1=new Figuur() {
            @Override
            public double berekenOmtrek() {
                return 0;
            }

            @Override
            public double berekenOppervlakte() {
                return 0;
            }
        };
        Figuur figuur2=new Figuur(Kleur.WIT, Kleur.ZWART, 1) {
            @Override
            public double berekenOmtrek() {
                return 0;
            }

            @Override
            public double berekenOppervlakte() {
                return 0;
            }
        };
        assertEquals(figuur1,figuur2);
    }

    @Test
    public void testException(){
        assertThrows(IllegalArgumentException.class, () -> new Figuur(Kleur.ZWART, Kleur.ZWART, -5){
            @Override
            public double berekenOmtrek() {
                return 0;
            }

            @Override
            public double berekenOppervlakte() {
                return 0;
            }
        });
    }

    @Test
    public void testEqualsDummy(){
        assertEquals(new FiguurDummy(),new FiguurDummy());
    }

    @Test
    public void testEqualsImplOk() {
        Figuur figuur1=new FiguurImpl() ;
        Figuur figuur2=new FiguurImpl(Kleur.WIT, Kleur.ZWART, 1) ;
        Figuur figuur3=new FiguurImpl(Kleur.WIT, Kleur.ZWART, 2) ;
        assertEquals(figuur1,figuur2);
        assertNotEquals(figuur1, figuur3);
        assertNotEquals(figuur2, figuur3);
    }

    @Test
    public void testEqualsImplNok() {
        assertThrows(IllegalArgumentException.class, () -> new FiguurImpl(Kleur.WIT, Kleur.ZWART, -2));
    }

    private static class FiguurDummy extends Figuur {
        @Override
        public double berekenOmtrek() {return 0;}
        @Override
        public double berekenOppervlakte() {return 0;}
    }

    private static class FiguurImpl extends Figuur {
        public FiguurImpl() {}
        public FiguurImpl(Kleur kleur, Kleur kleurRand, int dikteRand) { super(kleur, kleurRand, dikteRand); }
        @Override
        public double berekenOmtrek() { return 0; }
        @Override
        public double berekenOppervlakte() { return 0; }
    }
}