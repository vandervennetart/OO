package logica;

public class Cirkel extends Figuur{

    private double straal;


    public Cirkel() {
        this(50);
    }

    public Cirkel(double straal) {
        if (straal<0) throw new IllegalArgumentException("straal mag niet negatief zijn");
        this.straal = straal;
    }

    public Cirkel(double straal, Kleur kleur, Kleur kleurRand, int dikteRand) {
        super(kleur, kleurRand, dikteRand);
        if (straal<0) throw new IllegalArgumentException("straal mag niet negatief zijn");
        this.straal = straal;
    }

    public Cirkel(Punt middelpunt, double straal){
        this(straal);
        this.setMiddelpunt(middelpunt);
    }
    public Cirkel(Punt middelpunt, double straal, Kleur kleur, Kleur kleurRand, int dikteRand){
        super(kleur, kleurRand, dikteRand);
        if (straal<0) throw new IllegalArgumentException("straal mag niet negatief zijn");
        this.straal = straal;
        this.setMiddelpunt(middelpunt);

    }

    public double getStraal() {
        return straal;
    }

    @Override
    public double berekenOmtrek() {
        return 2*Math.PI*straal;
    }

    @Override
    public double berekenOppervlakte() {
        return Math.PI*Math.pow(straal, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cirkel cirkel)) return false;
        if (!super.equals(o)) return false;

        return Double.compare(straal, cirkel.straal) == 0;
    }

    @Override
    public String toString() {
        return "Cirkel: " + super.toString() + " R=" + straal + "cm";
    }
}
