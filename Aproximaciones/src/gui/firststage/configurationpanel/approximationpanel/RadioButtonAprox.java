package gui.firststage.configurationpanel.approximationpanel;

import data.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class RadioButtonAprox extends JPanel {
    JRadioButton minOrder = new JRadioButton("Min Order");
    JRadioButton maxQ = new JRadioButton("Max Q");
    JRadioButton selectOrder = new JRadioButton("Set Order");
    JTextField textSelectOrder = new JTextField("Set Order");

    //Hacer que no puedas deseleccionar el check box
    public RadioButtonAprox() {
        //ToDo: Revisar esto para que funcione.
        minOrder.setMnemonic(KeyEvent.VK_M); //Te subraya la letra y apretas y funciona
        maxQ.setMnemonic(KeyEvent.VK_Q);
        selectOrder.setMnemonic(KeyEvent.VK_S);

        //Set Default option
        minOrder.setSelected(true);
        maxQ.setSelected(false);
        selectOrder.setSelected(false);
        textSelectOrder.setEnabled(false);

        //Hardcode Size of TextField
        textSelectOrder.setMaximumSize(new Dimension(80, 24));
        textSelectOrder.setPreferredSize(new Dimension(80, 24));
        textSelectOrder.setMinimumSize(new Dimension(50, 24));

        //AddListener
        minOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    maxQ.setSelected(false);
                    selectOrder.setSelected(false);
                    textSelectOrder.setEnabled(false);
                }
            }
        });
        maxQ.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    minOrder.setSelected(false);
                    selectOrder.setSelected(false);
                    textSelectOrder.setEnabled(true);
                    textSelectOrder.setText("Set max Q");
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
                    textSelectOrder.setText("Set Order");
                }
            }
        });
        textSelectOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDataValid()) {
                    //this line doesn't work, I neeed to know why
                    //ud.setN(Integer.parseInt(textSelectOrder.getText()));
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

    public JRadioButton getMinOrder() {
        return minOrder;
    }
    public JRadioButton getMaxQ() {
        return maxQ;
    }
    public JRadioButton getSelectOrder() {
        return selectOrder;
    }
    public JTextField getTextSelectOrder() {
        return textSelectOrder;
    }
}
