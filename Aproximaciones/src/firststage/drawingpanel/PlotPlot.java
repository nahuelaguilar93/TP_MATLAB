package firststage.drawingpanel;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PlotPlot extends JPanel{
    //private static Plot2DPanel plot = new Plot2DPanel();
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private SuperTemplate currentTemplate = userData.getCurrentTemplate();

    public PlotPlot() {
        JPanel plot = createPlotPanel();

        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plot, c);
    }

    private JPanel createPlotPanel() {

        XYDataset dataset = createDatasetTemplate();
        JFreeChart chart = ChartFactory.createXYLineChart("Prueba de JFreeChart", "Frequency", "Atenuation", dataset);

        //Setup Color Background and grid
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();
        //TODO: cambiar para cuando no es pasabajos
        double wa = ((LowpassTemplate) currentTemplate).getWa();
        double wp = ((LowpassTemplate) currentTemplate).getWp();

        //Set Logarithmic axis
        LogAxis xAxis = new LogAxis("Frequency");
        xAxis.setRange(wp*0.1, wa*10);
        ValueAxis yAxis = plot.getDomainAxis();
        yAxis.setRange(0, Aa+10);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        return new ChartPanel(chart);
    }

    private XYDataset createDatasetTemplate() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();
        XYSeries series1 = new XYSeries("First");
        XYSeries series2 = new XYSeries("Second");
        if (currentTemplate instanceof LowpassTemplate) {
            double wa = ((LowpassTemplate) currentTemplate).getWa();
            double wp = ((LowpassTemplate) currentTemplate).getWp();
            series1.add(wp*0.1, Ap);
            series1.add(wp, Ap);
            series1.add(wp, Aa+10);
            series2.add(wa, 0);
            series2.add(wa, Aa);
            series2.add(wa*10, Aa);

            dataset = AddPlots(dataset, Math.min(wa, wp), Math.max(wa, wp));
        } else if (currentTemplate instanceof HighpassTemplate) {
            double wa = ((HighpassTemplate) currentTemplate).getWa();
            double wp = ((HighpassTemplate) currentTemplate).getWp();
            series1.add(wa*0.1, Aa);
            series1.add(wa, Aa);
            series1.add(wa, 0);
            series2.add(wp, Ap+10);
            series2.add(wp, Ap);
            series2.add(wp*10, Ap);

            dataset = AddPlots(dataset, Math.min(wa, wp), Math.max(wa, wp));
        } else if (currentTemplate instanceof BandpassTemplate) {
            double wpp = ((BandpassTemplate) currentTemplate).getWpp();
            double wpm = ((BandpassTemplate) currentTemplate).getWpm();
            double wap = ((BandpassTemplate) currentTemplate).getWap();
            double wam = ((BandpassTemplate) currentTemplate).getWam();

            dataset = AddPlots(dataset, Math.min(wam, wpm), Math.max(wap, wpp));
            //TODO: terminar esto
        } else if (currentTemplate instanceof BandrejectTemplate) {
            double wpp = ((BandrejectTemplate) currentTemplate).getWpp();
            double wpm = ((BandrejectTemplate) currentTemplate).getWpm();
            double wap = ((BandrejectTemplate) currentTemplate).getWap();
            double wam = ((BandrejectTemplate) currentTemplate).getWam();

            dataset = AddPlots(dataset, Math.min(wam, wpm), Math.max(wap, wpp));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public XYSeriesCollection AddPlots(XYSeriesCollection dataset, double wmin, double wmax) {
        double[] freq = GenericUtils.logspace(wmin * (0.1), wmax * 10, 500);
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] modulo = TF.getModuleDB(freq);
            dataset = addDatasetAprox(freq, modulo, currentAprox.getDetails(), dataset);
        }
        return dataset;
    }
    /*
    *   Función sin probar aún. Le pasas los ejes de la frecuencia y modulo y el nombre del string y te los agrega al plot
    */
    private XYSeriesCollection addDatasetAprox(double[] freq, double[] modulo, String seriesName, XYSeriesCollection dataset) {
        XYSeries series = new XYSeries(seriesName);

        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], modulo[i]);
        }

        dataset.addSeries(series);
        return dataset;
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