package presentatie;

import logica.Rechthoek;

import java.util.Arrays;
import java.util.Scanner;

public class DemoRechthoek {
    public static void main(String[] args) {
        Rechthoek rechthoek = new Rechthoek(1,1, 1, 1);
        System.out.println(rechthoek.isVierkant());
        System.out.println(rechthoek.berekenOmtrek());
        System.out.println(rechthoek.berekenOpp());


        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        do {
            String[] input = scanner.nextLine().split(",");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);

        } while (!rechthoek.isIn(x,y));
    }
}
