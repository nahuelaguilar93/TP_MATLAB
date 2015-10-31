package gui.firststage.configurationpanel.approximationpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class AproximationPanel extends JPanel{
    private ComboBoxAprox comboBoxAprox = new ComboBoxAprox();
    private ButtonAprox buttonAprox = new ButtonAprox();
    private CheckBoxAprox checkBoxAprox = new CheckBoxAprox();


    public AproximationPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(BorderFactory.createTitledBorder("Aproximation Configuration"));

            this.add(comboBoxAprox);
            this.add(checkBoxAprox);
            this.add(buttonAprox);
    }
}
