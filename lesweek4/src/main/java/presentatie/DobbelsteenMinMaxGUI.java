package presentatie;

import logica.Dobbelsteen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DobbelsteenMinMaxGUI {
    private JPanel mainPanel;
    private JTextField min;
    private JTextField max;
    private JButton button1;
    private JLabel value;
    private JLabel error;

    public DobbelsteenMinMaxGUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.setText("");
                try{

                    int minimum = Integer.parseInt(min.getText());
                    int maximum = Integer.parseInt(max.getText());

                    Dobbelsteen dobbel = new Dobbelsteen(minimum, maximum);
                    dobbel.gooi();

                    value.setText(dobbel.toString());
                }catch (IllegalArgumentException a){
                    error.setText("ERROR: Invoervelden niet oke");
                }

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("DobbelsteenMinMaxGUI");
        frame.setContentPane(new DobbelsteenMinMaxGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
