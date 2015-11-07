package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by NEGU on 7/10/2015.
 */
class PlotPlot extends JPanel{
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private SuperTemplate currentTemplate;
    private double wmin;
    private double wmax;
    XYPlot plot;

    public PlotPlot() {
        JPanel plotPanel = createPlotPanel();

        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plotPanel, c);
    }

    private JPanel createPlotPanel() {
        XYDataset datasetTemplate = createDatasetTemplate();
        JFreeChart chart = ChartFactory.createXYLineChart("Prueba de JFreeChart", "Frequency", "Atenuation", datasetTemplate);

        //Setup Color Background and grid
        plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        AddPlots();     //Agrego los filtros

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(wmin*0.1, wmax*10);
        ValueAxis yAxis = plot.getDomainAxis();
        yAxis.setRange(0, Aa+10);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        return new ChartPanel(chart);
    }

    private XYDataset createDatasetTemplate() {
        currentTemplate = userData.getCurrentTemplate();
        XYSeriesCollection dataset = new XYSeriesCollection();
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();
        XYSeries series1 = new XYSeries("First");
        XYSeries series2 = new XYSeries("Second");
        XYSeries series3 = new XYSeries("Third");
        if (currentTemplate instanceof LowpassTemplate) {
            double wa = ((LowpassTemplate) currentTemplate).getWa();
            double wp = ((LowpassTemplate) currentTemplate).getWp();
            series1.add(wp*0.1, Ap);
            series1.add(wp, Ap);
            series1.add(wp, Aa+10);
            series2.add(wa, 0);
            series2.add(wa, Aa);
            series2.add(wa*10, Aa);

            wmin = wp;
            wmax = wa;
        } else if (currentTemplate instanceof HighpassTemplate) {
            double wa = ((HighpassTemplate) currentTemplate).getWa();
            double wp = ((HighpassTemplate) currentTemplate).getWp();
            series1.add(wa*0.1, Aa);
            series1.add(wa, Aa);
            series1.add(wa, 0);
            series2.add(wp, Aa+10);
            series2.add(wp, Ap);
            series2.add(wp*10, Ap);

            wmin = wa;
            wmax = wp;
        } else if (currentTemplate instanceof BandpassTemplate) {
            double wpp = ((BandpassTemplate) currentTemplate).getWpp();
            double wpm = ((BandpassTemplate) currentTemplate).getWpm();
            double wap = ((BandpassTemplate) currentTemplate).getWap();
            double wam = ((BandpassTemplate) currentTemplate).getWam();
            series1.add(wam*0.1, Aa);
            series1.add(wam, Aa);
            series1.add(wam, 0);
            series2.add(wpm, Aa+10);
            series2.add(wpm, Ap);
            series2.add(wpp, Ap);
            series2.add(wpp, Aa+10);
            series3.add(wap, 0);
            series3.add(wap, Aa);
            series3.add(wap*10, Aa);

            wmin = wam;
            wmax = wap;

            dataset.addSeries(series3);
        } else if (currentTemplate instanceof BandrejectTemplate) {
            double wpp = ((BandrejectTemplate) currentTemplate).getWpp();
            double wpm = ((BandrejectTemplate) currentTemplate).getWpm();
            double wap = ((BandrejectTemplate) currentTemplate).getWap();
            double wam = ((BandrejectTemplate) currentTemplate).getWam();

            series1.add(wpm*0.1, Ap);
            series1.add(wpm, Ap);
            series1.add(wpm, Aa+10);
            series2.add(wam, 0);
            series2.add(wam, Aa);
            series2.add(wap, Aa);
            series2.add(wap, 0);
            series3.add(wpp, Aa+10);
            series3.add(wpp, Ap);
            series3.add(wpp*10, Ap);

            wmin = wpm;
            wmax = wpp;

            dataset.addSeries(series3);
        }
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public void AddPlots() {
        plot.setDataset(1, null);   //Cabeceada pero funciona para borrar
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] freq = GenericUtils.logspace(wmin * (0.1), wmax * 10, 10000);
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] modulo = TF.getModuleDB(freq);
            dataset = addDatasetAprox(freq, modulo, currentAprox.getDetails(), dataset);
        }
        plot.setDataset(1, dataset);
        plot.setRenderer(1, new StandardXYItemRenderer());
    }

    public void recreateTemplate() {
        plot.setDataset(0, createDatasetTemplate());
        plot.setDataset(1, null);

       //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        //Set Logarithmic axis
        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setRange(wmin*0.1, wmax*10);
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0, Aa+10);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
    }

    private XYSeriesCollection addDatasetAprox(double[] freq, double[] modulo, String seriesName, XYSeriesCollection dataset) {
        //TODO: ver que pasa cuando seriesName ya está.
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], -modulo[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }

    //TODO: hacer si hay tiempo
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