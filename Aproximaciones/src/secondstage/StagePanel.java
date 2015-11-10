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
    StageVisualizerPanel stageVisualizerPanel;
    StageProperties stageProperties;

    public StagePanel() {
        Singleton_S2 s = Singleton_S2.getInstance();
        this.setBorder(BorderFactory.createTitledBorder("Stages Visualizer"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        stageVisualizerPanel = s.getStageVisualizerPanel();
        stageProperties = s.getStageProperties();

        this.add(stageVisualizerPanel);
        this.add(stageProperties);

        // Lo de Nahuel
        JButton b = new JButton("Booom!");
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
