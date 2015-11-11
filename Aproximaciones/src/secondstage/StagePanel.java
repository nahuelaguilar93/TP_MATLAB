package secondstage;

import Data.Singleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 8/11/2015.
 */
public class StagePanel extends JPanel {
    StageVisualizerPanel stageVisualizerPanel;
    SetPlot setPlot;


    public StagePanel() {
        Singleton_S2 s = Singleton_S2.getInstance();
        //this.setBorder(BorderFactory.createTitledBorder("Stages Visualizer"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stageVisualizerPanel = s.getStageVisualizerPanel();
        setPlot = s.getSetPlot();

        this.add(stageVisualizerPanel);
        this.add(setPlot);

        // Lo de Nahuel
        JButton b = new JButton("Minimize DRL");
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton.getInstance().getUserData().getStageDisposition().gainCorrection();
                Singleton.getInstance().getUserData().getStageDisposition().minimizeDRL();
                Singleton_S2.getInstance().getPlotStagePanel().updatePlot();
                //                System.out.println(GenericUtils.dynamicRangeLoss(Singleton.getInstance().getUserData().getStageList(),100,1000));
            }
        });
        /////////////////////////////////////////////////////////////////////////
    }
}
