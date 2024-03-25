package logica;

import java.util.Objects;

public class Vak {
    public final static int MAX_SCORE = 20;
    private String  code;
    private String naam;
    private int stp;
    private Semester sem;
    private int score;

    public Vak(String code, String naam, int stp){

        this(code, naam, stp, Semester.ONBEPAALD);
    }

    public Vak(String code, String naam, int stp, Semester sem){
        if (code == null|| naam == null || code.isEmpty()|| naam.isEmpty() || stp<0 || stp>20){
            throw new IllegalArgumentException("geen naam of code meegegeven");
        }
        this.code = code;
        this.naam = naam;
        this.stp = stp;
        this.sem = sem;
    }

    public Vak(Vak vak){
        this(vak.getCode(), vak.getNaam(), vak.getStp(), vak.getSem());
    }

    public String getCode(){
        return code;
    }

    public String getNaam() {
        return naam;
    }

    public int getStp() {
        return stp;
    }

    public Semester getSem() {
        return sem;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (!(0<=score && score <= MAX_SCORE)){
            throw new IllegalArgumentException("score: " + score + ", ligt niet tussen 0 en 20");
        }
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Vak a)) return false;
        return stp == a.stp && Objects.equals(code, a.code) && Objects.equals(naam.toLowerCase(), a.naam.toLowerCase()) && sem == a.sem;
    }

    @Override
    public String toString() {
        return naam + " (" + stp + "stp)";
    }
}
