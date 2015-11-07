package firststage;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class DrawingPanel extends JPanel {

    public DrawingPanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        PlotPlot plotPanel = s.getPlotPlot();
        CheckBoxChoosePlot choosePlotPanel = s.getCheckBoxChoosePlot();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(plotPanel);
        this.add(choosePlotPanel);
    }
}
