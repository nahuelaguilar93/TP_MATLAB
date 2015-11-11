package thirdstage;

import java.awt.*;
import java.awt.List;
import java.util.*;
import javax.swing.*;

public class StagePanel extends JPanel{
	PlotPanel plotpanel;
	StageInfoPanel stageinfopanel;
	
	StagePanel(){
		setBorder(BorderFactory.createTitledBorder("Stages"));
		setBounds(0, 0, 724, 512);
		Singleton_S3 s = Singleton_S3.getInstance();
		
		String[] componentList={"R1","R2","R3"};
		this.add(new JList(componentList));
		
		plotpanel = s.getPlotPanel();
		stageinfopanel = s.getStageInfoPanel();
		
		this.add(plotpanel);
		this.add(stageinfopanel);
	}
}
