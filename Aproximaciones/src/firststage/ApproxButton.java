package firststage;

import mathematics.Approximation;

import Singleton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxButton extends JPanel {
    JButton approxButton = new JButton("Add Aproximation");

    public ApproxButton() {
        approxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ToDo: Ojo! creo que pasé mal esto... es un ListPanel y no un ApproxList!
                Singleton_S1 s = Singleton_S1.getInstance();
                ApproxList approxList = s.getApproxList();
                ApproxComboBox approxComboBox = s.getApproxComboBox();
                ApproxRadioButton approxRadioButton = s.getApproxRadioButton();
                Singleton ss = Singleton.getInstance();
                double Q = 0;
                int order = 0;
                if (approxRadioButton.isMaxQSelected()) {
                    if (approxRadioButton.isParsable())
                        Q = Double.parseDouble(approxRadioButton.getTextSelectorOrder());
                    else {
                        JInternalFrame frame = new JInternalFrame();
                        JOptionPane.showMessageDialog(frame, "Q must be a double value greater than 0.5.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if (approxRadioButton.isSetOrderSelected()) {
                    if (approxRadioButton.isParsable())
                        order = Integer.parseInt(approxRadioButton.getTextSelectorOrder());
                    else {
                        JInternalFrame frame = new JInternalFrame();
                        JOptionPane.showMessageDialog(frame, "Specified order must be an ingeter value greater than 1.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                Approximation newApprox = new Approximation(approxComboBox.getIndex(), UserData.CurrentTemplate, 0, order, Q);   //El cero harcodeado es el porcentaje de denormalizacion
                UserData.ApproximationList.add(newApprox);
                approxList.AddToList(newApprox.getDetails());
            }
        });
        this.add(approxButton);
    }
}