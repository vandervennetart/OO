package presentatie;
import logica.Filter;
public class DemoFilter {
    public static void main(String[] args) {
        String[] woorden = {"Wortel", "Sperzieboontjes", "Broccoli", "Bloemkool", "Spinazie", "Sla"};

        String[] gefilterd = Filter.filter(woorden, 8);
        for(String i : gefilterd){
            if (i != null){
                System.out.println(i);
            }
        }
    }
}
