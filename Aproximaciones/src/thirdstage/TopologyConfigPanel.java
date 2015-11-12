package thirdstage;

import Data.Singleton;
import mathematics.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class TopologyConfigPanel extends JPanel {
	
	private JComboBox topologyList = new JComboBox();
	//private JTextField textfield = new JTextField("1000");

	TopologyConfigPanel(){
		setBorder(BorderFactory.createTitledBorder("Stage Configuration"));

        topologyList.addItem("LPSallen");

        topologyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S3 s = Singleton_S3.getInstance();
                s.getComponentsPanel().updateComponentList();
                if ( topologyList.getSelectedIndex() != -1 ) {
                    s.getFilterCircuitPanel().updateImage((String) topologyList.getSelectedItem());
                    s.getSensitivitiesPanel().updateImage((String) topologyList.getSelectedItem());
                }
            }
        });

        updateList();
		this.add(topologyList);
		//this.add(new JLabel("R1 [ohm]"));
		//this.add(textfield);
	}

	public int getIndex() {
        return topologyList.getSelectedIndex();
    }

    public void updateList() {
        Singleton s = Singleton.getInstance();
        Singleton_S3 s3 = Singleton_S3.getInstance();

        int index = s3.getStagePanel().getSelectedIndex();
        if ( index != -1 ) {
            List<Stage> currentStage = s.getUserData().getStageList();
            List<String> filterString = currentStage.get(index).getList();

            topologyList.removeAllItems();
            for (String x : filterString)
                topologyList.addItem(x);
        }
    }

    public String getSelectedString() { return (String) topologyList.getSelectedItem(); }
}