package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class SetPlot extends JPanel{
    private StagePlotModePanel stagePlotModePanel;
    private FilterMode filterMode;
    private StageProperties stageProperties;

    SetPlot() {
        Singleton_S2 s = Singleton_S2.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        stagePlotModePanel = s.getstagePlotModePanel();
        filterMode = s.getFilterMode();
        stageProperties = s.getStageProperties();

        this.add(stagePlotModePanel);
        this.add(filterMode);
        this.add(stageProperties);
    }
}
