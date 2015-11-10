package thirdstage;

import javax.swing.*;
import java.awt.*;

public class StagesSelectionPanel extends JPanel{
	FilterCircuitPanel filterCircuitPanel;
    //StagePanel stagePanel;

	StagesSelectionPanel(){
		Singleton_S3 s = Singleton_S3.getInstance();

        setBorder(BorderFactory.createTitledBorder("Stages"));

        filterCircuitPanel = s.getFilterCircuitPanel();
        //StagePanel = s.getStagePanel();

        this.add(filterCircuitPanel);
        //this.add(StagePanel);
	}
}
