package logica;

import javax.swing.*;

public class Cijfer extends Toets{
    public Cijfer(int cijfer) {
        super(String.valueOf(cijfer));
        if (cijfer > 9 || cijfer < 0) throw new IllegalArgumentException("cijfer out of range");
    }

    public Cijfer(int cijfer, ImageIcon afbeelding) {
        super(String.valueOf(cijfer), afbeelding);
        if (cijfer > 9 || cijfer < 0) throw new IllegalArgumentException("cijfer out of range");

    }
}
