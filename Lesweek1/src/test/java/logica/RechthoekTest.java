package logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RechthoekTest {

    @Test
    void isVierkant() {
        Rechthoek r = new Rechthoek(0,0,5,5);
        assertTrue(r.isVierkant());
    }

    @Test
    void berekenOmtrek() {
        Rechthoek r = new Rechthoek(0,0,5,5);
        assertEquals(20, r.berekenOmtrek());
    }

    @Test
    void berekenOpp() {
        Rechthoek r = new Rechthoek(0,0,5,5);
        assertEquals(25, r.berekenOpp());
    }

    @Test
    void isIn() {
        Rechthoek r = new Rechthoek(0,0,5,5);
        assertTrue(r.isIn(2,-2));
    }

    @Test
    void testToString() {
        Rechthoek r = new Rechthoek(5,10,8,4);
        assertEquals("Rechthoek 8 x 4 met oorsprong (5,10)", r.toString());
    }
}