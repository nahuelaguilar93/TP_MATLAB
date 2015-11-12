package thirdstage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SensitivitiesPanel extends JPanel{
	private BufferedImage image;
	private Singleton_S3 s = Singleton_S3.getInstance();
	private JLabel imageLabel = new ScaledImageLabel();

	SensitivitiesPanel(){
		setBorder(BorderFactory.createTitledBorder("Sensitivities"));

		this.add(imageLabel);
	}

	public void updateImage(String path) {
		try {
			Image image = ImageIO.read(new File("./images/" + path + "Table.jpg"));
			imageLabel.setIcon(new ImageIcon(image));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}