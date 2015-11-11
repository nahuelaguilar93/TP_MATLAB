package thirdstage;

import javax.swing.*;

public class ComponentsPanel extends JPanel{
	ComponentsPanel(){
        //TODO: crear una lista con R1, R2 y R3. Copiarse de FilterList en firstStage
        String[] componentList={"R1","R2","R3"};
		setBorder(BorderFactory.createTitledBorder("Components"));

		
		this.add(new JList(componentList));
		
	}
}
