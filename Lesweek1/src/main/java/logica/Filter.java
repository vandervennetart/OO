package logica;

public class Filter {

    public static boolean filter(String woord, int minLetters){
        return woord.length() >= minLetters;
    }

    public static String[] filter(String[] wooorden, int minLetters){
        String[] gefilterdeWoorden = new String[wooorden.length];

        for (int i = 0; i < wooorden.length; i++){
            if (filter(wooorden[i], minLetters)){
                gefilterdeWoorden[i] = wooorden[i];
            }
        }
        return gefilterdeWoorden;
    }
}
