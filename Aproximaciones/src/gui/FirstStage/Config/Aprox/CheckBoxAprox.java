package gui.FirstStage.Config.Aprox;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class CheckBoxAprox extends JPanel {
    JCheckBox minOrder = new JCheckBox("Min Order");
    JCheckBox minQ = new JCheckBox("Min Q");
    JCheckBox selectOrder = new JCheckBox("Select Order");
    JTextField textSelectOrder = new JTextField("Select Order");

    public CheckBoxAprox() {
        minOrder.setMnemonic(KeyEvent.VK_M);
        minQ.setMnemonic(KeyEvent.VK_Q);
        selectOrder.setMnemonic(KeyEvent.VK_S);

        //Set Default option
        minOrder.setSelected(true);
        minQ.setSelected(false);
        selectOrder.setSelected(false);
        textSelectOrder.setEnabled(false);

        //AddListener
        minOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    minQ.setSelected(false);
                    selectOrder.setSelected(false);
                    textSelectOrder.setEnabled(false);
                    //Here we must change UserData, get minOrder=true and reset the other 2 fields! The same aplies to the other checkbox items.
                }
            }
        });
        minQ.addItemListener(new ItemListener() {
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
                    minQ.setSelected(false);
                    minOrder.setSelected(false);
                    textSelectOrder.setEnabled(true);
                }
            }
        });

        this.add(minOrder);
        this.add(minQ);
        this.add(selectOrder);
        this.add(textSelectOrder);
    }



}
