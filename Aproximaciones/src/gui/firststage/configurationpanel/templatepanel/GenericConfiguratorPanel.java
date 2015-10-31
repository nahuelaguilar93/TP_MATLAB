package gui.firststage.configurationpanel.templatepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 31/10/2015.
 */
public class GenericConfiguratorPanel extends JPanel {
    private int TEXT_HEIGH = 50;
    private int TEXT_WIDTH = 65;
    JTextField textFilterAp = new JTextField("[dB]");
    JTextField textFilterAa = new JTextField("[dB]");
    JLabel labelAa = new JLabel("Aa");
    JLabel labelAp = new JLabel("Ap");

    public GenericConfiguratorPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        textFilterAa.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterAp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterAa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterAp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

        textFilterAa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.Aa = Double.parseDouble(textFilterAa.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        textFilterAp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.Ap = Double.parseDouble(textFilterAp.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.add(labelAa);
        this.add(textFilterAa);
        this.add(labelAp);
        this.add(textFilterAp);
    }

    public double getFilterApValue() { return Double.parseDouble(textFilterAp.getText());}
    public double getFilterAaValue() { return Double.parseDouble(textFilterAa.getText());}
}