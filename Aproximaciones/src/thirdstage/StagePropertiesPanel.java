package thirdstage;

import javax.swing.*;
//import java.awt.event.*;

public class StagePropertiesPanel extends JPanel{
	
	TopologyConfigPanel stageconfigpanel = new TopologyConfigPanel();
	ComponentsPanel componentspanel = new ComponentsPanel();
	SensitivitiesPanel sensitivitiespanel = new SensitivitiesPanel();
	
	StagePropertiesPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //TODO: stageConfig, componentsPanel y sensitivitiesPanel implementar en distintos paneles
        
        Singleton_S3 s = Singleton_S3.getInstance();
        
        stageconfigpanel= s.getTopologyConfigPanel();
        this.add(stageconfigpanel);
        
        componentspanel= s.getComponentsPanel();
        this.add(componentspanel);
        
        sensitivitiespanel= s.getSensitivitiesPanel();
        this.add(sensitivitiespanel);
        
		setBorder(BorderFactory.createTitledBorder("Stage Properties"));
		
	}
}
