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
    }
}
