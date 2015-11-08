package secondstage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 8/11/2015.
 */
public class PlotPoleZeroPanel extends JPanel {
    public PlotPoleZeroPanel() {
        this.setMaximumSize(new Dimension(300, 300));
        this.setMinimumSize(new Dimension(300, 300));
        this.setPreferredSize(new Dimension(300, 300));

        this.setBorder(BorderFactory.createTitledBorder("PoleZeroPlot"));

    }
}
