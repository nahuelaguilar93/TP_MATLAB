package gui.firststage.configurationpanel.approximationpanel;

import data.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class RadioButtonAprox extends JPanel {
    private ButtonGroup groupOfRadioButtons = new ButtonGroup();
    private JRadioButton minOrder = new JRadioButton("Min Order");
    private JRadioButton maxQ = new JRadioButton("Max Q");
    private JRadioButton selectOrder = new JRadioButton("Set Order");
    private JTextField textSelectOrder = new JTextField("Set Order");

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
                    textSelectOrder.setEnabled(false);
                }
            }
        });
        maxQ.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textSelectOrder.setEnabled(true);
                    textSelectOrder.setText("Set max Q");
                }
            }
        });
        selectOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textSelectOrder.setEnabled(true);
                    textSelectOrder.setText("Set Order");
                }
            }
        });
        textSelectOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isDataValid()) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "Invalid order Number, it must be a positive integer number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        groupOfRadioButtons.add(minOrder);
        groupOfRadioButtons.add(maxQ);
        groupOfRadioButtons.add(selectOrder);

        this.add(minOrder);
        this.add(maxQ);
        this.add(selectOrder);
        this.add(textSelectOrder);
    }

    private boolean isDataValid() {
        try {
            int i = Integer.parseInt(textSelectOrder.getText());
            return (i > 1);
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }

    public boolean isMinOrderSelected () { return minOrder.isSelected(); }
    public boolean isMaxQSelected() { return maxQ.isSelected(); }
    public boolean isSelectOrderSelected() { return selectOrder.isSelected(); }

    public int getTextSelectorOrder() { return Integer.parseInt(textSelectOrder.getText()); }
}
