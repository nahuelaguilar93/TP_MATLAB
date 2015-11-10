package secondstage;

import Data.Singleton;
import tclib.GenericUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 8/11/2015.
 */
public class StagePanel extends JPanel {
    private PlotStagePanel plotStagePanel;


    public StagePanel() {
        Singleton_S2 s = Singleton_S2.getInstance();

        // Lo de Nahuel
        this.setBorder(BorderFactory.createTitledBorder("Stages Visualizer"));
        JButton b = new JButton("Booom!");
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(GenericUtils.dynamicRangeLoss(Singleton.getInstance().getUserData().getStageList(),100,1000));
            }
        });
        /////////////////////////////////////////////////////////////////////////

        plotStagePanel = s.getPlotStagePanel();

        this.add(plotStagePanel);
    }

}
