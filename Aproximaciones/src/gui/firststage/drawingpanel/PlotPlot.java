package gui.firststage.drawingpanel;

import org.math.plot.Plot2DPanel;
import tclib.TransferFunction;
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
        TransferFunction lowpass = new TransferFunction(num, den);

        double[] freq = GenericUtils.logspace(0.1, 10, 500);
        double[] modulo = lowpass.getModuleDB(freq);

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisScales("LOG", "LIN");
        plot.addLinePlot("my plot", freq, modulo);
        //plot.setSize(600,500);

        //Hardcoded size, it would be nice to change this.
        //this.setMinimumSize(new Dimension(640,400));
        //this.setPreferredSize(new Dimension(640,400));
        //plot.setMaximumSize(new Dimension(600,500));
        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plot);
    }
}