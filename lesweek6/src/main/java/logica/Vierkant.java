package logica;

public class Vierkant extends Figuur{

    private double zijde;


    public Vierkant() {
        this(50);
    }

    public Vierkant(double zijde) {
        if (zijde<0) throw new IllegalArgumentException("zijde mag niet negatief zijn");
        this.zijde = zijde;
    }

    public Vierkant(double zijde, Kleur kleur, Kleur kleurRand, int dikteRand) {
        super(kleur, kleurRand, dikteRand);
        if (zijde<0) throw new IllegalArgumentException("zijde mag niet negatief zijn");
        this.zijde = zijde;
    }

    public Vierkant(Punt middelpunt, double zijde){
        this(zijde);
        this.setMiddelpunt(middelpunt);
    }
    public Vierkant(Punt middelpunt, double zijde, Kleur kleur, Kleur kleurRand, int dikteRand){
        super(kleur, kleurRand, dikteRand);
        if (zijde<0) throw new IllegalArgumentException("zijde mag niet negatief zijn");
        this.zijde = zijde;
        this.setMiddelpunt(middelpunt);

    }

    public double getZijde() {
        return zijde;
    }

    @Override
    public double berekenOmtrek() {
        return zijde*4;
    }

    @Override
    public double berekenOppervlakte() {
        return Math.pow(zijde, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vierkant vierkant)) return false;
        if (!super.equals(o)) return false;

        return Double.compare(zijde, vierkant.zijde) == 0;
    }



    @Override
    public String toString() {
        return "Vierkant: " + super.toString() + " Z=" + zijde + "cm";
    }
}
