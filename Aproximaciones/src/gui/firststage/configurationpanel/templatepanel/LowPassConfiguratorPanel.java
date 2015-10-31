package gui.firststage.configurationpanel.templatepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 31/10/2015.
 */
public class LowPassConfiguratorPanel extends ConfiguratorPanel {
    private int TEXT_HEIGH = 50;
    private int TEXT_WIDTH = 65;
    private JTextField textFilterWp = new JTextField("[rad/seg]");
    private JTextField textFilterWa = new JTextField("[rad/seg]");
    private JLabel labelWp = new JLabel("Wp");
    private JLabel labelWa = new JLabel("Wa");

    public LowPassConfiguratorPanel() {
        //Set action after input
        textFilterWa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.Wa = Double.parseDouble(textFilterWa.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for input
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        textFilterWp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //filterData.Wp = Double.parseDouble(textFilterWp.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //Set Size
        textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(labelWa);
        this.add(textFilterWa);
        this.add(labelWp);
        this.add(textFilterWp);
    }

    public double getFilterWpValue() { return Double.parseDouble(textFilterWp.getText());}
    public double getFilterWaValue() { return Double.parseDouble(textFilterWa.getText());}
    public void asd() {}
}