package gui.firststage.configurationpanel.approximationpanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ComboBoxAprox extends JPanel{
    private static String[] AproxStrings = { "Butterworth", "Chebyshev", "Inverse Chebyshev", "Legendre", "Cauer", "Bessel", "Gauss" };
    static JComboBox AproxList = new JComboBox(AproxStrings);

    public ComboBoxAprox(){
        AproxList.setSelectedIndex(0); //Select Butter by Default
        AproxList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        this.add(AproxList);
    }

    public static void UpdateComboBoxAproximation() {
        //Here I update the ComboBox. ex. I don't want the Aproximation list to have Bessel if I have a HighPass template


    }
}
