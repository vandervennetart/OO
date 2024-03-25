package logica;

import java.util.ArrayList;
import java.util.Arrays;

public class Rapport {
    private Vak[] vakken;

    public Rapport() {
        this(new Vak[0]);
    }

    public Rapport(Vak[] vakken) {
        this.vakken = vakken;
    }

    public Vak[] getVakken() {
        return vakken;
    }

    public void setVakken(Vak[] vakken) {
        this.vakken = vakken;
    }

    public double geefGewogenResultaatProcent() {
        if (vakken.length <= 0) return 0;

        double[] percentPerVak = new double[vakken.length];

        int i = 0;
        for (Vak vak : vakken) {
            percentPerVak[i] = (double) vak.getScore() / Vak.MAX_SCORE;
            i++;
        }

        double resultaat = 0;
        int stp = 0;
        for (int j = 0; j < vakken.length; j++) {
            resultaat += percentPerVak[j] * vakken[j].getStp();
            stp += vakken[j].getStp();
        }



        return Helper.afronden((resultaat/stp)*100, 2);

    }

    public Graad geefGraad() {
        double punten = geefGewogenResultaatProcent();
        if (!(alleVakkenGeslaagd()) || punten < 50) return Graad.NIET_GESLAAGD;

        if (punten < 65){
            return Graad.VOLDOENING;
        } else if (punten < 75) {
            return Graad.ONDERSCHEIDING;
        } else if (punten < 85) {
            return Graad.GROTE_ONDERSCHEIDING;
        } else {
            return Graad.GROOTSTE_ONDERSCHEIDING;
        }
    }

    private boolean alleVakkenGeslaagd(){

        for (Vak vak :vakken) {
            if (vak.getScore()<10) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return geefGewogenResultaatProcent() + "% - " + geefGraad() ;
    }
}
