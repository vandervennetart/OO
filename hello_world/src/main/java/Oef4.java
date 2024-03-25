import java.util.Scanner;

public class Oef4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Geef r: ");
        float straal = scan.nextInt();

        System.out.println("Geef h: ");
        float hoogte = scan.nextInt();

        System.out.println(Math.round((Math.PI * Math.pow(straal, 2) * hoogte) * 100d) / 100d ) ;
    }
}
