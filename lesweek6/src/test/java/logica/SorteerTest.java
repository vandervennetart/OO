package logica;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kristien.vanassche
 * @author tim.vermeulen
 * @version 22/03/2023
 */
public class SorteerTest {
    private Vierkant v1, v2;
    private Cirkel c1, c2;

    @Test
    public void testCompareTo1() {
        int[] zijden = {1,7,4,2,3,6,5};
        ArrayList<Vierkant> lijst = new ArrayList<>();
        for (int z : zijden) lijst.add(new Vierkant(z));
        Collections.sort(lijst);
        for(int i = 0; i < lijst.size()-1; i++) {
            assertTrue(lijst.get(i).getZijde() > lijst.get(i+1).getZijde());
        }
    }

    @Test
    public void testCompareTo2() {
        int[] zijden = {1,7,4,2,3,6,5};
        int[] stralen = {1,7,4,2,3,6,5};
        ArrayList<Figuur> lijst = new ArrayList<>();
        for (int z : zijden) lijst.add(new Vierkant(z));
        for (int r : stralen) lijst.add(new Cirkel(r));
        Collections.sort(lijst);
        for(int i = 0; i < lijst.size()-1; i++) {
            assertTrue(lijst.get(i).berekenOmtrek() > lijst.get(i+1).berekenOmtrek());
        }
    }

    @Test
    public void testCompareTo3() {
        v1 = new Vierkant(7.0);
        v2 = new Vierkant(7.0);
        c1 = new Cirkel(4);
        c2 = new Cirkel(5);

        assertTrue(v1.compareTo(v2) == 0);
        assertTrue(v1.compareTo(c1) < 0);
        assertTrue(v1.compareTo(c2) > 0);
    }

    @Test
    public void testCompareTo4() {
        Cirkel oog1 = new Cirkel(15);
        oog1.setMiddelpunt(new Punt(-30,30));
        Cirkel oog2 = new Cirkel(15);
        oog2.setMiddelpunt(new Punt(0,0));
        Cirkel oog3 = new Cirkel(15);
        oog3.setMiddelpunt(new Punt(-30,-30));
        Cirkel oog4 = new Cirkel(15);
        oog4.setMiddelpunt(new Punt(30,30));
        Cirkel oog5 = new Cirkel(15);
        oog5.setMiddelpunt(new Punt(30,-30));
        Vierkant dobbelsteen = new Vierkant(120);
        dobbelsteen.setMiddelpunt(new Punt(0,0));

        Figuur[] figuren = {oog1, oog2, oog3, oog4, oog5, dobbelsteen};

        Arrays.sort(figuren);
        assertEquals(dobbelsteen, figuren[0]);
        assertEquals(oog3, figuren[1]);
        assertEquals(oog1, figuren[2]);
        assertEquals(oog2, figuren[3]);
        assertEquals(oog5, figuren[4]);
        assertEquals(oog4, figuren[5]);
    }

    @Test
    public void testCompareTo5() {
        v1 = new Vierkant(7.0);
        v2 = new Vierkant(7.01);
        v1.berekenOmtrek();
        v2.berekenOmtrek();

        assertTrue(v1.compareTo(v2) > 0);
    }
}