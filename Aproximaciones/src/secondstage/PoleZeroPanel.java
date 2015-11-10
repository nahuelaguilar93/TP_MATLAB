package secondstage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 8/11/2015.
 */
public class PoleZeroPanel extends JPanel {
    private PlotPoleZeroPanel plotPoleZeroPanel;
    private PoleZeroListsPanel poleZeroListsPanel;

    public PoleZeroPanel() {
        Singleton_S2 s = Singleton_S2.getInstance();

        plotPoleZeroPanel = s.getPlotPoleZeroPanel();
        poleZeroListsPanel = s.getPoleZeroListsPanel();

        this.setMaximumSize(new Dimension(500, 2080));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("PoleZeroStages"));

        this.add(plotPoleZeroPanel);
        this.add(poleZeroListsPanel);
    }

}
