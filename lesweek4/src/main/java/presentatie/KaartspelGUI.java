package presentatie;

import logica.KaartBoek;
import logica.KaartSoort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KaartspelGUI {
    private JPanel mainPage;
    private JButton nieuwDek;
    private JButton randomKaartVanSoortButton;
    private JButton randomKaartButton;
    private JLabel aantalKaarten;
    private JTextField soort;
    private JLabel output;
    private KaartBoek kaartBoek = new KaartBoek();;


    public KaartspelGUI() {

        nieuwDek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setForeground(Color.black);
                kaartBoek = new KaartBoek();
                output.setText("Nieuw kaartenboek");
                aantalKaarten.setText(String.valueOf(kaartBoek.getKaarten().size()));

                randomKaartButton.setEnabled(true);
                randomKaartVanSoortButton.setEnabled(true);
            }
        });

        randomKaartVanSoortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    output.setForeground(Color.black);
                    KaartSoort i = KaartSoort.valueOf(soort.getText());
                    output.setText(kaartBoek.haalRandomKaartVanSoort(i).toString());
                    aantalKaarten.setText(String.valueOf(kaartBoek.getKaarten().size()));
                }catch (NullPointerException | IllegalArgumentException a){
                    output.setForeground(Color.red);
                    if (a instanceof IllegalArgumentException){
                        output.setText("ongeldige soort");
                    }else{

                        output.setText("geen kaart van soort");
                    }

                }

            }
        });
        randomKaartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    output.setForeground(Color.black);
                    output.setText(kaartBoek.haalRandomKaart().toString());
                    aantalKaarten.setText(String.valueOf(kaartBoek.getKaarten().size()));
                }catch (NullPointerException a){
                    output.setForeground(Color.red);
                    output.setText("kaartenboek leeg");
                    randomKaartButton.setEnabled(false);
                    randomKaartVanSoortButton.setEnabled(false);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("KaartspelGUI");
        frame.setContentPane(new KaartspelGUI().mainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
