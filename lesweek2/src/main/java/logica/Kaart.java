package logica;

import java.util.Arrays;

public class Kaart {


    private char soort;
    private char waarde;

    public Kaart(char soort, char waarde){
        this.soort = soort;
        this.waarde = waarde;
    }

    public Kaart(String kaart){
        this(kaart.charAt(0), kaart.charAt(1));
    }

    public char getSoort() {
        return soort;
    }

    public char getWaarde() {
        return waarde;
    }

    public static boolean isGeldigeSoort(char soort){
        return (soort == 'S' || soort == 'R' || soort == 'H' || soort == 'K');
    }

    public static boolean isGeldigeWaarde(char waarde){
        if (waarde == 'T' || waarde == 'B' || waarde == 'D' || waarde == 'H' || waarde == 'A' ){
            return true;
        }else if(Character.isDigit(waarde)){
            return ('1' < waarde && waarde <= '9' );
        }
        return false;
    }

    public static boolean isGeldigeKaart(String kaart){
        return (kaart.length() == 2 && isGeldigeSoort(kaart.charAt(0)) && isGeldigeWaarde(kaart.charAt(1)));
    }

    public static String geefHoogsteKaart(String k1, String k2){
        String winnaar = "geen geldige kaart";

        String nummers = "23456789TBDHA";


        if (isGeldigeKaart(k1) && isGeldigeKaart(k2)){

            if (k1.charAt(0) == 'S' || k2.charAt(0) == 'S'){

                if (k1.charAt(0) == 'S' && k2.charAt(0) == 'S'){

                    if (nummers.indexOf(k1.charAt(1)) > nummers.indexOf(k2.charAt(1))){
                        winnaar = k1;
                    }else if (nummers.indexOf(k1.charAt(1)) < nummers.indexOf(k2.charAt(1))){
                        winnaar = k2;
                    }else{
                        winnaar = "gelijk";
                    }


                }else{
                    return (k1.charAt(0) == 'S') ? k1 : k2;
                }

            } //schoppen

            if (nummers.indexOf(k1.charAt(1)) > nummers.indexOf(k2.charAt(1))){
                winnaar = k1;
            }else if (nummers.indexOf(k1.charAt(1)) < nummers.indexOf(k2.charAt(1))){
                winnaar = k2;
            }else{
                winnaar = "gelijk";
            }

        }
        return winnaar;
    }

    public boolean isGeldig(){
        return (isGeldigeSoort(this.soort) && isGeldigeWaarde(this.waarde));
    }

    @Override
    public String toString() {
        return ""+this.soort+this.waarde;
    }
}
