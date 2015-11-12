package secondstage;

import Data.Singleton;
import mathematics.Stage;
import tclib.TransferFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEGU on 10/11/2015.
 */
public class StagePlotModePanel extends JPanel{
    private Singleton_S2 s2 = Singleton_S2.getInstance();
    private Singleton s = Singleton.getInstance();
    private ButtonGroup groupOfPlotMode = new ButtonGroup();
    private JRadioButton singleModeRadioButton = new JRadioButton("Plot Selected Stage");
    private JRadioButton multipleModeRadioButton = new JRadioButton("Plot All Stages");
    private JRadioButton cumModeRadioButton = new JRadioButton("Plot Acumulative Stages");
    private JRadioButton showAllAcumulativeRadioButton = new JRadioButton("Plot Final Acumulative Stages");

    StagePlotModePanel() {
        multipleModeRadioButton.setSelected(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        multipleModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {
                    s2.getPlotStagePanel().updatePlot();
                }
            }
        });
        singleModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {
                    s2.getPlotStagePanel().updatePlot();
                }
            }
        });
        cumModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {
                    s2.getPlotStagePanel().updatePlot();
                }
            }
        });
        showAllAcumulativeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s2.getPlotStagePanel().updatePlot();
            }
        });

        groupOfPlotMode.add(singleModeRadioButton);
        groupOfPlotMode.add(multipleModeRadioButton);
        groupOfPlotMode.add(cumModeRadioButton);
        groupOfPlotMode.add(showAllAcumulativeRadioButton);

        this.setBorder(BorderFactory.createTitledBorder("Plot Mode"));
        this.add(singleModeRadioButton);
        this.add(multipleModeRadioButton);
        this.add(cumModeRadioButton);
        this.add(showAllAcumulativeRadioButton);
    }

    public JRadioButton getSingleModeRadioButton() { return singleModeRadioButton; }
    public JRadioButton getMultipleModeRadioButton() { return multipleModeRadioButton; }
    public JRadioButton getCumModeRadioButton() { return cumModeRadioButton; }
}
