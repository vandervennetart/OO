package presentatie;

import logica.Dobbelsteen;

public class DemoDobbelsteen {
    public static void main(String[] args) {
        int gewenstAantalOgen = 3;
        int maxAantalWorpen = 10;
        int aantal = telAantalWorpen(maxAantalWorpen, gewenstAantalOgen);
        if (aantal <= 0) {
            System.out.println("Er werd geen " + gewenstAantalOgen
                    + " gegooid binnen de " + maxAantalWorpen + " beschikbare pogingen");
        }
        else {
            System.out.println("In " + aantal + " pogingen werd een "
                    + gewenstAantalOgen + " gegooid");
        }

    }

    public static int telAantalWorpen(int maxAantalWorpen, int gewenstAantalOgen){
        Dobbelsteen dobbel = new Dobbelsteen(0, gewenstAantalOgen * 2);
        int pogingen = 0;
        int geworpen;
        do{
            dobbel.gooi();
            geworpen = dobbel.get();
            pogingen++;

            System.out.println(geworpen);
        }while (gewenstAantalOgen != geworpen);

        return pogingen <= maxAantalWorpen ? pogingen : -1;
    }
}
