import java.util.Scanner;

public class Oef3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Geef je getal1: ");
        int getal1 = scan.nextInt();

        System.out.println("Geef je getal2: ");
        int getal2 = scan.nextInt();

        System.out.println("de som is: " + (getal1 + getal2));


        System.out.println("Geef je getal3: ");
        float getal3 = scan.nextInt();

        System.out.println("de som is: " + (getal3*2));


        System.out.println("Geef je getal4: ");
        int getal4 = scan.nextInt();

        System.out.println("Geef je getal5: ");
        int getal5 = scan.nextInt();

        System.out.println("de som is: " + (Math.pow(getal4, getal5)));

        System.out.println("Geef je getal6: ");
        int getal6 = scan.nextInt();

        System.out.println("de som is: " + (Math.sqrt(getal6)));
    }

}
