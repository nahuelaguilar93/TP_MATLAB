package thirdstage;

import javax.swing.*;
//import java.awt.event.*;

public class StagePropertiesPanel extends JPanel{
        
	SensitivitiesPanel sensitivitiespanel = new SensitivitiesPanel();
	
	StagePropertiesPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //TODO: stageConfig, componentsPanel y sensitivitiesPanel implementar en distintos paneles
        
        Singleton_S3 s = Singleton_S3.getInstance();

        sensitivitiespanel= s.getSensitivitiesPanel();
        this.add(sensitivitiespanel);
	}
}
