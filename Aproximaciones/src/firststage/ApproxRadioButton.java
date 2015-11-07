package firststage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ApproxRadioButton extends JPanel {
    private ButtonGroup groupOfRadioButtons = new ButtonGroup();
    private JRadioButton minOrder = new JRadioButton("Min Order");
    private JRadioButton maxQ = new JRadioButton("Max Q");
    private JRadioButton setOrder = new JRadioButton("Set Order");
    private JTextField textField = new JTextField();

    //Hacer que no puedas deseleccionar el check box
    public ApproxRadioButton() {
        //ToDo: Revisar esto para que funcione.
        minOrder.setMnemonic(KeyEvent.VK_M); //Te subraya la letra y apretas y funciona
        maxQ.setMnemonic(KeyEvent.VK_Q);
        setOrder.setMnemonic(KeyEvent.VK_S);

        //Set Default option
        minOrder.setSelected(true);
//        maxQ.setSelected(false);
//        setOrder.setSelected(false);
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
                    textField.grabFocus();
                }
            }
        });
        setOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //CheckBox Selected
                    textField.setEnabled(true);
                    textField.grabFocus();
                }
            }
        });

        groupOfRadioButtons.add(minOrder);
        groupOfRadioButtons.add(maxQ);
        groupOfRadioButtons.add(setOrder);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(minOrder)
                                .addComponent(maxQ)
                                .addComponent(setOrder))
                        .addComponent(textField)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(minOrder)
                                .addComponent(maxQ)
                                .addComponent(setOrder))
                        .addComponent(textField)
        );
    }

    public boolean isParsable(){
        if (setOrder.isSelected()) {
            try {
                int i = Integer.parseInt(textField.getText());
                return (i > 1);
            } catch (NumberFormatException e) {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Specified order must be natural number greater than 1.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (maxQ.isSelected()){
            try {
                Double i = Double.parseDouble(textField.getText());
                return (i > 0.5);
            } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Q must be a real number greater than 0.5.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
            }
        } else return true;
    }

    public boolean isMinOrderSelected () { return minOrder.isSelected(); }
    public boolean isMaxQSelected() { return maxQ.isSelected(); }
    public boolean isSetOrderSelected() { return setOrder.isSelected(); }
    public String getTextSelectorOrder() { return textField.getText(); }
}
