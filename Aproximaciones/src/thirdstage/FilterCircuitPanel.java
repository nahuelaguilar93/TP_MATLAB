package thirdstage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FilterCircuitPanel extends JPanel{
    private BufferedImage image;
    private Singleton_S3 s = Singleton_S3.getInstance();
    private JLabel imageLabel = s.getScaledImageLabel();
    //private JButton testButton = new JButton("Update Image");

	FilterCircuitPanel(){
		setBorder(BorderFactory.createTitledBorder("Filter Circuit"));

        /*testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage("BPSallen");
            }
        });
*/
        this.add(imageLabel);
        //this.add(testButton);
    }

    public void updateImage(String path) {
        try {
            System.out.println("Looking for image" + path + ".jpg");
            Image image = ImageIO.read(new File("./images/" + path + ".jpg"));
            imageLabel.setIcon(new ImageIcon(image));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
