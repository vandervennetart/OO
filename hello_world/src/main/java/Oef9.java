
import java.time.LocalDate; // import the LocalDate class

public class Oef9 {
    public static void main(String[] args) {
        int dag = 1;
        int maand = 3;
        int jaar = 2024;
        System.out.println(isSchrikkeljaar(jaar));
        System.out.println(isDatumGeldig(dag, maand, jaar));

        LocalDate date = LocalDate.now(); // Create a date object
        System.out.println(formatDatum(date));
        System.out.println(formatDatum(dag,maand, jaar));

    }

    public static boolean isSchrikkeljaar(int jaar){
        return (jaar % 4) == 0 &&( (jaar % 100) != 0 || (jaar % 400) == 0);
    }

    public static boolean isDatumGeldig(int dag, int maand, int jaar){
        //int[] maanden = new int[]{31,(isSchrikkeljaar(jaar) ? 29: 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //return (1 < maand && maand < 12) && (0 < dag && dag < maanden[maand-1]);

        int aantal_dagen;
        switch (maand){
            case 1, 3, 5, 7, 8, 10, 12:
                aantal_dagen = 31;
                break;
            case 2:
                aantal_dagen = (isSchrikkeljaar(jaar) ? 29: 28);
                break;

            case 4, 6, 9, 11:
                aantal_dagen = 30;
                break;
            default:
                return false;

        }

        LocalDate date = LocalDate.now(); // Create a date object
        int current_year = date.getYear();
        System.out.println(current_year);

        return (1900 <= jaar && jaar <= current_year)  && (0 < dag && dag <= aantal_dagen);
    }

    public static String formatDatum(int dag, int maand, int jaar){
        return String.format("%02d",dag) +"/" + String.format("%02d",maand) +"/" + String.format("%04d",jaar);
    }

    public static String formatDatum(LocalDate date){
        return formatDatum(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }


}
