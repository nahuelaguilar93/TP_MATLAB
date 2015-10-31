package gui.firststage.configurationpanel.approximationpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class AproximationPanel extends JPanel{
    private ComboBoxAprox comboBoxAprox;
    private RadioButtonAprox radioButtonAprox;
    private ButtonAprox buttonAprox;

    public ComboBoxAprox getComboBoxAprox() {
        return comboBoxAprox;
    }

    public AproximationPanel(JPanel listPanel) {
        comboBoxAprox = new ComboBoxAprox();
        radioButtonAprox = new RadioButtonAprox();
        buttonAprox = new ButtonAprox(comboBoxAprox, radioButtonAprox, listPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Aproximation Configuration"));

        this.add(comboBoxAprox);
        this.add(radioButtonAprox);
        this.add(buttonAprox);
    }
}