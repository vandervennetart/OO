package logica;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DobbelsteenTest
 *
 * @author kristien.vanassche
 * @version 25/02/2024
 */
class DobbelsteenTest {
    @Test
    public void testGooiEnGet() {
        int AANTAL = 10000;
        int min = 1, max = 6;
        int worp;
        Dobbelsteen dobbelsteen = new Dobbelsteen(min,max);
        for (int i = 0; i < AANTAL; i++) {
            dobbelsteen.gooi();
            worp = dobbelsteen.get();
            assertTrue(worp >= min && worp <= max);
        }
    }

    @Test
    void testGetEnToString() {
        for(int i = 0; i < 20; i++) {
            int random = new Random().nextInt(20);
            Dobbelsteen dobbelsteen = new Dobbelsteen(random, random);
            dobbelsteen.gooi();
            System.out.print(dobbelsteen.get() + " ");
            assertEquals(random, dobbelsteen.get());
            assertEquals(String.valueOf(dobbelsteen.get()), dobbelsteen.toString());
        }
        System.out.println();
    }

    @Test
    public void testDistributie1() {
        int[] aantallen = new int[6];
        Dobbelsteen dobbelsteen = new Dobbelsteen(1,6);
        int AANTAL = 6000;
        for (int i = 0; i < AANTAL; i++) {
            dobbelsteen.gooi();
            aantallen[dobbelsteen.get()-1]++;
        }
        int som = 0;
        int waardeSom = 0;
        for (int i = 0; i < 6; i++) {
            som += aantallen[i];
            waardeSom += aantallen[i] * (i+1);
        }
        printDistributie(aantallen);
        assertEquals(AANTAL, som);
        assertEquals(3, waardeSom/AANTAL);
    }

    @Test
    public void testDistributie2() {
        boolean steedsKleinsteAantalAanRand = true;
        int[] aantallen;
        int AANTAL = 600;
        Dobbelsteen dobbelsteen = new Dobbelsteen(1,6);
        for (int i = 0; i < 10; i++) {
            aantallen = new int[6];
            for (int j = 0; j < AANTAL; j++) {
                dobbelsteen.gooi();
                aantallen[dobbelsteen.get() - 1]++;
            }
            printDistributie(aantallen);

            for (int j = 1; j < 5; j++) {
                if (aantallen[j] < aantallen[0] || aantallen[j] < aantallen[5]) {
                    steedsKleinsteAantalAanRand = false;
                    break;
                }
            }
        }
        assertFalse(steedsKleinsteAantalAanRand);
    }

    private void printDistributie(int[] rij) {
        System.out.print("Distributie: ");
        for (int j = 0; j < 6; j++) {
            System.out.print(rij[j] + "\t");
        }
        System.out.println();
    }
}