package gui.FirstStage.Plot;

import org.math.plot.Plot2DPanel;
import tclib.Approximation;
import tclib.GenericUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PlotPlot extends JPanel{

    public PlotPlot() {
        double[] num = {0, 1};
        double[] den = {1, 0.25, 1};
        Approximation lowpass = new Approximation(num, den);

        double[] freq = GenericUtils.logspace(0.1, 10, 500);
        double[] modulo = lowpass.getModuleDB(freq);

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisScales("LOG", "LIN");
        plot.addLinePlot("my plot", freq, modulo);

        //Hardcoded size, it would be nice to change this.
        plot.setPreferredSize(new Dimension(600,500));
        plot.setMinimumSize(new Dimension(600,500));
        plot.setMaximumSize(new Dimension(600,500));

        this.add(plot);
    }

}
