package logica;

import javax.swing.*;

public class Emoticon extends Toets implements IPictogram{
    private String naam;

    public Emoticon(String code,String naam, ImageIcon afbeelding) {
        super(code, afbeelding);

        if (code == null || naam == null || code.isEmpty() || naam.isEmpty()) throw new IllegalArgumentException("code of naam is empty");
        this.naam = naam;
    }

    public Emoticon(String code,String naam) {
        super(code);
        if (code == null || naam == null || code.isEmpty() || naam.isEmpty()) throw new IllegalArgumentException("code of naam is empty");
        this.naam = naam;
    }

    @Override
    public String getCode() {
        return getWeergavetekst();
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public String toString() {
        return getCode() + " " + getNaam();
    }



    @Override
    public int compareTo(Object o){
        if (!(o instanceof Emoticon a)) throw new IllegalArgumentException("not an emoticon");

        return naam.compareTo(a.naam);
    }

}
