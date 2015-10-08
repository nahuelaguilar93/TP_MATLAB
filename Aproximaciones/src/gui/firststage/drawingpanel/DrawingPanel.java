package gui.firststage.drawingpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class DrawingPanel extends JPanel {

    private static PlotPlot plotPanel = null;
    private static CheckBoxChoosePlot choosePlotPanel = null;

    public DrawingPanel() {
        if (choosePlotPanel == null) {
            choosePlotPanel = new CheckBoxChoosePlot();
        }
        if (plotPanel == null) {
            plotPanel = new PlotPlot();
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.setMaximumSize(new Dimension(724, 768));
//        this.setMinimumSize(new Dimension(724, 768));
//        this.setPreferredSize(new Dimension(724, 768));

        this.add(plotPanel);
        this.add(choosePlotPanel);
    }

}
