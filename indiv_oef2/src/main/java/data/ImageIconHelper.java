package data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageIconHelper {
    public static ImageIcon haalImageIcon(String naam) {
        Image afbeelding;
        ImageIcon imageIcon = null;

        try {
            afbeelding = ImageIO.read(ImageIconHelper.class.getResource("/images/" + naam + ".png"));
            afbeelding = afbeelding.getScaledInstance(200,200, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(afbeelding);
        }
        catch(Exception e) {
            try {
                afbeelding = ImageIO.read(ImageIconHelper.class.getResource("/images/default.jpg"));
                imageIcon = new ImageIcon(afbeelding);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return imageIcon;
    }
}
