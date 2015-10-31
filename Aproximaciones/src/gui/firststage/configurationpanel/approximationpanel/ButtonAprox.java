package gui.firststage.configurationpanel.approximationpanel;

import data.UserData;
import gui.firststage.configurationpanel.listpanel.ListOfAprox;
import gui.firststage.configurationpanel.listpanel.ListPanel;
import tclib.Approximation;

import javax.jws.soap.SOAPBinding;
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
                ListPanel listPanel = (ListPanel) listOfAproxPanel;
                ListOfAprox listOfAprox = (ListOfAprox) listPanel.getListOfAprox();
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
                //listOfAprox.AddToList(/* newAprox.getDetails*/);
                //listOfAprox.addItemToList(/* newAprox.getDetails */);
            }
        });
        this.add(buttonAprox);
    }
}
