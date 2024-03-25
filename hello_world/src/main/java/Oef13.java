public class Oef13 {
    public static void main(String[] args) {
        dobbel(5);
    }
    public static void dobbel(int gewenst){
        int gekregen;
        int poging = 0;
        do {
            poging++;
            gekregen = dobbel();

            System.out.println("poging " + poging + ": " + gekregen);
        }while (gekregen != gewenst);
        System.out.println("in " + poging + " pogingen werd er een " + gekregen + " gedobbeld");
    }

    public static int dobbel(){
        return (int) Math.round(Math.random() * 6);
    }
}
