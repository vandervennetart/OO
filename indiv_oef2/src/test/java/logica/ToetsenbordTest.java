package logica;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ToetsenbordTest
 *
 * @author kristien.vanassche
 * @version 11/04/2024
 */
class ToetsenbordTest {

    @Test
    void testVorm(){
        Field[] fields = Toetsenbord.class.getDeclaredFields();
        assertEquals(2, fields.length);

        for (Field f : fields) {
            assert ((f.getModifiers() & Modifier.PRIVATE) != 0);
        }

        assertEquals(2, Toetsenbord.class.getDeclaredConstructors().length);
        assertDoesNotThrow(()->Toetsenbord.class.getDeclaredConstructor(Categorie.class));
        assertDoesNotThrow(()->Toetsenbord.class.getDeclaredConstructor(Categorie.class, int.class));
    }

    @Test
    void getLetterToetsen() {
        Toetsenbord tb = new Toetsenbord(Categorie.LETTER);
        assertEquals(0, tb.getAantalKolommen());
        assertEquals(26, tb.getToetsen().size());
        assertEquals("A", tb.getToetsen().get(0).getWeergavetekst());
        assertEquals("Z", tb.getToetsen().get(25).getWeergavetekst());
        assertNull(tb.getToets("z"));
        assertNotNull(tb.getToets("Z"));
        assertEquals("B",tb.getToets("B").getWeergavetekst());
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                tb.getToetsen().stream().map(Toets::getWeergavetekst).collect(Collectors.joining("")));
    }

    @Test
    void getCijferToetsen() {
        Toetsenbord tb = new Toetsenbord(Categorie.CIJFER);
        assertEquals(10, tb.getToetsen().size());
        assertEquals("7894561230",
                tb.getToetsen().stream().map(Toets::getWeergavetekst).collect(Collectors.joining("")));
    }

    @Test
    void getCijferToetsen2() {
        Toetsenbord tb = new Toetsenbord(Categorie.CIJFER, 5);
        assertEquals(5, tb.getAantalKolommen());
        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.toString(i), tb.getToets(Integer.toString(i)).toString());
        }
    }

    @Test
    void getEmoticonToetsen() {
        String[] refSetEmoticons = {":'(", ";-)", ":-)"};
        Toetsenbord tb = new Toetsenbord(Categorie.EMOTICON);
        assertEquals(24, tb.getToetsen().size());
        for (int i = 0; i < refSetEmoticons.length; i++) {
            assertTrue(tb.getToetsen().contains(new Emoticon(refSetEmoticons[i], "naam")));
        }
    }

    @Test
    void getEmoticonToetsenVolgorde() {
        Toetsenbord tb = new Toetsenbord(Categorie.EMOTICON);
        assertEquals("angel", ((Emoticon)tb.getToetsen().get(0)).getNaam());
        assertEquals("wink", ((Emoticon)tb.getToetsen().get(23)).getNaam());

        assertTrue(tb.getToetsen().stream().map(t->((Emoticon)t).getNaam()).collect(Collectors.joining(" "))
                .startsWith("angel angry blushing"));
        assertTrue(tb.getToetsen().stream().map(t->((Emoticon)t).getNaam()).collect(Collectors.joining(" "))
                .endsWith("unsure upset wink"));

        assertEquals("O:) >:O \uF04A ^_^ o.O :'( 3:) 8-) :-D >:( :-* :v :|] :( (^^^) :-) -_- 8-| :O (Y) :-P :/ >:O ;-)",
                tb.getToetsen().stream().map(Toets::getWeergavetekst).collect(Collectors.joining(" ")));
    }
}