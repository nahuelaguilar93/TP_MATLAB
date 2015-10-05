package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import org.math.plot.*;
import tclib.Approximation;
import tclib.GenericUtils;


/**
 * Created by kdewald on 23/09/15.
 */
public class StageTwo extends JPanel {

    public StageTwo() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLUE);


        double[] num = {0,1};
        double[] den = {1, 0.25 ,1};
        Approximation lowpass = new Approximation(num,den);

        double[] freq = GenericUtils.logspace(0.1,10,500);
        double[] modulo = lowpass.getModuleDB(freq);
        double[] phase = lowpass.getPhase(freq);

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisScales("LOG", "LIN");
        plot.addLinePlot("my plot", freq, modulo);

        Plot2DPanel plot2 = new Plot2DPanel();
        plot2.setAxisScales("LOG", "LIN");
        plot2.addLinePlot("my plot", freq, phase);


        this.add(plot);
        this.add(plot2);
    }
}
