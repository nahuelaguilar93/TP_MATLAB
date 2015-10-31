package gui.firststage.configurationpanel.approximationpanel;

import data.UserData;
import gui.firststage.configurationpanel.listpanel.ListOfAprox;
import mathematics.Approximation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ButtonAprox extends JPanel{
    JButton buttonAprox = new JButton("Add Aproximation");
    public ButtonAprox(JPanel comboBoxAproxPanel, JPanel radioButtonAproxPanel, JPanel listOfAproxPanel) {
        buttonAprox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ToDo: Ojo! creo que pasé mal esto... es un ListPanel y no un ListOfAprox!
                ListOfAprox listOfAprox = (ListOfAprox) listOfAproxPanel;
                ComboBoxAprox comboBoxAprox = (ComboBoxAprox) comboBoxAproxPanel;
                RadioButtonAprox radioButtonAprox = (RadioButtonAprox) radioButtonAproxPanel;
                double Q = 0;
                int order = 0;
                if (radioButtonAprox.isMaxQSelected()) {
                    Q = radioButtonAprox.getTextSelectorOrder();
                }
                else if (radioButtonAprox.isSelectOrderSelected()) {
                    order = radioButtonAprox.getTextSelectorOrder();
                }
                Approximation newAprox = new Approximation(comboBoxAprox.getComboBoxAproxIndex(), UserData.CurrentTemplate, order, Q);
                UserData.ApproximationList.add(newAprox);
                listOfAprox.AddToList(newAprox.getDetails());
            }
        });
        this.add(buttonAprox);
    }
}