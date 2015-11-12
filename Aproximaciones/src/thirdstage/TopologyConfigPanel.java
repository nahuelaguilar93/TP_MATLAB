package thirdstage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class TopologyConfigPanel extends JPanel {
	
	private JComboBox topologyList = new JComboBox();
	private JTextField textfield = new JTextField("1000");
    private ArrayList<String> filterString = new ArrayList<String>(Arrays.asList("HPSallen", "LPSallen", "Ackerberg Mossberg", "BPRauch", "BPSallen", "BRSallen", "Fleischer Tow", "Kerwin Huelsman Newcomb", "Tow Thomas", "HPRauch", "LPRauch"));
	
	
	TopologyConfigPanel(){
		setBorder(BorderFactory.createTitledBorder("Stage Configuration"));

        topologyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S3 s = Singleton_S3.getInstance();
                s.getComponentsPanel().updateComponentList();
                s.getFilterCircuitPanel().updateImage((String) topologyList.getSelectedItem());
                s.getSensitivitiesPanel().updateImage((String) topologyList.getSelectedItem());
            }
        });

        updateList();
		this.add(topologyList);
		this.add(new JLabel("R1 [ohm]"));
		this.add(textfield);
	}

	public int getIndex() { return topologyList.getSelectedIndex(); }

    public void updateList() {
        //Approximation.getStringsToComboBox(uData.getCurrentTemplate());
        topologyList.removeAllItems();
        for (String x : filterString)
            topologyList.addItem(x);
    }

    public String getSelectedString() { return (String) topologyList.getSelectedItem(); }
}
