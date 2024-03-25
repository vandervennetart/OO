package logica;

public class Kaart {


    private KaartSoort soort;
    private KaartWaarde waarde;

    public Kaart(KaartSoort soort, KaartWaarde waarde){
        this.soort = soort;
        this.waarde = waarde;
    }

    public Kaart(String data){

            this.soort = Kaart.naarKaartSoort(data.charAt(0));
            this.waarde = Kaart.naarKaartWaarde(data.charAt(1));



        //this(Kaart.naarKaartSoort(data.charAt(0)) ,Kaart.naarKaartWaarde(data.charAt(1)) );


    }

    public KaartSoort getSoort() {
        return soort;
    }

    public KaartWaarde getWaarde() {
        return waarde;
    }





    public static Kaart geefHoogsteKaart(Kaart k1, Kaart k2){




        if (k1.getSoort() == KaartSoort.SCHOPPEN || k2.getSoort() == KaartSoort.SCHOPPEN){

            if (k1.getSoort() == KaartSoort.SCHOPPEN  && k2.getSoort() == KaartSoort.SCHOPPEN ){

                if (k1.getWaarde().ordinal() < k2.getWaarde().ordinal()){
                    return k2;
                }else{
                    return k1;
                }


            }else{
                return (k1.getSoort() == KaartSoort.SCHOPPEN) ? k1 : k2;
            }

        } //schoppen

        if (k1.getWaarde().ordinal() < k2.getWaarde().ordinal()){
            return k2;
        }else{
            return k1;
        }

    }

    public boolean isGelijkwaardigAan(Kaart andereKaart){
        if (this.getSoort() == KaartSoort.SCHOPPEN ^ andereKaart.getSoort() == KaartSoort.SCHOPPEN){
            return false;
        }else{
            return this.getWaarde() == andereKaart.getWaarde();
        }
    }


    private static KaartSoort naarKaartSoort(char c){
        return switch (c) {
            case 'H' -> KaartSoort.HARTEN;
            case 'K' -> KaartSoort.KLAVEREN;
            case 'R' -> KaartSoort.RUITEN;
            case 'S' -> KaartSoort.SCHOPPEN;
            default -> throw new IllegalArgumentException("geen geldige soort");
        };
    }

    private static KaartWaarde naarKaartWaarde(char c){
        return switch (c) {
            case '2' -> KaartWaarde.TWEE;
            case '3' -> KaartWaarde.DRIE;
            case '4' -> KaartWaarde.VIER;
            case '5' -> KaartWaarde.VIJF;
            case '6' -> KaartWaarde.ZES;
            case '7' -> KaartWaarde.ZEVEN;
            case '8' -> KaartWaarde.ACHT;
            case '9' -> KaartWaarde.NEGEN;
            case 'T' -> KaartWaarde.TIEN;
            case 'B' -> KaartWaarde.BOER;
            case 'D' -> KaartWaarde.DAME;
            case 'H' -> KaartWaarde.HEER;
            case 'A' -> KaartWaarde.AAS;
            default -> throw new IllegalArgumentException("geen geldige waarde");
        };
    }

    @Override
    public String toString() {
        return this.soort+" "+this.waarde;
    }
}
