package thirdstage;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class StageConfigPanel extends JPanel {
	
	private JComboBox approxList = new JComboBox();
	private JTextField textfield = new JTextField("1000");
	
	
	StageConfigPanel(){
		setBorder(BorderFactory.createTitledBorder("Stage Configuration"));

		
		this.add(approxList);
		this.add(new JLabel("R1 [ohm]"));
		this.add(textfield);
	}

	public int getIndex() { return approxList.getSelectedIndex(); }

    public void updateList() {
        ArrayList<String> filterString = new ArrayList<String>(Arrays.asList("HPSallen", "LPSallen"));   //Approximation.getStringsToComboBox(uData.getCurrentTemplate());
        approxList.removeAllItems();
        for (String x : filterString)
            approxList.addItem(x);
    }
}
