package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class StageVisualizerPanel extends JPanel {
    private PlotStagePanel plotStagePanel;
    private SetPlot setPlot;


    StageVisualizerPanel() {
        Singleton_S2 s = Singleton_S2.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        plotStagePanel = s.getPlotStagePanel();
        setPlot = s.getSetPlot();

        this.add(plotStagePanel);
        this.add(setPlot);
    }
}
