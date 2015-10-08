package gui.firststage.configurationpanel.filterselectpanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class TextFieldFilterType extends JPanel {

    JTextField textFilterBandWith = new JTextField("1");
    JTextField textFilterWp = new JTextField("[rad/seg]");
    JTextField textFilterAp = new JTextField("[dB]");
    JTextField textFilterWa = new JTextField("[rad/seg]");
    JTextField textFilterAa = new JTextField("[dB]");
    JLabel labelBandWith = new JLabel("B");
    JLabel labelWp = new JLabel("Wp");
    JLabel labelWa = new JLabel("Wa");
    JLabel labelAa = new JLabel("Aa");
    JLabel labelAp = new JLabel("Ap");

    public TextFieldFilterType() {
        textFilterBandWith.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(labelAa);
        this.add(textFilterAa);
        this.add(labelAp);
        this.add(textFilterAp);
        this.add(labelBandWith);
        this.add(textFilterBandWith);
        this.add(labelWa);
        this.add(textFilterWa);
        this.add(labelWp);
        this.add(textFilterWp);
    }
}

