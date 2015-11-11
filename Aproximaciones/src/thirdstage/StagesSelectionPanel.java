package thirdstage;

import javax.swing.*;
import java.awt.*;

public class StagesSelectionPanel extends JPanel{
	FilterCircuitPanel filterCircuitPanel;
    StagePanel stagePanel;

	StagesSelectionPanel(){
		Singleton_S3 s = Singleton_S3.getInstance();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //setBorder(BorderFactory.createTitledBorder("Stages"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        filterCircuitPanel = s.getFilterCircuitPanel();
        stagePanel = s.getStagePanel();

        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup().addComponent(filterCircuitPanel).addComponent(stagePanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup().addComponent(filterCircuitPanel).addComponent(stagePanel)
        );
        layout.linkSize(SwingConstants.VERTICAL, filterCircuitPanel, stagePanel);
	}
}
