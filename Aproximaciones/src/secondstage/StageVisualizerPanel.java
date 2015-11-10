package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class StageVisualizerPanel extends JPanel {
    private PlotStagePanel plotStagePanel;

    StageVisualizerPanel() {
        Singleton_S2 s = Singleton_S2.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        plotStagePanel = s.getPlotStagePanel();

        this.add(plotStagePanel);
    }
}
