package presentatie;


import logica.Rapport;
import logica.Vak;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class RapportGUI {
    private JPanel mainPanel;
    private JPanel input;


    private JLabel vakNaam1;
    private JLabel vakNaam2;
    private JLabel vakNaam3;
    private JLabel vakNaam4;
    private JLabel vakNaam5;
    private JLabel vakNaam6;

    private JTextField punten1;
    private JTextField punten3;
    private JTextField punten2;
    private JTextField punten4;
    private JTextField punten5;
    private JTextField punten6;



    private JLabel maxScoreLabel;
    private JLabel MaxScore;
    private JLabel Resultaat;


    private JLabel[] vakken = {vakNaam1, vakNaam2, vakNaam3, vakNaam4, vakNaam5, vakNaam6};
    private JTextField[] punten = {punten1, punten2, punten3, punten4, punten5, punten6};
    private Rapport rapport = new Rapport();

    private int aantalVakken;


    public RapportGUI(){
        rapport.setVakken(new Vak[]{new Vak("code_EN", "Elektronische Netwerken", 6), new Vak("code_PF", "Programming Fundamentals", 6), new Vak("code_WI", "Web Introduction", 6), new Vak("code_IF", "Infrastructure Fundamentals", 6), new Vak("code_ER", "Elektronische Realisatietechnieken", 6), new Vak("code_FSI", "Fundamental Skills for ICT", 6) });


        //rapport.setVakken(new Vak[]{new Vak("code_EN", "Elektronische Netwerken", 6), new Vak("code_PF", "Programming Fundamentals", 6)});

        aantalVakken = rapport.getVakken().length;

        MaxScore.setText("Max Score: " + String.valueOf(Vak.MAX_SCORE));

        int i;
        for (i = 0; i < aantalVakken; i++) {
            vakken[i].setText(rapport.getVakken()[i].getNaam());
            vakken[i].setVisible(true);
            punten[i].setVisible(true);
        }

        for (; i < vakken.length; i++) {
            vakken[i].setVisible(false);
            punten[i].setVisible(false);
        }


        FocusAdapter listener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                controlePunten(Arrays.asList(punten).indexOf(e.getSource()));
            }
        };
        punten1.addFocusListener(listener);
        punten2.addFocusListener(listener);
        punten3.addFocusListener(listener);
        punten5.addFocusListener(listener);
        punten6.addFocusListener(listener);
        punten4.addFocusListener(listener);

    }

    private void controlePunten(int index){
        try{
            int score = Integer.parseInt(punten[index].getText());
            rapport.getVakken()[index].setScore(score);

            if(rapport.getVakken()[index].getScore() >= 10){
                punten[index].setBackground(Color.green);
            }else{
                punten[index].setBackground(Color.orange);
            }

            boolean allesIngevuld = true;

            for (int i = 0; i < aantalVakken; i++) {
                if (punten[i].getText() == null || punten[i].getText().isEmpty()) allesIngevuld = false;
            }

            if (allesIngevuld){
                Resultaat.setText("Resultaat: " + rapport.toString());
            }
        }catch (IllegalArgumentException e){
            punten[index].setBackground(Color.red);
            Resultaat.setText("Score moet in  bereik [0,20] liggen");
            Resultaat.setForeground(Color.red);
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RapportGUI");
        frame.setContentPane(new RapportGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);




    }
}
