package gui.firststage.Config.Aprox;

import data.UserData;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class CheckBoxAprox extends JPanel {
    JCheckBox minOrder = new JCheckBox("Min Order");
    JCheckBox maxQ = new JCheckBox("Max Q");
    JCheckBox selectOrder = new JCheckBox("Select Order");
    JTextField textSelectOrder = new JTextField("Select Order");

    public CheckBoxAprox() {
        minOrder.setMnemonic(KeyEvent.VK_M);
        maxQ.setMnemonic(KeyEvent.VK_Q);
        selectOrder.setMnemonic(KeyEvent.VK_S);

        //Set Default option
        minOrder.setSelected(true);
        maxQ.setSelected(false);
        selectOrder.setSelected(false);
        textSelectOrder.setEnabled(false);

        //AddListener
        minOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    maxQ.setSelected(false);
                    selectOrder.setSelected(false);
                    textSelectOrder.setEnabled(false);
                    //Here we must change UserData, get minOrder=true and reset the other 2 fields! The same aplies to the other checkbox items.
                }
            }
        });
        maxQ.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    minOrder.setSelected(false);
                    selectOrder.setSelected(false);
                    textSelectOrder.setEnabled(false);
                }
            }
        });
        selectOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    maxQ.setSelected(false);
                    minOrder.setSelected(false);
                    textSelectOrder.setEnabled(true);
                }
            }
        });
        textSelectOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDataValid()) {
                    UserData.setN(Integer.parseInt(textSelectOrder.getText()));
                }
                else {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "Invalid order Number, it must be a positive integer number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.add(minOrder);
        this.add(maxQ);
        this.add(selectOrder);
        this.add(textSelectOrder);
    }

    private boolean isDataValid() {
        try {
            int i = Integer.parseInt(textSelectOrder.getText());
            if ( i > 1) {return true;}
            else {return false;}
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }
}
