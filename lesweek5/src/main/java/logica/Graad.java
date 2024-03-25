package logica;

public enum Graad {
    ONBEPAALD,
    NIET_GESLAAGD,
    VOLDOENING,
    ONDERSCHEIDING,
    GROTE_ONDERSCHEIDING,
    GROOTSTE_ONDERSCHEIDING;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}
