package thirdstage;

import javax.swing.*;
import java.awt.*;

public class StagesSelectionPanel extends JPanel{
	FilterCircuitPanel filterCircuitPanel;
	StagePanel stagepanel= new StagePanel();
    //StagePanel stagePanel;

	StagesSelectionPanel(){
		Singleton_S3 s = Singleton_S3.getInstance();

        setBorder(BorderFactory.createTitledBorder("Stages"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        filterCircuitPanel = s.getFilterCircuitPanel();
        stagepanel = s.getStagePanel();

        this.add(filterCircuitPanel);
        this.add(stagepanel);
	}
}
