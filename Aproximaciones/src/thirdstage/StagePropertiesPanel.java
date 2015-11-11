package thirdstage;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class StagePropertiesPanel extends JPanel{
	
	StageConfigPanel stageconfigpanel=new StageConfigPanel();
	ComponentsPanel componentspanel=new ComponentsPanel();
	SensitivitiesPanel sensitivitiespanel = new SensitivitiesPanel();
	
	StagePropertiesPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //TODO: stageConfig, componentsPanel y sensitivitiesPanel implementar en distintos paneles
        
        Singleton_S3 s = Singleton_S3.getInstance();
        
        stageconfigpanel= s.getStageConfigPanel();
        this.add(stageconfigpanel);
        
        componentspanel= s.getComponentsPanel();
        this.add(componentspanel);
        
        sensitivitiespanel= s.getSensitivitiesPanel();
        this.add(sensitivitiespanel);
        
		setBorder(BorderFactory.createTitledBorder("Stage Properties"));
		
	}
}
