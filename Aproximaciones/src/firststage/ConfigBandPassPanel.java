package firststage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConfigBandPassPanel extends ConfiguratorPanel {
    private int TEXT_HEIGH = 50;
    private int TEXT_WIDTH = 65;
    JTextField textFilterWo = new JTextField("[rad/seg]");
    JTextField textFilterB = new JTextField("[rad/seg");
    JLabel labelWo = new JLabel("Wo");
    JLabel labelB = new JLabel("B");

    public ConfigBandPassPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        textFilterB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.B = Double.parseDouble(textFilterB.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        textFilterWo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.Wo = Double.parseDouble(textFilterWo.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        textFilterWo.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWo.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterB.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

        this.add(labelWo);
        this.add(textFilterWo);
        this.add(labelB);
        this.add(textFilterB);
    }
}
