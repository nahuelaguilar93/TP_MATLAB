package gui.firststage.Config.Filter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class TextFieldFilterType extends JPanel {

    JTextField textFieldFilterType = new JTextField("1");
    JTextField textFilterWp = new JTextField("[rad/seg]");
    JTextField textFilterAp = new JTextField("[dB]");
    JTextField textFilterWa = new JTextField("[rad/seg]");
    JTextField textFilterAa = new JTextField("[dB]");
    JLabel labelAnchoDeBanda = new JLabel("Ancho de Banda (B)");
    JLabel labelWp = new JLabel("Frecuencia de Paso (Wp)");
    JLabel labelWa = new JLabel("Frecuencia de Atenuacion (Wa)");
    JLabel labelAa = new JLabel("Amplitud de Atenuacion (Aa)");
    JLabel labelAp = new JLabel("Amplitud de Paso (Ap)");

    public TextFieldFilterType() {
        textFieldFilterType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(labelAnchoDeBanda);
        this.add(textFieldFilterType);
        this.add(labelAa);
        this.add(textFilterAa);
        this.add(labelAp);
        this.add(textFilterAp);
        this.add(labelWa);
        this.add(textFilterWa);
        this.add(labelWp);
        this.add(textFilterWp);
    }
}

