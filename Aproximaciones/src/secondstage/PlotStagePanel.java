package secondstage;

import Data.Singleton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
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
        this.setBorder(BorderFactory.createTitledBorder("Stages Visualizer"));
        plotPanel = createPanel();

        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(plotPanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(plotPanel)
        );
    }

    private JPanel createPanel() {
        Singleton s = Singleton.getInstance();
        SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
        XYSeriesCollection dataset = new XYSeriesCollection();
        JFreeChart chart = ChartFactory.createXYLineChart("", "Frequency [rad/seg]", "Atenuation [dB]", dataset);

        //Setup Color Background and grid
        plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);

        //Used to set default Axis
        double Aa = currentTemplate.getAa();
        double Ap = currentTemplate.getAp();

        double wmin = currentTemplate.getWmin();
        double wmax = currentTemplate.getWmax();
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

    public void updatePlot() { plot.setDataset(0, null); }
    public void updatePlot(List<TransferFunction> myTFList, int Index) {
        plot.setDataset(0, null);
        if (!myTFList.isEmpty()) {
            Singleton s = Singleton.getInstance();
            SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
            XYSeriesCollection dataset = new XYSeriesCollection();
            if ( Index == -1) {
                dataset = createDataset(myTFList);
            }
            else {
                dataset = createDataset(myTFList, Index, dataset);
            }
            plot.setDataset(0, dataset);

            //Used to set default Axis
            double Aa = currentTemplate.getAa();
            double Ap = currentTemplate.getAp();

            double wmin = currentTemplate.getWmin();
            double wmax = currentTemplate.getWmax();
            //Set Logarithmic axis
            LogarithmicAxis xAxis = new LogarithmicAxis("Frequency");
            xAxis.setRange(wmin * 0.1, wmax * 10);
            //ValueAxis yAxis = plot.getRangeAxis();
            //yAxis.setRange(-currentTemplate.getG(), 2*(Aa-currentTemplate.getG()));

            //Set 'y' default Axis
            plot.setDomainAxis(xAxis);
            //plot.setRangeAxis(yAxis);
        }
    }

    private XYSeriesCollection createDataset(List < TransferFunction > myTFList) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int i = 0; i < myTFList.size(); i++) {
            dataset = createDataset(myTFList, i, dataset);
        }
        return dataset;
    }
    private XYSeriesCollection createDataset(List < TransferFunction > myTFList, int index, XYSeriesCollection dataset) {
        Singleton s = Singleton.getInstance();
        SuperTemplate currentTemplate = s.getUserData().getCurrentTemplate();
        double wmin = currentTemplate.getWmin();
        double wmax = currentTemplate.getWmax();

        double[] freq = GenericUtils.logspace(wmin*0.1, wmax*10, 10000);
        double[] module = myTFList.get(index).getModuleDB(freq);

        XYSeries series = new XYSeries("Stage" + index);
        for (int i = 0; i < freq.length; i++) {
            series.add(freq[i], -module[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }
}
