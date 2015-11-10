package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class StageVisualizerPanel extends JPanel {
    private PlotStagePanel plotStagePanel;
    private StagePlotModePanel stagePlotModePanel;

    StageVisualizerPanel() {
        Singleton_S2 s = Singleton_S2.getInstance();

        plotStagePanel = s.getPlotStagePanel();
        stagePlotModePanel = s.getstagePlotModePanel();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(plotStagePanel);
        this.add(stagePlotModePanel);
    }
}
