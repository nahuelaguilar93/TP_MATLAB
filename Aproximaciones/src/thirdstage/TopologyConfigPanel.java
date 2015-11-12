package thirdstage;

import Data.Singleton;
import mathematics.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class TopologyConfigPanel extends JPanel {
	
	private JComboBox topologyList = new JComboBox();
	private JTextField textfield = new JTextField("1000");

	TopologyConfigPanel(){
		setBorder(BorderFactory.createTitledBorder("Stage Configuration"));

        topologyList.addItem("Select a Stage");

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
        Singleton s = Singleton.getInstance();
        Singleton_S3 s3 = Singleton_S3.getInstance();

        int index = s3.getStagePanel().getSelectedIndex();
        System.out.println("UpdateList con index = " + index);
        if ( index != -1 ) {
            System.out.println("Estoy por cambiar la lista");
            List<Stage> currentStage = s.getUserData().getStageList();
            List<String> filterString = currentStage.get(index).getList();
            for(Stage x : currentStage)
               System.out.println("Stage list sizes: " + x.getList().size());

            topologyList.removeAllItems();
            topologyList.addItem("JAMON");
            for (String x : filterString)
                topologyList.addItem(x);
        }
    }

    public String getSelectedString() { return (String) topologyList.getSelectedItem(); }
}