package presentatie;

import logica.Pizza;

public class DemoPizza {
    public static void main(String[] args) {
        String[] toppingsNapoli = new String[] {"anjovis","kappertjes", "olijven"};
        Pizza napoliMedium = new Pizza("medium", toppingsNapoli);
        System.out.println(napoliMedium); // Een medium pizza met anjovis en kappertjes en olijven

        String[] toppingsHawai = new String[] {"ananas","gekookte ham"};
        Pizza hawaiLarge = new Pizza("large", toppingsHawai);
        System.out.println(hawaiLarge); // Een large pizza met ananas en gekookte ham

        Pizza combinatie = napoliMedium.add(hawaiLarge);
        System.out.println(combinatie); // Een large pizza met anjovis en kappertjes en olijven en ananas en gekookte ham
        int aantal = combinatie.geefAantalToppings(); // 5
        System.out.println(aantal); // 5

        String[] zoeken = new String[] {"peperoni","ananas","champignons"};
        boolean[] aanwezigJaNee = combinatie.welkeAanwezig(zoeken); // {False, True, False}
        for (boolean b : aanwezigJaNee) {
            System.out.print(b + ", ");
        }
    }
}
