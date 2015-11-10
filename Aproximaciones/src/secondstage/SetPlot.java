package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class SetPlot extends JPanel{
    private StagePlotModePanel stagePlotModePanel;
    private FilterMode filterMode;

    SetPlot() {
        Singleton_S2 s = Singleton_S2.getInstance();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stagePlotModePanel = s.getstagePlotModePanel();
        filterMode = s.getFilterMode();

        this.add(stagePlotModePanel);
        this.add(filterMode);
    }
}
