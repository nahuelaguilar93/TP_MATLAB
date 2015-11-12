package secondstage;

import Data.Singleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 10/11/2015.
 */
public class FilterMode extends JPanel{
    JButton showHoleFilterButton;
    JLabel lossDynamicRange = new JLabel("Loss Dynamic Range (LDR): ");

    FilterMode() {
        Singleton s = Singleton.getInstance();
        Singleton_S2 s2 = Singleton_S2.getInstance();

        showHoleFilterButton = new JButton("Show Accumulated Stages");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Accumulated Stages"));

        showHoleFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s2.getPlotStagePanel().updatePlot();
            }
        });

        // Lo de Nahuel
        JButton b = new JButton("Minimize DRL");
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton.getInstance().getUserData().getStageDisposition().gainCorrection();
                Singleton.getInstance().getUserData().getStageDisposition().minimizeDRL();
                Singleton_S2.getInstance().getPlotStagePanel().updatePlot();
                updateLDR();
                //                System.out.println(GenericUtils.dynamicRangeLoss(Singleton.getInstance().getUserData().getStageList(),100,1000));
            }
        });
        /////////////////////////////////////////////////////////////////////////

        //this.add(showHoleFilterButton);
        this.add(lossDynamicRange);
    }

    public void updateLDR() {
        lossDynamicRange.setText("Loss Dynamic Range (LDR): " + Singleton.getInstance().getUserData().getStageDisposition().getDRL());     //Luewgo agregarle + lo que me pase nahuel
    }
}
