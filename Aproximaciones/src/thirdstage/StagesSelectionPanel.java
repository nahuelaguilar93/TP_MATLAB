package thirdstage;

import secondstage.PlotPoleZeroPanel;
import secondstage.StageProperties;

import javax.swing.*;
import java.awt.*;

public class StagesSelectionPanel extends JPanel{
	FilterCircuitPanel filterCircuitPanel;
    StagePanel stagePanel;
    TopologyConfigPanel stageconfigpanel = new TopologyConfigPanel();
    ComponentsPanel componentspanel = new ComponentsPanel();
    StageProperties stageProperties;
    PlotPoleZeroPanel plotPoleZeroPanel;


	StagesSelectionPanel(){
		Singleton_S3 s = Singleton_S3.getInstance();
        //setBorder(BorderFactory.createTitledBorder("Stages"));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        filterCircuitPanel = s.getFilterCircuitPanel();
        stagePanel = s.getStagePanel();
        componentspanel= s.getComponentsPanel();
        stageconfigpanel= s.getTopologyConfigPanel();
        stageProperties = s.getStageProperties();
        plotPoleZeroPanel = s.getPlotPoleZeroPanel();

       //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(filterCircuitPanel)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(stagePanel)
                                .addComponent(stageconfigpanel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(componentspanel)
                                .addComponent(stageProperties))
                        .addComponent(plotPoleZeroPanel)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(filterCircuitPanel)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(stagePanel)
                                .addComponent(stageconfigpanel))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(componentspanel)
                                .addComponent(stageProperties))
                        .addComponent(plotPoleZeroPanel)
        );
        //layout.linkSize(SwingConstants.VERTICAL, filterCircuitPanel, stagePanel);
	}
}
