package presentatie;

import logica.Priem;

import java.util.Arrays;

public class DemoPriem {
    public static void main(String[] args) {
        Priem.printPriems(10);
        System.out.println(Arrays.toString(Priem.geefPriem(10)));
    }
}
