package logica;

import javax.swing.*;
import java.util.Objects;

public abstract class Toets implements Comparable{
    private String weergavetekst;
    private ImageIcon afbeelding;

    public Toets(String weergavetekst) {
        this.weergavetekst = weergavetekst;
    }

    public Toets(String weergavetekst, ImageIcon afbeelding) {
        this(weergavetekst);
        this.afbeelding = afbeelding;
    }

    public String getWeergavetekst() {
        return weergavetekst;
    }

    public ImageIcon getAfbeelding() {
        return afbeelding;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Toets a)) throw new IllegalArgumentException("geen type Toets");
        return weergavetekst.compareTo(a.weergavetekst);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toets toets)) return false;

        return weergavetekst.equals(toets.weergavetekst);
    }

    @Override
    public String toString() {
        return weergavetekst;
    }


}
