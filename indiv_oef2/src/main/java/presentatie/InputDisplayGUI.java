package presentatie;

import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * InputDisplayGUI
 *
 * @author kristien.vanassche
 * @version 11/04/2024
 */
public class InputDisplayGUI {
    private JPanel panelMain;
    private JPanel panelIntern;
    private JRadioButton lettersRadioButton;
    private JRadioButton emoticonsRadioButton;
    private JRadioButton cijfersRadioButton;
    private JTextArea textAreaToetsen;
    private JTextArea textAreaToetsInfo;
    private JButton buttonSpatie;
    private JButton buttonBackspace;
    private JButton buttonClear;
    private Toetsenbord toetsenbord;

    public InputDisplayGUI() {
        lettersRadioButton.setSelected(true);

        try {
            toetsenbord = new Toetsenbord(Categorie.LETTER);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }

        if (toetsenbord == null) {
            JOptionPane.showMessageDialog(null, "Initialiseer je toetsenbord!");
            return;
        }
        else if (toetsenbord.getToetsen() == null) {
            JOptionPane.showMessageDialog(null, "Initialiseer de toetsenlijst van je toetsenbord!");
        }
        setKnoppenGUI();
        this.textAreaToetsInfo.setOpaque(false);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lettersRadioButton.isSelected()){
                    toetsenbord = new Toetsenbord(Categorie.LETTER);
                } else if (cijfersRadioButton.isSelected()) {
                    toetsenbord = new Toetsenbord(Categorie.CIJFER, 3);
                }else{
                    toetsenbord = new Toetsenbord(Categorie.EMOTICON);
                }
                setKnoppenGUI();
            }
        };
        lettersRadioButton.addActionListener(listener);
        cijfersRadioButton.addActionListener(listener);
        emoticonsRadioButton.addActionListener(listener);


        buttonSpatie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaToetsen.append(" ");
            }
        });
        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textAreaToetsen.getText();
                String newText = text.substring(0, text.length() - 1);
                textAreaToetsen.setText(newText);
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaToetsen.setText("");
            }
        });
    }

    private void setKnoppenGUI() {
        panelIntern.removeAll();

        int aantalkol = toetsenbord.getAantalKolommen();

        if (aantalkol <= 0) {
            aantalkol = (int) Math.sqrt(toetsenbord.getToetsen().size()) + 1;
        }
        panelIntern.setLayout(new GridLayout(0, aantalkol));


        for (Toets toets : toetsenbord.getToetsen()) {
            if (toets != null) {
                JButton button = new JButton(toets.getWeergavetekst());
                button.setName(toets.getWeergavetekst());

                if (toets instanceof IPictogram) {
                    button.setToolTipText(((IPictogram) toets).getNaam());
                }

                ToolTipManager.sharedInstance().registerComponent(button); //cf. https://stackoverflow.com/questions/21949967/jbutton-dynamic-tooltip-text-not-showing
                ToolTipManager.sharedInstance().setInitialDelay(0);
                ToolTipManager.sharedInstance().setDismissDelay(10000);

                if (toets.getAfbeelding() != null) {
                    Image image = toets.getAfbeelding().getImage();
                    image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                    button.setIcon(new ImageIcon(image));
                    button.setContentAreaFilled(false);
                    button.setBorder(BorderFactory.createLineBorder(Color.white, 2));
                }


                ActionListener btnlistener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textAreaToetsInfo.setText("");

                        JButton knop = (JButton) e.getSource();
                        Toets t = toetsenbord.getToets(knop.getName());
                        textAreaToetsen.append(t.getWeergavetekst());
                        if (t instanceof Emoticon a){
                            textAreaToetsInfo.setText(a.toString());
                        }
                    }
                };

                button.addActionListener(btnlistener);


                panelIntern.add(button);
            } else {
                panelIntern.add(new JButton("")); //dummy toets toevoegen
            }

            panelIntern.repaint();
            panelIntern.revalidate();
        }
    }

    private void createUIComponents() {
        panelIntern = new JPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Toetsenbord 2024");
        frame.setContentPane(new presentatie.InputDisplayGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1300, 500);
        frame.setVisible(true);
    }
}
