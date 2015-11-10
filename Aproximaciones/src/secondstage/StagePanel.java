package secondstage;

import Data.Singleton;
import tclib.GenericUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 8/11/2015.
 */
public class StagePanel extends JPanel {
    private PlotStagePanel plotStagePanel;
    private StagePlotModePanel stagePlotModePanel;

    public StagePanel() {
        Singleton_S2 s = Singleton_S2.getInstance();

        plotStagePanel = s.getPlotStagePanel();
        stagePlotModePanel = s.getstagePlotModePanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*this.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.gridheight = 4;*/

        this.add(plotStagePanel);

        //c.gridy = 5;
        //c.gridheight = 1;

        this.add(stagePlotModePanel);

        // Lo de Nahuel
        this.setBorder(BorderFactory.createTitledBorder("Stages Visualizer"));
        JButton b = new JButton("Booom!");
        //c.gridx = 1;
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(GenericUtils.dynamicRangeLoss(Singleton.getInstance().getUserData().getStageList(),100,1000));
            }
        });
        /////////////////////////////////////////////////////////////////////////
    }
}
