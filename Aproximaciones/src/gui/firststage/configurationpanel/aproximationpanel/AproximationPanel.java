package gui.firststage.configurationpanel.aproximationpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class AproximationPanel extends JPanel{
    private static ComboBoxAprox comboBoxAprox = new ComboBoxAprox();
    private static ButtonAprox buttonAprox = new ButtonAprox();
    private static CheckBoxAprox checkBoxAprox = new CheckBoxAprox();


    public AproximationPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(BorderFactory.createTitledBorder("Aproximation Configuration"));

            this.add(comboBoxAprox);
            this.add(checkBoxAprox);
            this.add(buttonAprox);
    }
}
