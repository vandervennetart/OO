import java.util.Scanner;

public class Oef8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("geef een string: ");
        String url = scan.nextLine();

        System.out.println(isUrl(url) ? "het is een url" : "het is geen url");
    }
    public static boolean isUrl(String url){
        return (url.startsWith("http://") ||  url.startsWith("https://"));
    }
}
