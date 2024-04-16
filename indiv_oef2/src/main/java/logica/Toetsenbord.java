package logica;

import data.ImageIconHelper;
import data.ToetsData;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

public class Toetsenbord {
    private int aantalKolommen;
    private List<Toets> toetsen;

    public Toetsenbord(Categorie categorie, int aantalKolommen){
        this(categorie);
        this.aantalKolommen = aantalKolommen;
    }

    public Toetsenbord(Categorie categorie){
        toetsen = new ArrayList<>();
        if (categorie == Categorie.LETTER){
            toetsen.add(new Letter('A'));
            toetsen.add(new Letter('B'));
            toetsen.add(new Letter('C'));
            toetsen.add(new Letter('D'));
            toetsen.add(new Letter('E'));
            toetsen.add(new Letter('F'));
            toetsen.add(new Letter('G'));
            toetsen.add(new Letter('H'));
            toetsen.add(new Letter('I'));
            toetsen.add(new Letter('J'));
            toetsen.add(new Letter('K'));
            toetsen.add(new Letter('L'));
            toetsen.add(new Letter('M'));
            toetsen.add(new Letter('N'));
            toetsen.add(new Letter('O'));
            toetsen.add(new Letter('P'));
            toetsen.add(new Letter('Q'));
            toetsen.add(new Letter('R'));
            toetsen.add(new Letter('S'));
            toetsen.add(new Letter('T'));
            toetsen.add(new Letter('U'));
            toetsen.add(new Letter('V'));
            toetsen.add(new Letter('W'));
            toetsen.add(new Letter('X'));
            toetsen.add(new Letter('Y'));
            toetsen.add(new Letter('Z'));
        } else if (categorie == Categorie.CIJFER) {
            toetsen.add(new Cijfer(7));
            toetsen.add(new Cijfer(8));
            toetsen.add(new Cijfer(9));
            toetsen.add(new Cijfer(4));
            toetsen.add(new Cijfer(5));
            toetsen.add(new Cijfer(6));
            toetsen.add(new Cijfer(1));
            toetsen.add(new Cijfer(2));
            toetsen.add(new Cijfer(3));
            toetsen.add(new Cijfer(0));
        }else{
            for (String e : ToetsData.INFO_EMOTICONS) {
                String[] eInfo =  new String[2];
                eInfo = e.split(" ");
                toetsen.add(new Emoticon(eInfo[0], eInfo[1], ImageIconHelper.haalImageIcon(eInfo[1])));
            }

            toetsen.sort(null);

        }
    }


    public List<Toets> getToetsen() {
        return toetsen;
    }

    public int getAantalKolommen() {
        return aantalKolommen;
    }

    public Toets getToets(String weergavetekst) {
        for (Toets toets : toetsen) {
            if (Objects.equals(toets.getWeergavetekst(), weergavetekst)) return toets;
        }
        return null;

    }
}
