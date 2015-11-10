package secondstage;

import Data.Singleton;
import Data.UserData;
import org.apache.commons.math3.analysis.function.Sin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tclib.GenericUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 10/11/2015.
 */
public class PlotStagePanel extends JPanel {
    XYPlot plot;
    JPanel plotPanel;

    PlotStagePanel() {
        this.setBorder(BorderFactory.createTitledBorder("Stages Plot Attenuation"));
        plotPanel = new JPanel();
        this.add(plotPanel, "Atenuation");
    }

    public void updatePlot(List<TransferFunction> myTFList, int Index) {
        if (!myTFList.isEmpty()) {
            plotPanel = createPanel(myTFList, Index);
        }
    }

    private JPanel createPanel(List<TransferFunction> myTFList, int Index) {
        Singleton s = Singleton.getInstance();
        SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
        XYSeriesCollection dataset = new XYSeriesCollection();
        if ( Index == -1) {
            dataset = createDataset(myTFList);
        }
        else {
            dataset = createDataset(myTFList, Index, dataset);
        }
        JFreeChart chart = ChartFactory.createXYLineChart("", "Frequency [rad/seg]", "Atenuation [dB]", dataset);

        //Setup Color Background and grid
        plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        double wmin = getWmin();
        double wmax = getWmax();
        //Set Logarithmic axis
        LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
        xAxis.setRange(wmin * 0.1, wmax * 10);
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(-currentTemplate.getG(), 2*(Aa-currentTemplate.getG()));

        //Set 'y' default Axis
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);

        return new ChartPanel(chart);
    }

    private XYSeriesCollection createDataset(List < TransferFunction > myTFList) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int i = 0; i < myTFList.size(); i++) {
            dataset = createDataset(myTFList, i, dataset);
        }
        return dataset;
    }
    private XYSeriesCollection createDataset(List < TransferFunction > myTFList, int index, XYSeriesCollection dataset) {
        double wmin = getWmin();
        double wmax = getWmax();

        double[] freq = GenericUtils.logspace(wmin*0.1, wmax*10, 10000);
        double[] module = myTFList.get(index).getModuleDB(freq);

        XYSeries series = new XYSeries("Stage" + index);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], -module[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }

    double getWmax() {
        Singleton s = Singleton.getInstance();
        SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
        if (currentTemplate instanceof LowpassTemplate) {
            double wa = ((LowpassTemplate) currentTemplate).getWa();
            return wa;
        }
        else if (currentTemplate instanceof HighpassTemplate) {
            double wp = ((HighpassTemplate) currentTemplate).getWp();
            return wp;
        }
        else if (currentTemplate instanceof BandpassTemplate) {
            double wap = ((BandpassTemplate) currentTemplate).getWap();
            return wap;
        }
        else {
            double wpp = ((BandrejectTemplate) currentTemplate).getWpp();
            return wpp;
        }
    }
    double getWmin() {
        Singleton s = Singleton.getInstance();
        SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
        if (currentTemplate instanceof LowpassTemplate) {
            double wp = ((LowpassTemplate) currentTemplate).getWp();
            return wp;
        }
        else if (currentTemplate instanceof HighpassTemplate) {
            double wa = ((HighpassTemplate) currentTemplate).getWa();
            return wa;
        }
        else if (currentTemplate instanceof BandpassTemplate) {
            double wam = ((BandpassTemplate) currentTemplate).getWam();
            return wam;
        }
        else {
            double wpm = ((BandrejectTemplate) currentTemplate).getWpm();
            return wpm;
        }
    }
}
