package logica;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ToetsTest
 *
 * @author kristien.vanassche
 * @version 11/04/2024
 */

class ToetsTest {
    private ImageIcon icon;
    private Toets t;

    public ToetsTest() {
    }

    @Test
    public void testToetsVorm(){
        assert((Toets.class.getModifiers() & Modifier.ABSTRACT) != 0);

        Field[] fields = Toets.class.getDeclaredFields();
        assertEquals(2, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }

        assertEquals(2, Toets.class.getDeclaredConstructors().length);
        assertDoesNotThrow(()->Toets.class.getDeclaredConstructor(String.class));
        assertDoesNotThrow(()->Toets.class.getDeclaredConstructor(String.class, ImageIcon.class));
    }

    @Test
    public void testConstructor() {
        t = new Toets("TestAnoniem1") {};
        assertEquals("TestAnoniem1", t.getWeergavetekst());
        assertEquals("TestAnoniem1", t.toString());
        assertNull(t.getAfbeelding());

        t = new Toets("TestAnoniem2", icon) {};
        assertEquals("TestAnoniem2", t.getWeergavetekst());
        assertEquals("TestAnoniem2", t.toString());
        assert(icon == t.getAfbeelding());
    }

    @Test
    public void testLetter() {
        assertEquals(0, Letter.class.getDeclaredFields().length);

        t = new Letter('A');
        assertEquals("A", t.getWeergavetekst());
        assertEquals("A", t.toString());
        assertNull(t.getAfbeelding());
    }

    @Test
    public void testEquals() {
        t = new Letter('A');
        assertEquals(new Letter('A'), t);
        assertNotEquals(new Letter('B'), t);

        t = new Cijfer(1);
        assertEquals(new Cijfer(1), t);
        assertNotEquals(new Cijfer(2), t);
    }

    @Test
    public void testCijferBase() {
        assertEquals(0, Cijfer.class.getDeclaredFields().length);

        t = new Cijfer(1);
        assertEquals(1, Integer.parseInt(t.getWeergavetekst()));
        assertEquals("1", t.toString());
        assertNull(t.getAfbeelding());
    }

    @Test
    public void testCijferExtra() {
        icon = new ImageIcon("./images/default.jpg");
        t = new Cijfer(2, icon);
        assertEquals(2, Integer.parseInt(t.getWeergavetekst()));
        assertEquals("2", t.toString());
        assertNotNull(t.getAfbeelding());
        assert(t.getAfbeelding() == icon);
        assertThrows(IllegalArgumentException.class, ()-> new Cijfer(-1));
        assertThrows(IllegalArgumentException.class, ()-> new Cijfer(10));
    }

    @Test
    public void testCijferNok() {
        assertThrows(IllegalArgumentException.class, ()-> new Cijfer(-1));
        assertThrows(IllegalArgumentException.class, ()-> new Cijfer(10));
    }

    @Test
    public void testEmoticonBase() {
        assertEquals(1, Emoticon.class.getDeclaredFields().length);

        t = new Emoticon("(Â§Â°Â°Â§)", "mijnEmoticon");
        assertEquals("(Â§Â°Â°Â§)", t.getWeergavetekst());
        assertEquals("(Â§Â°Â°Â§) mijnEmoticon", t.toString());
    }

    @Test
    public void testEmoticonMetIcon() {
        icon = new ImageIcon("./images/default.jpg");
        t = new Emoticon("(Â§Â°Â°Â§)", "mijnEmoticon", icon);

        assertEquals("(Â§Â°Â°Â§)", t.getWeergavetekst());
        assertEquals("(Â§Â°Â°Â§)", ((Emoticon)t).getCode());
        assertEquals("mijnEmoticon", ((Emoticon)t).getNaam());

        assertNotNull(t.getAfbeelding().getImage());
        assertEquals("(Â§Â°Â°Â§) mijnEmoticon", t.toString());
    }

    @Test
    public void testEmoticonInterfaceBase() {
        String s1, s2;
        IPictogram mijnPictogram = new Emoticon(s1 = "mijnCode",s2 = "mijnNaam");
        assertEquals(s1, mijnPictogram.getCode());
        assertEquals(s2, mijnPictogram.getNaam());
        assertNull(mijnPictogram.getAfbeelding());
        assertEquals(s1 + " " + s2, mijnPictogram.toString());
    }

    @Test
    public void testEmoticonInterfaceMetAfbeelding() {
        icon = new ImageIcon("./images/default.jpg");
        IPictogram mijnPictogram = new Emoticon("mijnCode","mijnNaam", icon);
        assertEquals(icon, mijnPictogram.getAfbeelding());
    }

    @Test
    public void testEmoticonMissingData() {
        assertThrows(IllegalArgumentException.class, () -> new Emoticon(null, "XXXXX"));
        assertThrows(IllegalArgumentException.class, () -> new Emoticon("", "XXXXX"));
        assertThrows(IllegalArgumentException.class, () -> new Emoticon("X", null));
        assertThrows(IllegalArgumentException.class, () -> new Emoticon("X", ""));
        t = new Emoticon("X", "X");
    }
}