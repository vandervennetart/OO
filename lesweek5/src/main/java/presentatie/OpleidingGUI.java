package presentatie;

import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class OpleidingGUI {
    private JPanel mainPanel;
    private JPanel Rapport;
    private JLabel vakNaam1;
    private JLabel vakNaam2;
    private JLabel vakNaam3;
    private JLabel vakNaam4;
    private JLabel vakNaam5;
    private JLabel vakNaam6;
    private JTextField punten1;
    private JTextField punten2;
    private JTextField punten3;
    private JTextField punten5;
    private JTextField punten6;
    private JTextField punten4;
    private JLabel MaxScore;
    private JLabel Resultaat;
    private JTextField studentennummer;
    private JButton addButton;
    private JButton verwijderButton;
    private JComboBox klasgroep;
    private JComboBox filter;
    private JPanel studentManager;
    private JTextField naam;
    private JTextPane students;
    private JList studentenLijst;
    private JLabel error;


    private JLabel[] vakken = {vakNaam1, vakNaam2, vakNaam3, vakNaam4, vakNaam5, vakNaam6};
    private JTextField[] punten = {punten1, punten2, punten3, punten4, punten5, punten6};
    private logica.Rapport rapport;
    private Opleiding opleiding;

    private int aantalVakken;







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

    private void reset(){
        rapport = new Rapport();
        rapport.setVakken(new Vak[]{new Vak("code_EN", "Elektronische Netwerken", 6), new Vak("code_PF", "Programming Fundamentals", 6), new Vak("code_WI", "Web Introduction", 6), new Vak("code_IF", "Infrastructure Fundamentals", 6), new Vak("code_ER", "Elektronische Realisatietechnieken", 6), new Vak("code_FSI", "Fundamental Skills for ICT", 6) });
        aantalVakken = rapport.getVakken().length;

        for (Component c : studentManager.getComponents()) {
            if (c instanceof JTextField){
                ((JTextField) c).setText("");

            }

        }



        int i;
        for (i = 0; i < aantalVakken; i++) {
            vakken[i].setText(rapport.getVakken()[i].getNaam());
            vakken[i].setVisible(true);
            punten[i].setVisible(true);
            punten[i].setBackground(Color.white);
            punten[i].setText("");
        }

        for (; i < vakken.length; i++) {
            vakken[i].setVisible(false);
            punten[i].setVisible(false);
        }


    }

    public OpleidingGUI() {



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    error.setText("");

                    String studentNumber = studentennummer.getText();
                    String studentNaam = naam.getText();
                    Klasgroep studentKlasgroep = (Klasgroep) klasgroep.getSelectedItem();

                    Student s = new Student(studentNumber, studentNaam, studentKlasgroep);
                    s.setRapport(rapport);
                    opleiding.voegStudentToe(s);
                    doeFilter();

                    reset();
                } catch (IllegalArgumentException a) {
                    error.setText("not a valid student");
                    error.setForeground(Color.red);
                }


            }
        });
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doeFilter();

            }
        });
        verwijderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    error.setText("");
                    String studentNumber = studentennummer.getText();
                    String studentNaam = naam.getText();
                    Klasgroep studentKlasgroep = (Klasgroep) klasgroep.getSelectedItem();

                    Student s = new Student(studentNumber, studentNaam, studentKlasgroep);
                    opleiding.verwijderStudent(s);
                    doeFilter();
                    reset();
                } catch (IllegalArgumentException a) {
                    error.setText("not a valid student");
                    error.setForeground(Color.red);
                }

            }
        });


        klasgroep.setModel(new DefaultComboBoxModel(Klasgroep.values()));
        filter.setModel(new DefaultComboBoxModel(Graad.values()));
        MaxScore.setText("Max Score: " + String.valueOf(Vak.MAX_SCORE));

        opleiding = new Opleiding("ICT-Elektronica");

        reset();





        //rapport.setVakken(new Vak[]{new Vak("code_EN", "Elektronische Netwerken", 6), new Vak("code_PF", "Programming Fundamentals", 6)});


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

    public static void main(String[] args) {
        JFrame frame = new JFrame("OpleidingGUI");
        frame.setContentPane(new OpleidingGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);




    }


    private void doeFilter(){
        if ((Graad) filter.getSelectedItem() == Graad.ONBEPAALD){
            studentenLijst.setListData(opleiding.getStudenten().toArray());
        }else{
            studentenLijst.setListData(opleiding.geefStudentenMetGraad((Graad) filter.getSelectedItem()).toArray());
        }
    }

}
