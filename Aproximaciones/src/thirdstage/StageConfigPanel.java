package thirdstage;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class StageConfigPanel extends JPanel {
	
	private JComboBox approxList = new JComboBox();
	private JTextField textfield = new JTextField("nose que va aca");
	
	
	StageConfigPanel(){
	    //TODO: para este mirate AproxComboBox y ConfigLowPassPanel metiendola en una sola clase
	    //TODO: Me chupa un huevo el layout que uses en este... de ultima ni lo especifiques y que lo haga solo.
		setBorder(BorderFactory.createTitledBorder("Stage Configuration"));
		
		this.add(approxList);
	    //updateList();
		
		this.add(new JLabel(">>>"));
		
		//textfield.setLayout(new BoxLayout(textfield, BoxLayout.X_AXIS));
		this.add(textfield);
		
	}

	public int getIndex() { return approxList.getSelectedIndex(); }

	/*public void updateList() {
	    //Here I update the ComboBox. ex. I don't want the Aproximation list to have Bessel if I have a HighPass template
	    UserData uData = Singleton.getInstance().getUserData();
	    List<String> approxString = Approximation.getStringsToComboBox(uData.getCurrentTemplate());
	    approxList.removeAllItems();
	    for (String x : approxString)
	    approxList.addItem(x);
	}*/
}
