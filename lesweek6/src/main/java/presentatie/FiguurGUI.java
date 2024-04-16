package presentatie;

import logica.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class FiguurGUI {
    private JPanel mainPanel;
    private JList componentList;
    private JRadioButton cirkel;
    private JTextField x;
    private JTextField y;
    private JComboBox kleur;
    private JComboBox kleurRand;
    private JTextField grootte;
    private JSpinner dikteRand;
    private JRadioButton vierkant;
    private JButton addButton;
    private JButton undoButton;
    private JLabel error;
    private JButton sorteerButton;
    private JTextArea textArea1;

    private ArrayList<Figuur> gekozenFiguren = new ArrayList<>();


    public FiguurGUI(){
        //set values to comboBoxes
        kleur.setModel(new DefaultComboBoxModel(Kleur.values()));
        kleurRand.setModel(new DefaultComboBoxModel(Kleur.values()));


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (!allesIngevult()) throw new IllegalArgumentException("Niet alle velden zijn ingevuld");

                    if (cirkel.isSelected()){
                        gekozenFiguren.add(new Cirkel(new Punt(Integer.parseInt(x.getText()),Integer.parseInt(y.getText())) , (double) Integer.parseInt(grootte.getText()), (Kleur) kleur.getSelectedItem() , (Kleur) kleurRand.getSelectedItem(), (Integer) dikteRand.getValue()));
                    }else{
                        gekozenFiguren.add(new Vierkant(new Punt(Integer.parseInt(x.getText()),Integer.parseInt(y.getText())) , (double) Integer.parseInt(grootte.getText()), (Kleur) kleur.getSelectedItem() , (Kleur) kleurRand.getSelectedItem(), (Integer) dikteRand.getValue()));

                    }
                    componentList.setListData(gekozenFiguren.toArray());

                }catch (IllegalArgumentException a){
                    error.setText(a.getMessage());
                }


            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gekozenFiguren.toArray().length > 0){
                    gekozenFiguren.removeLast();
                    componentList.setListData(gekozenFiguren.toArray());
                }
            }
        });
        sorteerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(gekozenFiguren);
                componentList.setListData(gekozenFiguren.toArray());
            }
        });
    }

    private boolean allesIngevult(){
        return (!Objects.equals(x.getText(), "")) && (!Objects.equals(x.getText(), ""))  && (!Objects.equals(grootte.getText(), "")) && (!Objects.equals(dikteRand.getValue(), ""));
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Figuur");
        frame.setContentPane(new FiguurGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setVisible(true);
    }
}
