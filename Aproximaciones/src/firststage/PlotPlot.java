package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class PlotPlot extends JPanel{
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private SuperTemplate currentTemplate;
    private double wmin;
    private double wmax;
    XYPlot plotA;
    XYPlot plotPZ;
    CardLayout cardLayout = new CardLayout();

    public PlotPlot() {
        JPanel plotPanel = createAttenuationPanel();
        JPanel poleZeroPanel = createPoleZeroPanel();

        this.setLayout(cardLayout);

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(poleZeroPanel, "PoleZero");
        this.add(plotPanel, "Atenuation");
        //cardLayout.show(this, "Atenuation");
    }

    public void recreateTemplate() {
        plotA.setDataset(0, createDatasetTemplate());
        plotA.setDataset(1, null);

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        //Set Logarithmic axis
        ValueAxis xAxis = plotA.getDomainAxis();
        xAxis.setRange(wmin * 0.1, wmax * 10);
        ValueAxis yAxis = plotA.getRangeAxis();
        yAxis.setRange(0, Aa+10);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plotA.setDomainAxis(xAxis);
        plotA.setRangeAxis(yAxis);
        cardLayout.show(this, "Atenuation");
    }

    private JPanel createPoleZeroPanel() {
        XYSeriesCollection poleZeroDataset = createPoleZeroPlot();
        /*XYSeriesCollection poleZeroDataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("prueba");
        series.add(1,1);
        series.add(0,1);
        series.add(1,0.5);
        poleZeroDataset.addSeries(series);*/
        JFreeChart chart = ChartFactory.createScatterPlot("Poles/Zeros", "Real", "Imaginary", poleZeroDataset);

        //TODO: Falta agregar la configuración de los ejes y esas cosas
        plotPZ = chart.getXYPlot();

        return new ChartPanel(chart);
    }
    private JPanel createAttenuationPanel() {
        XYDataset datasetTemplate = createDatasetTemplate();
        JFreeChart chart = ChartFactory.createXYLineChart("Prueba de JFreeChart", "Frequency", "Atenuation", datasetTemplate);

        //Setup Color Background and grid
        plotA = chart.getXYPlot();
        plotA.setBackgroundPaint(Color.LIGHT_GRAY);
        plotA.setRangeGridlinePaint(Color.BLACK);
        plotA.setDomainGridlinePaint(Color.BLACK);

        addPlots();     //Agrego los filtros

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(wmin * 0.1, wmax * 10);
        ValueAxis yAxis = plotA.getDomainAxis();
        yAxis.setRange(0, Aa+10);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plotA.setDomainAxis(xAxis);
        plotA.setRangeAxis(yAxis);

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
    private XYSeriesCollection createPoleZeroPlot() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for ( Approximation x : userData.getApproximationList()) {
            dataset = getPolesZeroDataset(x, dataset);
        }
        return dataset;
    }

    public void addPlots() {
        plotA.setDataset(1, null);   //Cabeceada pero funciona para borrar
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
        plotA.setDataset(1, dataset);
        plotA.setRenderer(1, new StandardXYItemRenderer());
        //cardLayout.show(this, "Atenuation");
    }
    public void poleZeroPlot() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeriesCollection poleZeroDataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("prueba");
        series.add(1,1);
        series.add(0,1);
        series.add(1,0.5);
        poleZeroDataset.addSeries(series);
        for ( Approximation x : userData.getApproximationList()) {
            dataset = getPolesZeroDataset(x, dataset);
        }
        plotPZ.setDataset(0, dataset);
        plotPZ.setDataset(0, poleZeroDataset);
        cardLayout.show(this, "PoleZero");
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
    private XYSeriesCollection getPolesZeroDataset(Approximation x, XYSeriesCollection dataset) {
        XYSeries zerosSeries = new XYSeries(x.getDetails() + "zeros");
        Complex[] zerosArray = x.getTF().getZeros();
        for (int i = 0; i < zerosArray.length; i++) {
            zerosSeries.add(zerosArray[i].getReal(), zerosArray[i].getImaginary());
            System.out.println(zerosArray[i].getReal());
            System.out.println(zerosArray[i].getImaginary());
        }
        XYSeries polesSeries = new XYSeries(x.getDetails() + "poles");
        Complex[] polesArray = x.getTF().getPoles();
        for (int i = 0; i < polesArray.length; i++) {
            polesSeries.add(polesArray[i].getReal(), polesArray[i].getImaginary());
            System.out.println(polesArray[i].getReal());
            System.out.println(polesArray[i].getImaginary());
        }
        polesSeries.add(1,1);
        polesSeries.add(1,0.5);
        polesSeries.add(1,0);
        dataset.addSeries(polesSeries);
        dataset.addSeries(zerosSeries);
        return dataset;
    }
}