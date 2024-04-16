package logica;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ToetsTestSort
 *
 * @author kristien.vanassche
 * @version 11/04/2024
 */

class ToetsTestSort {
    @Test
    public void testToetsSort() {
        List<Toets> lijst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt(1000);
            lijst.add(new Toets("TestAnoniem" + rand) {});
        }

        Collections.sort(lijst);

        for (int i = 0; i < lijst.size()-1; i++) {
            assertTrue(lijst.get(i).getWeergavetekst().compareTo(lijst.get(i+1).getWeergavetekst()) <= 0);
        }
        System.out.println(lijst);
    }

    @Test
    public void testEmoticonSort() {
        Emoticon[] rij = new Emoticon[] {
                new Emoticon("...", "C"),
                new Emoticon("..", "A"),
                new Emoticon(".", "B")
        };
        StringBuilder s;

        s = new StringBuilder();
        for (Emoticon e : rij) s.append(e.getNaam());
        assertEquals("CAB", s.toString());

        Arrays.sort(rij);

        s = new StringBuilder();
        for (Emoticon e : rij) s.append(e.getNaam());
        assertEquals("ABC", s.toString());
    }
}