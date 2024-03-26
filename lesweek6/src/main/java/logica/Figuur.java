package logica;

import java.util.Objects;

public abstract class Figuur implements Comparable {
    private Kleur kleur;
    private Kleur kleurRand;
    private int dikteRand;
    private Punt middelpunt;

    public Figuur(){
        this(Kleur.WIT, Kleur.ZWART, 1);
    }

    public Figuur(Kleur kleur, Kleur kleurRand, int dikteRand) {

        if (dikteRand < 0) throw new IllegalArgumentException("dikteRand kan niet negatief zijn");

        this.middelpunt = new Punt(0, 0);
        this.kleur = kleur;
        this.kleurRand = kleurRand;
        this.dikteRand = dikteRand;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public Kleur getKleurRand() {
        return kleurRand;
    }

    public int getDikteRand() {
        return dikteRand;
    }

    public Punt getMiddelpunt() {
        return middelpunt;
    }

    public void setMiddelpunt(Punt middelpunt) {
        this.middelpunt = middelpunt;
    }

    @Override
    public String toString() {
        return kleur + "/" + kleurRand + " " + getDikteRand() +"cm " + middelpunt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Figuur figuur)) return false;

        if (dikteRand != figuur.dikteRand) return false;
        if (kleur != figuur.kleur) return false;
        if (kleurRand != figuur.kleurRand) return false;
        return Objects.equals(middelpunt, figuur.middelpunt);
    }

    public double berekenAfstand(Figuur f){
        return berekenAfstand(f.getMiddelpunt());
    }

    public double berekenAfstand(Punt p){
        return middelpunt.berekenAfstand(p);
    }

    public abstract double berekenOmtrek();

    public abstract double berekenOppervlakte();

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Figuur a)) throw new IllegalArgumentException();
        if (this.berekenOmtrek() > a.berekenOmtrek()) return -1;
        if (this.berekenOmtrek() < a.berekenOmtrek()) return 1;

        return this.middelpunt.compareTo(a.getMiddelpunt());




    }
}
