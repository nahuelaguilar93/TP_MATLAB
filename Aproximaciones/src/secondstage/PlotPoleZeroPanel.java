package secondstage;

import Data.Singleton;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import mathematics.Stage;
import org.jfree.util.ShapeUtilities;

import java.awt.geom.Ellipse2D;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.List.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 8/11/2015.
 */
public class PlotPoleZeroPanel extends JPanel {
    Singleton s = Singleton.getInstance();
    XYPlot plotPZ;

    public PlotPoleZeroPanel() {
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        this.setPreferredSize(new Dimension(400, 400));

        XYSeriesCollection poleZeroDataset = createPoleZeroDataset();
        JFreeChart chart = ChartFactory.createScatterPlot("Poles/Zeros", "Real", "Imaginary", poleZeroDataset);

        //TODO: Falta agregar la configuración de los ejes y esas cosas
        plotPZ = chart.getXYPlot();
        JPanel chartPanel = new ChartPanel(chart);

        //Setup layout
        this.setBorder(BorderFactory.createTitledBorder("PoleZeroPlot"));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(chartPanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(chartPanel)
        );

        updatePoleZeroPlot();
        updatePoleZeroColour();
    }

    public void updatePoleZeroPlot() { plotPZ.setDataset(0, createPoleZeroDataset()); }
    public void updatePoleZeroColour() {
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plotPZ.getRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesPaint(1, Color.BLACK);

        Shape cross = ShapeUtilities.createDiagonalCross(3, (float) 0.3);
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

        if ( maxDoubleHigh() != 0 && maxDoubleWidth() != 0 ) {
            ValueAxis xAxis = plotPZ.getDomainAxis();
            xAxis.setRange(maxDoubleWidth()*1.1, -maxDoubleWidth()*0.1);
            ValueAxis yAxis = plotPZ.getRangeAxis();
            yAxis.setRange(-maxDoubleHigh()*1.1, maxDoubleHigh()*1.1);
            plotPZ.setDomainAxis(xAxis);
            plotPZ.setRenderer(renderer);
        }
    }

    private XYSeriesCollection createPoleZeroDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        String stringName;

        stringName = "Unmatched Poles";
        dataset = addPointsSeriesToDataset(s.getUserData().getUnmatchedPoles(), dataset, stringName);
        stringName = "Unmatched Zeros";
        dataset = addPointsSeriesToDataset(s.getUserData().getUnmatchedZeros(), dataset, stringName);
        stringName = "Stage ";
        List<Stage> myStageList = s.getUserData().getStageList();
        for (int i = 0; i < myStageList.size(); i++) {
            dataset = addPointsSeriesToDataset(Arrays.asList(myStageList.get(i).getPoles()), dataset, stringName + i +" Poles");
            dataset = addPointsSeriesToDataset(Arrays.asList(myStageList.get(i).getZeros()), dataset, stringName + i + " Zeros");
        }
        return dataset;
    }

    private XYSeriesCollection addPointsSeriesToDataset(List<Complex> complexList, XYSeriesCollection dataset, String stringName) {
        XYSeries series = new XYSeries(stringName);
        if (!complexList.isEmpty() ) {
            for (Complex x : complexList) {
                series.add(x.getReal(), x.getImaginary());
                series.add(x.getReal(), -x.getImaginary());
            }
        }
        dataset.addSeries(series);

        return dataset;
    }

    double maxDoubleHigh() {
        double max = 0;
        List<Complex> currentPolesList = s.getUserData().getUnmatchedPoles();
        for ( int i = 0; i < currentPolesList.size(); i++) {
            if ( currentPolesList.get(i).getImaginary() > max ) {
                max = currentPolesList.get(i).getImaginary();
            }
        }
        List<Complex> currentZeroList = s.getUserData().getUnmatchedPoles();
        for ( int i = 0; i < currentZeroList.size(); i++) {
            if ( currentZeroList.get(i).getImaginary() > max ) {
                max = currentZeroList.get(i).getImaginary();
            }
        }
        List<Stage> selectedPolesList = s.getUserData().getStageList();
        for ( Stage x : selectedPolesList) {
            for ( Complex c : x.getPoles()) {
                if ( c.getImaginary() > max) {
                    max = c.getImaginary();
                }
            }
        }
        return max;
    }
    double maxDoubleWidth() {
        double max = 0;
        List<Complex> currentPolesList = s.getUserData().getUnmatchedPoles();
        for ( int i = 0; i < currentPolesList.size(); i++) {
            if ( currentPolesList.get(i).getReal() < max ) {
                max = currentPolesList.get(i).getReal();
            }
        }
        List<Stage> selectedPolesList = s.getUserData().getStageList();
        for ( Stage x : selectedPolesList) {
            for ( Complex c : x.getPoles()) {
                if ( c.getReal() < max) {
                    max = c.getReal();
                }
            }
        }
        return max;
    }
}