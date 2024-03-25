package logica;

public enum Klasgroep {
    _1ICT1,
    _1ICT2,
    _1ICT3,
    _1ICT4,
    _1ICT5,
    _1ICT6,
    _1ICT7,
    _1ICT8,
    _1ELO1,
    _1ELO2,
    _1ELO3;

    @Override
    public String toString() {
        return name().replace("_", "");
    }
}
