package secondstage;

import Data.Singleton;
import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;
import tclib.GenericUtils;

import javax.swing.*;
import java.util.List;

/**
 * Created by NEGU on 10/11/2015.
 */
public class StageProperties extends JPanel{
    Singleton s = Singleton.getInstance();
    Singleton_S2 s2 = Singleton_S2.getInstance();

    JLabel qLabel = new JLabel("Quality Factor (Q): ");
    JLabel qValue = new JLabel("");
    JLabel dinamicRangeLabel = new JLabel("Dinamic Range (DR): ");
    JLabel dinamicRangeValue = new JLabel("");
    JLabel wpLabel = new JLabel("Pole Frequency (wp): ");
    JLabel wpValue = new JLabel("");
    JLabel wzLabel = new JLabel("Zero Frequency (wz): ");
    JLabel wzValue = new JLabel("");

    StageProperties(){
        //TODO: agergar todo lo que uno quiera aca de info
        this.setBorder(BorderFactory.createTitledBorder("Stage Properties"));

        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(qLabel)
                                .addComponent(qValue)
                                .addComponent(wpLabel)
                                .addComponent(wpValue))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dinamicRangeLabel)
                                .addComponent(dinamicRangeValue)
                                .addComponent(wzLabel)
                                .addComponent(wzValue))
                );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(qLabel)
                                .addComponent(qValue)
                                .addComponent(wpLabel)
                                .addComponent(wpValue))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dinamicRangeLabel)
                                .addComponent(dinamicRangeValue)
                                .addComponent(wzLabel)
                                .addComponent(wzValue))
        );

    }

    public void updateLabels() {
        List<Stage> currentStageList = s.getUserData().getStageList();
        int index = s2.getPoleZeroListsPanel().getStagesListIndex();

        if ( index != -1 ) {
            updateQ(currentStageList.get(index).getPoles());
            updatewp(currentStageList.get(index).getPoles());
            if (currentStageList.get(index).getZeros().length != 0) {
                updatewz(currentStageList.get(index).getZeros());
            }
        }
    }
    private void updateQ(Complex[] pole) { qValue.setText(String.format("%.2f", GenericUtils.getQ(pole[0]))); }
    private void updatewp(Complex[] pole) { wpValue.setText(String.format("%.2f", pole[0].getImaginary())); }
    private void updatewz(Complex[] zero) { wzValue.setText(String.format("%.2f", zero[0].getImaginary())); }
}

