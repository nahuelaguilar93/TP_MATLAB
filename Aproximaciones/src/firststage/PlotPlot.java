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
import org.jfree.util.ShapeUtilities;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by NEGU on 7/10/2015.
 */
class PlotPlot extends JPanel{
    private Singleton s = Singleton.getInstance();
    private UserData userData = s.getUserData();
    private Singleton_S1 s1 = Singleton_S1.getInstance();
    private SuperTemplate currentTemplate;
    private double wmin;
    private double wmax;
    XYPlot plotA;
    XYPlot plotPZ;
    XYPlot plotStepOrImpulse;
    CardLayout cardLayout = new CardLayout();
    Shape cross = ShapeUtilities.createDiagonalCross(3,(float)0.3);

    public PlotPlot() {
        JPanel plotPanel = createAttenuationPanel();
        JPanel poleZeroPanel = createPoleZeroPanel();
        JPanel stepPanel = createStepPanel();

        this.setLayout(cardLayout);

        this.setBorder(BorderFactory.createTitledBorder("Plot"));
        this.add(poleZeroPanel, "PoleZero");
        this.add(plotPanel, "Atenuation");
        this.add(stepPanel, "StepOrImpulse");
        cardLayout.show(this, "Atenuation");
    }

    public void recreateTemplate() {
        plotA.setDataset(0, createDatasetTemplate());
        updateAttenuationPlot();

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

    public void updatePlot() {
        if (s1.getCheckBoxChoosePlot().getPolosCeros().isSelected()) {
            updatePoleZeroPlot();
        }
        else if (s1.getCheckBoxChoosePlot().getFase().isSelected()) {
            updatePhase();
        }
        else if (s1.getCheckBoxChoosePlot().getImpulse().isSelected()) {
            updateImpulse();
        }
        else if (s1.getCheckBoxChoosePlot().getStep().isSelected()) {
            updateStep();
        }
        else if (s1.getCheckBoxChoosePlot().getNormalizedTemplate().isSelected()) {
            updateNormalizedTemplate();
        }
        else {
            updateAttenuationPlot();
            cardLayout.show(this, "Atenuation");
        }
    }

    public void updateStep() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] temp = GenericUtils.linspace(0, 100 / wmin, 10000); //TODO: ese 100/wmin es mágico, habría que poner un tiempo significativo
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] step = TF.getStepResponse(temp);
            dataset = addStepOrImpulseSeriesToDataset(temp, step, currentAprox.getDetails(), dataset);
        }
        plotStepOrImpulse.setDataset(0, dataset);
        plotStepOrImpulse.setRenderer(0, new StandardXYItemRenderer());

        plotStepOrImpulse.setDataset(0, dataset);
        ValueAxis xAxis = plotStepOrImpulse.getDomainAxis();
        xAxis.setAutoRange(true);
        ValueAxis yAxis = plotStepOrImpulse.getRangeAxis();
        yAxis.setAutoRange(true);

        plotStepOrImpulse.setDomainAxis(xAxis);
        plotStepOrImpulse.setRangeAxis(yAxis);

        cardLayout.show(this, "StepOrImpulse");
    }
    public void updateImpulse(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] temp = GenericUtils.linspace(0, 100/wmin, 10000); //TODO: ese 100/wmin es mágico, habría que poner un tiempo significativo
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] step = TF.getImpulseResponse(temp);
            dataset = addStepOrImpulseSeriesToDataset(temp, step, currentAprox.getDetails(), dataset);
        }
        plotStepOrImpulse.setDataset(0, dataset);
        plotStepOrImpulse.setRenderer(0, new StandardXYItemRenderer());

        plotStepOrImpulse.setDataset(0, dataset);
        ValueAxis xAxis = plotStepOrImpulse.getDomainAxis();
        xAxis.setAutoRange(true);
        ValueAxis yAxis = plotStepOrImpulse.getRangeAxis();
        yAxis.setAutoRange(true);

        plotStepOrImpulse.setDomainAxis(xAxis);
        plotStepOrImpulse.setRangeAxis(yAxis);

        cardLayout.show(this, "StepOrImpulse");
    }
    public void updateAttenuationPlot() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] freq = GenericUtils.logspace(wmin * (0.1), wmax * 10, 10000);
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] modulo = TF.getModuleDB(freq);
            dataset = addAttenuationSeriesToDataset(freq, modulo, currentAprox.getDetails(), dataset);
        }
        plotA.setDataset(1, dataset);
        plotA.setRenderer(1, new StandardXYItemRenderer());
        //cardLayout.show(this, "Atenuation");
    }
    public void updatePhase() {
        plotA.setDataset(0, null);
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] freq = GenericUtils.logspace(wmin * (0.1), wmax * 10, 10000);
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getTF();
            double[] phase = TF.getPhase(freq);
            dataset = addPhaseSeriesToDataset(freq, phase, currentAprox.getDetails(), dataset);
        }
        plotA.setDataset(1, dataset);
        plotA.setRenderer(1, new StandardXYItemRenderer());

        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(wmin * 0.1, wmax * 10);
        ValueAxis yAxis = plotA.getRangeAxis();
        yAxis.setRange(-190, 190);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plotA.setDomainAxis(xAxis);
        plotA.setRangeAxis(yAxis);

        cardLayout.show(this, "Atenuation");
    }
    public void updatePoleZeroPlot() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for ( Approximation x : userData.getApproximationList()) {
            dataset = addPoleZeroSeriesToDataset(x, dataset);
        }
        plotPZ.setDataset(0, dataset);

        ValueAxis xAxis = plotPZ.getDomainAxis();
        xAxis.setAutoRange(true);
        ValueAxis yAxis = plotPZ.getRangeAxis();
        yAxis.setAutoRange(true);

        plotPZ.setDomainAxis(xAxis);
        plotPZ.setRangeAxis(yAxis);

        for (int i = 0; i < plotPZ.getSeriesCount(); i++) {
            if ( i % 2 == 1 ) {
                //plotPZ.getRenderer().setSeriesPaint(i, plotPZ.getRenderer().getSeriesPaint(i - 1));
                plotPZ.getRenderer().setSeriesShape(i, new Ellipse2D.Double(-3, -3, 6, 6));
            }
            else {
                plotPZ.getRenderer().setSeriesPaint(i+1, plotPZ.getRenderer().getSeriesPaint(i));
                plotPZ.getRenderer().setSeriesShape(i, cross);
            }
        }

        cardLayout.show(this, "PoleZero");
    }
    public void updateNormalizedTemplate() {
        //TODO: esta aun no anda
        plotA.setDataset(0, null);
        XYSeriesCollection dataset = new XYSeriesCollection();
        double[] freq = GenericUtils.logspace(0, userData.getCurrentTemplate().getWan()*10, 10000);
        java.util.List<Approximation> approximationList = userData.getApproximationList();
        Approximation currentAprox;
        for (int i = 0; i < approximationList.size(); i++) {
            currentAprox = approximationList.get(i);
            TransferFunction TF = currentAprox.getNTF();
            double[] modulo = TF.getModuleDB(freq);
            dataset = addAttenuationSeriesToDataset(freq, modulo, currentAprox.getDetails(), dataset);
        }
        plotA.setDataset(1, dataset);
        plotA.setRenderer(1, new StandardXYItemRenderer());

        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(0, userData.getCurrentTemplate().getWan()*10);
        plotA.setDomainAxis(xAxis);

        cardLayout.show(this, "Atenuation");
    }

    // This methods create the panel where the plot is... this runs only once (the cunstractor calls them)
    private JPanel createPoleZeroPanel() {
        XYSeriesCollection poleZeroDataset = createPoleZeroDataset();
        JFreeChart chart = ChartFactory.createScatterPlot("Poles/Zeros", "Real", "Imaginary", poleZeroDataset);

        //TODO: Falta agregar la configuración de los ejes y esas cosas
        plotPZ = chart.getXYPlot();

        return new ChartPanel(chart);
    }
    private JPanel createAttenuationPanel() {
        XYDataset datasetTemplate = createDatasetTemplate();
        JFreeChart chart = ChartFactory.createXYLineChart("", "Frequency [rad/seg]", "Atenuation [dB]", datasetTemplate);

        //Setup Color Background and grid
        plotA = chart.getXYPlot();
        plotA.setBackgroundPaint(Color.LIGHT_GRAY);
        plotA.setRangeGridlinePaint(Color.BLACK);
        plotA.setDomainGridlinePaint(Color.BLACK);

        updateAttenuationPlot();     //Agrego los filtros

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(wmin * 0.1, wmax * 10);
        ValueAxis yAxis = plotA.getRangeAxis();
        yAxis.setRange(0, 2*Aa);   //TODO: 0 debe ser cambiado por ganancia

        //Set 'y' default Axis
        plotA.setDomainAxis(xAxis);
        plotA.setRangeAxis(yAxis);

        return new ChartPanel(chart);
    }
    private JPanel createStepPanel() {
        XYSeriesCollection stepDataset = createStepDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("Step/Impulse Response", "Time", "Amplitude", stepDataset);

        plotStepOrImpulse = chart.getXYPlot();

        return new ChartPanel(chart);
    }

    // This methods creates (and updates) the right dataset with the userData information
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
            series1.add(wp, 2*Aa);
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
            series2.add(wp, 2*Aa);
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
            series2.add(wpm, 2*Aa);
            series2.add(wpm, Ap);
            series2.add(wpp, Ap);
            series2.add(wpp, 2*Aa);
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
            series1.add(wpm, 2*Aa);
            series2.add(wam, 0);
            series2.add(wam, Aa);
            series2.add(wap, Aa);
            series2.add(wap, 0);
            series3.add(wpp, 2*Aa);
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
    private XYSeriesCollection createPoleZeroDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for ( Approximation x : userData.getApproximationList()) {
            dataset = addPoleZeroSeriesToDataset(x, dataset);
        }
        return dataset;
    }
    private XYSeriesCollection createStepDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        return dataset;
    }

    private XYSeriesCollection addAttenuationSeriesToDataset(double[] freq, double[] modulo, String seriesName, XYSeriesCollection dataset) {
        //TODO: ver que pasa cuando seriesName ya está.
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], -modulo[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }
    private XYSeriesCollection addPoleZeroSeriesToDataset(Approximation x, XYSeriesCollection dataset) {
        XYSeries zerosSeries = new XYSeries(x.getDetails() + " Zeros");
        Complex[] zerosArray = x.getTF().getZeros();
        for (int i = 0; i < zerosArray.length; i++) {
            zerosSeries.add(zerosArray[i].getReal(), zerosArray[i].getImaginary());
        }
        XYSeries polesSeries = new XYSeries(x.getDetails() + " Poles");
        Complex[] polesArray = x.getTF().getPoles();
        for (int i = 0; i < polesArray.length; i++) {
            polesSeries.add(polesArray[i].getReal(), polesArray[i].getImaginary());
        }
        dataset.addSeries(polesSeries);
        dataset.addSeries(zerosSeries);
        return dataset;
    }
    private XYSeriesCollection addStepOrImpulseSeriesToDataset(double[] temp, double[] step, String seriesName, XYSeriesCollection dataset) {
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < temp.length; i++) {
            series.add(temp[i], step[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }
    private XYSeriesCollection addPhaseSeriesToDataset(double[] freq, double[] phase, String seriesName, XYSeriesCollection dataset) {
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], phase[i]*180/Math.PI);
        }
        dataset.addSeries(series);
        return dataset;
    }
}