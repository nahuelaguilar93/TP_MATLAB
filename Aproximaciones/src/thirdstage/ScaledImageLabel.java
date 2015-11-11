package thirdstage;

import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;

/**
 * This is an extended version of a JLabel which draws its icon image using
 * the ImageDrawer utility.
 *
 * @author www.codejava.net
 *
 */
public class ScaledImageLabel extends JLabel {
    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        if (icon != null) {
            ImageDrawer.drawScaledImage(icon.getImage(), this, g);
        }
    }
}


