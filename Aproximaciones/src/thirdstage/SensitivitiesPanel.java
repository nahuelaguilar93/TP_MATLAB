package thirdstage;

import javax.swing.*;
import java.awt.*;

public class SensitivitiesPanel extends JPanel{
	
	String[] lista={"R1","R2","R3"};  //supongo qe cambias esto..
	
	SensitivitiesPanel(){
		
		setBorder(BorderFactory.createTitledBorder("Sensitivities"));
		
		//FIJATE ESTOO JAJA
		
		JList<String> list = new JList<String>(lista); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80)); 
		
		this.add(listScroller);
	}
}
