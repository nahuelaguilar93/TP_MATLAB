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
    private JRadioButton cumModeRadioButton = new JRadioButton("Plot cumulative Stages");


    StagePlotModePanel() {
        multipleModeRadioButton.setSelected(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        multipleModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {
                    List<TransferFunction> myTFList = new ArrayList<>();
                    for ( Stage x : s.getUserData().getStageList())
                          myTFList.add(new TransferFunction(x.getTF()));
                    s2.getPlotStagePanel().updatePlot(myTFList, -1);
                }
            }
        });
        singleModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {
                    List<TransferFunction> myTFList = new ArrayList<>();
                    for ( Stage x : s.getUserData().getStageList())
                        myTFList.add(new TransferFunction(x.getTF()));
                    s2.getPlotStagePanel().updatePlot(myTFList, s2.getPoleZeroListsPanel().getStagesListIndex());
                }
            }
        });
        cumModeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!s.getUserData().getStageList().isEmpty()) {


                    List<TransferFunction> acumTF =  new ArrayList<>();
                    acumTF.add(new TransferFunction(s.getUserData().getStageList().get(0).getTF()));
                    for(int i = 1; i < s.getUserData().getStageList().size(); i++){
                        TransferFunction nextTF = new TransferFunction(acumTF.get(i-1));
                        nextTF.multiply(s.getUserData().getStageList().get(i).getTF());
                        acumTF.add(nextTF);
                    }
                    s2.getPlotStagePanel().updatePlot(acumTF, s2.getPoleZeroListsPanel().getStagesListIndex());
                }
            }
        });

        groupOfPlotMode.add(singleModeRadioButton);
        groupOfPlotMode.add(multipleModeRadioButton);
        groupOfPlotMode.add(cumModeRadioButton);

        this.setBorder(BorderFactory.createTitledBorder("Plot Mode"));
        this.add(singleModeRadioButton);
        this.add(multipleModeRadioButton);
        this.add(cumModeRadioButton);
    }

    public void updateStagePlot() {
        if (multipleModeRadioButton.isSelected()) {
            if (!s.getUserData().getStageList().isEmpty()) {
                List<TransferFunction> myTFList = new ArrayList<>();
                for ( Stage x : s.getUserData().getStageList()) {
                    myTFList.add(x.getTF());
                }
                s2.getPlotStagePanel().updatePlot(myTFList, -1);
            }
        }
        else {
            if (!s.getUserData().getStageList().isEmpty()) {
                List<TransferFunction> myTFList = new ArrayList<>();
                for ( Stage x : s.getUserData().getStageList()) {
                    myTFList.add(x.getTF());
                }
                s2.getPlotStagePanel().updatePlot(myTFList, s2.getPoleZeroListsPanel().getStagesListIndex());
            }
        }
    }

}
