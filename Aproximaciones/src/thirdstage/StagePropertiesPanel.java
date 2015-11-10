package thirdstage;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class StagePropertiesPanel extends JPanel{
	
	StagePropertiesPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //TODO: stageConfig, componentsPanel y sensitivitiesPanel implementar en distintos paneles

		setBorder(BorderFactory.createTitledBorder("Stage Properties"));
		

		JPanel stageConfig = new JPanel();
        //TODO: para este mirate AproxComboBox y ConfigLowPassPanel metiendola en una sola clase
        //TODO: Me chupa un huevo el layout que uses en este... de ultima ni lo especifiques y que lo haga solo.
		stageConfig.setBorder(BorderFactory.createTitledBorder("Stage Configuration"));
		//create combo box
		//stageConfig.add(new JComboBox<String>(lista));
		
		JPanel componentsPanel = new JPanel();
        //TODO: crear una lista con R1, R2 y R3. Copiarse de FilterList en firstStage
        String[] componentList={"R1","R2","R3"};
		componentsPanel.setBorder(BorderFactory.createTitledBorder("Components"));

		
		JPanel sensitivitiesPanel = new JPanel();
		sensitivitiesPanel.setBorder(BorderFactory.createTitledBorder("Sensitivities"));
		//TODO: nunca hice una puta tabla en mi vida... Fijate de internet y sino lo hago yo... dejalo para el final sino asi lo ahces solo si te sobra el tiempo
		//create list
		/*JList<String> list = new JList<String>(lista); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));    */
		
		add(stageConfig);
		add(componentsPanel);
		add(sensitivitiesPanel);
	}
}
