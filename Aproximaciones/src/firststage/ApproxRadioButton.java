package firststage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxRadioButton extends JPanel {
    private ButtonGroup groupOfRadioButtons = new ButtonGroup();
    private JRadioButton minOrder = new JRadioButton("Min Order");
    private JRadioButton maxQ = new JRadioButton("Max Q");
    private JRadioButton setOrder = new JRadioButton("Set Order");
    private JTextField textField = new JTextField("Set Order");

    //Hacer que no puedas deseleccionar el check box
    public ApproxRadioButton() {
        //ToDo: Revisar esto para que funcione.
        minOrder.setMnemonic(KeyEvent.VK_M); //Te subraya la letra y apretas y funciona
        maxQ.setMnemonic(KeyEvent.VK_Q);
        setOrder.setMnemonic(KeyEvent.VK_S);

        //Set Default option
        minOrder.setSelected(true);
        maxQ.setSelected(false);
        setOrder.setSelected(false);
        textField.setEnabled(false);

        //Hardcode Size of TextField
        textField.setMaximumSize(new Dimension(80, 24));
        textField.setPreferredSize(new Dimension(80, 24));
        textField.setMinimumSize(new Dimension(50, 24));

        //AddListener
        minOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textField.setEnabled(false);
                }
            }
        });
        maxQ.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textField.setEnabled(true);
                    textField.setText("Set max Q");
                }
            }
        });
        setOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textField.setEnabled(true);
                    textField.setText("Set Order");
                }
            }
        });
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String s = textField.getText();
                if (s.equals("Set Order") || s.equals("Set max Q"))
                    textField.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        groupOfRadioButtons.add(minOrder);
        groupOfRadioButtons.add(maxQ);
        groupOfRadioButtons.add(setOrder);

        this.add(minOrder);
        this.add(maxQ);
        this.add(setOrder);
        this.add(textField);
    }

    public boolean isParsable(){
        try {
            if (minOrder.isSelected()) {
                int i = Integer.parseInt(textField.getText());
                return (i > 1);
            } else {
                Double i = Double.parseDouble(textField.getText());
                return (i > 0.5);
            }
        }
        catch (NumberFormatException e) { return false; }
    }

    public boolean isMinOrderSelected () { return minOrder.isSelected(); }
    public boolean isMaxQSelected() { return maxQ.isSelected(); }
    public boolean isSetOrderSelected() { return setOrder.isSelected(); }
    public String getTextSelectorOrder() { return textField.getText(); }
}
