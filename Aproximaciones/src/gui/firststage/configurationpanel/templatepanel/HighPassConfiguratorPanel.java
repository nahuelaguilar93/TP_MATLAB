package gui.firststage.configurationpanel.templatepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 31/10/2015.
 */
public class HighPassConfiguratorPanel extends ConfiguratorPanel {
    private int TEXT_HEIGH = 50;
    private int TEXT_WIDTH = 65;
    JTextField textFilterWp = new JTextField();
    JTextField textFilterWa = new JTextField();
    JLabel labelWp = new JLabel("Wp");
    JLabel labelWa = new JLabel("Wa");
    JLabel units = new JLabel("Hz");
    private double Wp;
    private double Wa;

    public HighPassConfiguratorPanel() {
        textFilterWa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Wa = Double.parseDouble(textFilterWa.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        textFilterWp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Wp = Double.parseDouble(textFilterWp.getText());
                } catch (NumberFormatException nfe) {
                    //error Message for order
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "The Input must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        textFilterWa.setText("bla bla");
        textFilterWp.setText("Def1");

        textFilterWp.setMinimumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWp.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));
        textFilterWa.setMaximumSize(new Dimension(TEXT_WIDTH, TEXT_HEIGH));

        this.add(labelWa);
        this.add(textFilterWa);
        this.add(labelWp);
        this.add(textFilterWp);
    }

    public double getFilterWpValue() { return Double.parseDouble(textFilterWp.getText());}
    public double getFilterWaValue() { return Double.parseDouble(textFilterWa.getText());}
}