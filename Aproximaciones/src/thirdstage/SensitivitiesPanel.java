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
			Image image = ImageIO.read(new File("./images/" + path + ".jpg"));
			imageLabel.setIcon(new ImageIcon(image));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*

String[] lista={"R1","R2","R3"};  //supongo qe cambias esto..
	String[] lista2={"R1","R2","R3","R4","R5"};
setBorder(BorderFactory.createTitledBorder("Sensitivities"));

		//FIJATE ESTOO JAJA

		JList<String> list = new JList<String>(lista); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		this.add(listScroller);

		// PROBANDO TABLA

		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

		Object[][] data = {
			    {"S", "Campione",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"Q", "Huml",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Wp", "Walrath",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Wz", "Zakhour",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"G", "Milne",
			     "Pool", new Integer(10), new Boolean(false)}
			};

		JTable table = new JTable(data,columnNames);

		add(table);
 */
