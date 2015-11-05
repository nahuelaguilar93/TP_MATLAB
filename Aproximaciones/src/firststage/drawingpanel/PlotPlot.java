package firststage.drawingpanel;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;
import org.math.plot.Plot2DPanel;
import tclib.TransferFunction;
import tclib.GenericUtils;
import javax.swing.*;
import java.awt.*;
import flanagan.plot.PlotPoleZero;
import tclib.templates.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PlotPlot extends JPanel{
    private static Plot2DPanel plot = new Plot2DPanel();

    public PlotPlot() {
        double[] num = {0, 1};
        double[] den = {1, 0.25, 1};
        TransferFunction lowpass = new TransferFunction(num, den);

        double[] freq = GenericUtils.logspace(0.1, 100, 500);
        double[] modulo = lowpass.getModuleDB(freq);

        // create your PlotPanel (you can use it as a JPanel)

        plot.setAxisScales( "LOG" , "LIN" );
        plot.addLinePlot("my plot", freq, modulo);
        //plot.setSize(600,500);

        PlotPoleZero plotPoleZero = new PlotPoleZero(num, den);

        //Hardcoded size, it would be nice to change this.
        //this.setMinimumSize(new Dimension(640,400));
        //this.setPreferredSize(new Dimension(640,400));
        //plot.setMaximumSize(new Dimension(600,500));
        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plot, c);
        //this.add(plotPoleZero);
    }

    //Aún no probé esto... Hay que agregarlo al boton.
    public void AddPlot() {
        Singleton s = Singleton.getInstance();
        UserData userData = s.getUserData();

        double wp = 5000;
        double wa = 1000;
        TransferFunction TF = new Approximation(1, userData.CurrentTemplate).getTF();
        //TODO: En estos ifs saco info de los Ap y todo eso para saber la escala en la cual plotear
/*
        if (userData.CurrentTemplate instanceof LowpassTemplate) {

        }
        else if (userData.CurrentTemplate instanceof HighpassTemplate) {

        }
        else if (userData.CurrentTemplate instanceof BandpassTemplate) {

        }
        else if (userData.CurrentTemplate instanceof BandrejectTemplate) {

        }*/

        double[] freq = GenericUtils.logspace(Math.min(wp, wa)*(1-0.9), Math.max(wp,wa)*1.9, 500);
        double[] modulo = TF.getModuleDB(freq);

        plot.addLinePlot("my plot1", freq, modulo);

        this.add(plot);
    }

}