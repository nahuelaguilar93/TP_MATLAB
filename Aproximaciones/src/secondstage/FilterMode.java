package secondstage;

import Data.Singleton;
import tclib.TransferFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEGU on 10/11/2015.
 */
public class FilterMode extends JPanel{
    JButton showHoleFilterButton;
    JLabel lossDynamicRange = new JLabel("Loss Dynamic Range (LDR): ");

    FilterMode() {
        Singleton s = Singleton.getInstance();
        Singleton_S2 s2 = Singleton_S2.getInstance();
        List<TransferFunction> myTFList = new ArrayList<>();
        showHoleFilterButton = new JButton("Show Accumulated Stages");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Accumulated Stages"));

        showHoleFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTFList.add(s.getUserData().getApproximationList().get(s.getUserData().getSelection()).getTF());
                s2.getPlotStagePanel().updatePlot(myTFList, 0);
            }
        });

        this.add(showHoleFilterButton);
        this.add(lossDynamicRange);
    }

    public void updateLDR() {
        lossDynamicRange.setText("Loss Dynamic Range (LDR): ");     //Luewgo agregarle + lo que me pase nahuel
    }
}
