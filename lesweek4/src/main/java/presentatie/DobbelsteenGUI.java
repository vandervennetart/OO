package presentatie;

import logica.Dobbelsteen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DobbelsteenGUI {
    private JButton buttonGooi;
    private JPanel mainPanel;
    private JLabel Worp;
    private JLabel worp;

    public DobbelsteenGUI() {
        buttonGooi.addActionListener(new ActionListener() {

            Dobbelsteen dobbel = new Dobbelsteen();
            @Override
            public void actionPerformed(ActionEvent e) {
                dobbel.gooi();
                worp.setText(dobbel.toString());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DobbelsteenGUI");
        frame.setContentPane(new DobbelsteenGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
