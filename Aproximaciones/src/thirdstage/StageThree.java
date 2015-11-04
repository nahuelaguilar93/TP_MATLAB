package thirdstage;

import org.math.plot.Plot2DPanel;
import tclib.TransferFunction;
import tclib.GenericUtils;

import javax.swing.*;
import java.awt.*;

public class StageThree extends JPanel {

    public StageThree() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.RED);


        double[] num = {1};
        //double[] den = {1, 1};
        double[] den = {1, 0.05, 1};
        TransferFunction lowpass = new TransferFunction(num, den);

        double[] time = GenericUtils.linspace(0, 100, 1000);
        double[] impulse = lowpass.getImpulseResponse(time);

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisScales("LIN", "LIN");
        plot.addLinePlot("my plot", time, impulse);

        this.add(plot);


    }

}
