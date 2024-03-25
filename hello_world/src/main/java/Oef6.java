import java.util.Scanner;

public class Oef6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Geef r: ");
        float straal = scan.nextInt();

        System.out.println("Geef h: ");
        float hoogte = scan.nextInt();

        System.out.println(volume(straal, hoogte)) ;
    }
    public static double volume(float straal, float hoogte){
        return Math.PI * Math.pow(straal, 2) * hoogte;
    }
}
