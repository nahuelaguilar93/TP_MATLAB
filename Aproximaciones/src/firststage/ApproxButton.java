package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxButton extends JPanel {
    JButton approxButton = new JButton("Add Aproximation");

    public ApproxButton() {
        this.add(approxButton);
        approxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Singleton_S1 s = Singleton_S1.getInstance();
                FilterList filterList = s.getFilterList();
                ApproxComboBox approxComboBox = s.getApproxComboBox();
                ApproxRadioButton approxRadioButton = s.getApproxRadioButton();
                Singleton ss = Singleton.getInstance();
                UserData uData = ss.getUserData();
                double Q = 0;
                int order = 0;
                if (approxRadioButton.isMaxQSelected()) {
                    if (approxRadioButton.isParsable())
                        Q = Double.parseDouble(approxRadioButton.getTextSelectorOrder());
                    else return;
                } else if (approxRadioButton.isSetOrderSelected()) {
                    if (approxRadioButton.isParsable())
                        order = Integer.parseInt(approxRadioButton.getTextSelectorOrder());
                    else return;
                }
                double sliderPercentage = s.getSliderDesnorm().getSliderValue();
                Approximation newApprox = new Approximation(approxComboBox.getIndex(), uData.getCurrentTemplate(), sliderPercentage, order, Q);
                for(Approximation x : uData.getApproximationList())
                    if(x.equals(newApprox)) {
                        s.getPlotPlot().updatePlot();
                        return;
                    }
                uData.getApproximationList().add(newApprox);
                filterList.updateList();
                s.getPlotPlot().updatePlot();
            }
        });
    }
}