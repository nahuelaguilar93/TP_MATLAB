package gui.FirstStage;

import gui.FirstStage.ChoosePlot.ChoosePlotFactory;
import gui.FirstStage.Plot.PlotFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class Panel2 extends JPanel {

    private static JPanel plotPanel = null;
    private static JPanel choosePlotPanel = null;

    public static JPanel getPlotPanel() {
        if (plotPanel == null) {
            plotPanel = new JPanel();
            plotPanel.setBorder(BorderFactory.createTitledBorder("Plot"));
            plotPanel.add(PlotFactory.getPlot());
        }
        return plotPanel;
    }

    public static JPanel getChoosePlotPanel() {
        if (choosePlotPanel == null) {
            choosePlotPanel = new JPanel();
            //Size of chosePlotPanel
            choosePlotPanel.setPreferredSize(new Dimension(724, 100));
            choosePlotPanel.setMaximumSize(new Dimension(1620, 100));
            choosePlotPanel.setMinimumSize(new Dimension(500,100));
            //choosePlotPanel.setBorder(BorderFactory.createTitledBorder("Select Plot"));
            choosePlotPanel.add(ChoosePlotFactory.getCheckBoxChoosePlot());
        }
        return choosePlotPanel;
    }
}
