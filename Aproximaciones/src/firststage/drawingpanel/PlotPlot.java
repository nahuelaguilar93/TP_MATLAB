package firststage.drawingpanel;

import Data.Singleton;
import Data.UserData;
import flanagan.plot.PlotPoleZero;
import javafx.scene.chart.NumberAxis;
import mathematics.Approximation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.LogFormat;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.math.plot.Plot2DPanel;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.LowpassTemplate;
import tclib.templates.SuperTemplate;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PlotPlot extends JPanel{
    //private static Plot2DPanel plot = new Plot2DPanel();
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private SuperTemplate currentTemplate = userData.getCurrentTemplate();


    public PlotPlot() {
        /*double[] num = {0, 1};
        double[] den = {1, 0.25, 1};
        TransferFunction lowpass = new TransferFunction(num, den);

        double[] freq = GenericUtils.logspace(0.1, 100, 500);
        double[] modulo = lowpass.getModuleDB(freq);*/

        // create your PlotPanel (you can use it as a JPanel)
        //plot.setAxisScales( "LOG" , "LIN" );
        //plot.addLinePlot("the template", freq, modulo);
        JPanel plot = createChartPanel();

        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plot, c);
    }

    //Aún no probé esto... Hay que agregarlo al boton.
    public void AddPlot() {
        double wp = 5000;
        double wa = 1000;
        TransferFunction TF = new Approximation(1, userData.getCurrentTemplate()).getTF();
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

        //plot.addLinePlot("my plot1", freq, modulo);

        //this.add(plot);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        //TODO: hay que crear una para cada userData y listo ^^
        //if (currentTemplate instanceof LowpassTemplate) {
            double Aa = currentTemplate.getAa();
            double Ap = currentTemplate.getAp();
            double wa = ((LowpassTemplate) currentTemplate).getWa();
            double wp = ((LowpassTemplate) currentTemplate).getWp();

            XYSeries series1 = new XYSeries("First");
            series1.add(0, Ap);
            series1.add(wp, Ap);
            series1.add(wp, Aa+10);

            XYSeries series2 = new XYSeries("Second");
            series2.add(wa, 0);
            series2.add(wa, Aa);
            series2.add(wa*1.9, Aa);

            dataset.addSeries(series1);
            dataset.addSeries(series2);

            return dataset;
        //}
    }

    private JPanel createChartPanel() {
        XYDataset dataset = createDataset();

        LogAxis xAxis = new LogAxis("Frequency");
        xAxis.setBase(10);
        LogFormat format = new LogFormat(xAxis.getBase(), "", "", true);
        xAxis.setNumberFormatOverride(format);

        JFreeChart chart = ChartFactory.createXYLineChart("Prueba de JFreeChart", "Frequency", "Atenuation", dataset);

        //Setup Color Background and grid
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        ValueAxis rangeAxis = plot.getRangeAxis();

        return new ChartPanel(chart);
    }

    public void saveImage () {
        File imageFile = new File("XYLineChar.png");
        int width = 640;
        int height = 480;

        /*try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }*/
    }

}