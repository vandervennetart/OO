package logica;

import java.util.ArrayList;

public class KaartBoek {

    private ArrayList<Kaart> kaarten = new ArrayList<Kaart>();;


    public KaartBoek() {

        for (KaartSoort soort : KaartSoort.values()) {
            for (KaartWaarde waarde : KaartWaarde.values()) {
                this.kaarten.add(new Kaart(soort, waarde)) ;

            }
        }
    }

    public KaartBoek(KaartSoort[] soorten) {
        for (KaartSoort soort : soorten) {
            for (KaartWaarde waarde : KaartWaarde.values()) {
                this.kaarten.add(new Kaart(soort, waarde));

            }
        }

    }

    public KaartBoek(String[] kaarten) {

        for (String kaart : kaarten) {
            Kaart nieuwe_kaart = new Kaart(kaart);
            if(nieuwe_kaart.getSoort() != null && nieuwe_kaart.getWaarde() != null){

                try{
                    this.kaarten.add(nieuwe_kaart) ;
                }catch (IllegalArgumentException ignored) {
                }
            }

        }


    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public boolean bevatKaart(Kaart kaart) {
        for (Kaart k :kaarten) {
            if (k.isGelijkwaardigAan(kaart) && k.getSoort() == kaart.getSoort()){
                return true;
            }
        }
        return false;
    }

    public boolean bevatKaartVanSoort(KaartSoort soort) {
        return (! geefAlleKaartenVanSoort(soort).isEmpty());
    }

    public ArrayList<Kaart> geefAlleKaartenVanSoort(KaartSoort soort){
        ArrayList<Kaart> kaarten_van_soort = new ArrayList<Kaart>();
        for (Kaart k :kaarten) {
            if (k.getSoort() == soort){
                kaarten_van_soort.add(k);
            }
        }

        return kaarten_van_soort;
    }

    public ArrayList<Kaart> haalAlleKaartenVanSoort(KaartSoort soort){
        ArrayList<Kaart> kaarten_van_soort = new ArrayList<Kaart>();
        for (Kaart k :kaarten) {
            if (k.getSoort() == soort){
                kaarten_van_soort.add(k);

            }
        }

        for (Kaart toRemove :kaarten_van_soort) {
            kaarten.remove(toRemove);
        }

        return (!kaarten_van_soort.isEmpty())? kaarten_van_soort : null;
    }


    public Kaart geefRandomKaart() {
        if(kaarten.isEmpty()){
            return null;
        }
        Dobbelsteen dobbel = new Dobbelsteen(0, kaarten.size() - 1);
        dobbel.gooi();
        return kaarten.get(dobbel.get());
    }

    public Kaart haalRandomKaart(){
        if(kaarten.isEmpty()){
            return null;
        }

        Dobbelsteen dobbel = new Dobbelsteen(0, kaarten.size() - 1);
        dobbel.gooi();
        Kaart gegooid = kaarten.get(dobbel.get());
        kaarten.remove(gegooid);
        return gegooid;
    }

    public Kaart geefRandomKaartVanSoort(KaartSoort soort) {

        if(geefAlleKaartenVanSoort(soort).isEmpty()){
            return null;
        }

        Dobbelsteen dobbel = new Dobbelsteen(0, geefAlleKaartenVanSoort(soort).size() - 1);
            dobbel.gooi();

        return geefAlleKaartenVanSoort(soort).get(dobbel.get());
    }

    public Kaart haalRandomKaartVanSoort(KaartSoort soort) {
        if(geefAlleKaartenVanSoort(soort).isEmpty()){
            return null;
        }

        Dobbelsteen dobbel = new Dobbelsteen(0, geefAlleKaartenVanSoort(soort).size() - 1);
        dobbel.gooi();
        Kaart gegooid = geefAlleKaartenVanSoort(soort).get(dobbel.get());

        kaarten.remove(geefAlleKaartenVanSoort(soort).get(dobbel.get()));
        return gegooid;
    }

}
