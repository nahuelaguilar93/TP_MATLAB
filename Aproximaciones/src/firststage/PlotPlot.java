package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
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
    //private static Plot2DPanel plot = new Plot2DPanel();
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private SuperTemplate currentTemplate = userData.getCurrentTemplate();
    private int datasetIndex = 0;
    private double wmin;
    private double wmax;
    XYPlot plot;

    public PlotPlot() {
        JPanel plotPanel = createPlotPanel();

        /*final JButton addDataSetButton = new JButton("Add Dataset");
        addDataSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlots();
            }
        });*/

        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(plotPanel, c);
        //this.add(addDataSetButton);
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

            wmin = wp;
            wmax = wa;
        } else if (currentTemplate instanceof HighpassTemplate) {
            double wa = ((HighpassTemplate) currentTemplate).getWa();
            double wp = ((HighpassTemplate) currentTemplate).getWp();
            series1.add(wa*0.1, Aa);
            series1.add(wa, Aa);
            series1.add(wa, 0);
            series2.add(wp, Ap+10);
            series2.add(wp, Ap);
            series2.add(wp*10, Ap);

            wmin = wa;
            wmax = wp;
        } else if (currentTemplate instanceof BandpassTemplate) {
            double wpp = ((BandpassTemplate) currentTemplate).getWpp();
            double wpm = ((BandpassTemplate) currentTemplate).getWpm();
            double wap = ((BandpassTemplate) currentTemplate).getWap();
            double wam = ((BandpassTemplate) currentTemplate).getWam();
            //TODO: terminar esto
        } else if (currentTemplate instanceof BandrejectTemplate) {
            double wpp = ((BandrejectTemplate) currentTemplate).getWpp();
            double wpm = ((BandrejectTemplate) currentTemplate).getWpm();
            double wap = ((BandrejectTemplate) currentTemplate).getWap();
            double wam = ((BandrejectTemplate) currentTemplate).getWam();
        }
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public void AddPlots() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] freq = GenericUtils.logspace(wmin * (0.1), wmax * 10, 500);
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
    /*
    *  Le pasas los ejes de la frecuencia y modulo y el nombre del string y te los agrega al plot
    */
    private XYSeriesCollection addDatasetAprox(double[] freq, double[] modulo, String seriesName, XYSeriesCollection dataset) {
        //TODO: ver que pasa cuando seriesName ya está.
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], -modulo[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }

    private XYSeriesCollection createRandomDataset(final String name) {
        final XYSeries series = new XYSeries(name);
        double value = -100.0;
        for (int i = 0; i < 5000; i++) {
            value = value * (1.0 + Math.random() / 100);
            series.add(i, value);
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
    }

    private void AddRandPlot () {
        datasetIndex++;
        plot.setDataset(datasetIndex, createRandomDataset("S" + datasetIndex));
        plot.setRenderer(datasetIndex, new StandardXYItemRenderer());
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