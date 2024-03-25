package logica;

import java.util.Objects;

public class KaartBoek {
    public final int AANTAL;

    public Kaart[] kaarten;


    public KaartBoek() {
        this.AANTAL = 52;
        this.kaarten = new Kaart[this.AANTAL];

        char[] soorten = new char[]{'S', 'H', 'K', 'R'};
        char[] getallen = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'B', 'D', 'H', 'A'};
        int index = 0;

        for (char soort : soorten) {

            for (char getal : getallen) {
                this.kaarten[index] = new Kaart(soort, getal);
                index++;
            }
        }
    }

    public KaartBoek(char[] soorten) {

        int aantalJuisteSoorten = 0;
        for (char soort : soorten) {
            if (Kaart.isGeldigeSoort(soort)) {
                aantalJuisteSoorten++;
            }
        }


        this.AANTAL = aantalJuisteSoorten * 13;
        this.kaarten = new Kaart[this.AANTAL];
        char[] getallen = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'B', 'D', 'H', 'A'};
        int index = 0;

        int iS = 0;
        for (char soort : soorten) {

            if (Kaart.isGeldigeSoort(soort)) {
                for (char getal : getallen) {
                    this.kaarten[index] = new Kaart(soort, getal);
                    index++;
                }
            }

        }
    }

    public KaartBoek(String[] kaarten) {


        int aantalGeldig = 0;
        for (String kaart : kaarten) {
            if (Kaart.isGeldigeKaart(kaart)) {
                aantalGeldig++;

            }
        }

        this.AANTAL = aantalGeldig;
        this.kaarten = new Kaart[this.AANTAL];

        int index = 0;
        for (String kaart : kaarten) {
            if (Kaart.isGeldigeKaart(kaart)) {

                this.kaarten[index] = new Kaart(kaart);
                index++;


            }
        }


    }

    public boolean bevatKaart(String kaart) {
        for (Kaart kaart_in_boek : this.kaarten) {
            if (Objects.equals(kaart, "" + kaart_in_boek.getSoort() + kaart_in_boek.getWaarde())) {
                return true;
            }
        }
        return false;
    }

    public boolean bevatKaartVanSoort(char soort) {
        for (Kaart kaart_in_boek : this.kaarten) {
            if (Objects.equals(soort, kaart_in_boek.getSoort())) {
                return true;
            }
        }
        return false;
    }

    public Kaart[] geefAlleKaartenVanSoort(char soort) {
        Kaart[] kaartenVanSoort;


        boolean soortInBoek = false;
        for (Kaart kaart_in_boek : this.kaarten) {
            if (kaart_in_boek.getSoort() == soort) {
                soortInBoek = true;
                break;
            }

        }


        int i = 0;
        if (Kaart.isGeldigeSoort(soort) && soortInBoek) {
            kaartenVanSoort = new Kaart[13];

            for (Kaart kaart_in_boek : this.kaarten) {
                if (Objects.equals(soort, kaart_in_boek.getSoort())) {

                    kaartenVanSoort[i] = kaart_in_boek;
                    i++;
                }
            }

            int kaartenInKaartenVanSoort = 0;
            for (Kaart kaart : kaartenVanSoort) {
                if (kaart != null)
                    kaartenInKaartenVanSoort++;
            }

            Kaart[] output = new Kaart[kaartenInKaartenVanSoort];

            int index = 0;
            for (Kaart kaart : kaartenVanSoort) {
                if (kaart != null) {
                    output[index] = kaart;
                    index++;
                }

            }


            return output;


        }


        return new Kaart[0];


    }

    public Kaart geefRandomKaart() {
        Dobbelsteen dobbel = new Dobbelsteen(0, AANTAL - 1);
        dobbel.gooi();
        return kaarten[dobbel.get()];
    }

    public Kaart geefRandomKaartVanSoort(char soort) {

        boolean soortInBoek = false;
        for (Kaart kaart_in_boek : this.kaarten) {
            if (kaart_in_boek.getSoort() == soort) {
                soortInBoek = true;
                break;
            }

        }

        if(soortInBoek){
            Dobbelsteen dobbel = new Dobbelsteen(0, AANTAL - 1);

            do {
                dobbel.gooi();
            } while (!(kaarten[dobbel.get()].getSoort() == soort));


            return kaarten[dobbel.get()];
        }
        return null;
    }


}
