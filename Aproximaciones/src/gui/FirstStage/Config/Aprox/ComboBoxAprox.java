package gui.FirstStage.Config.Aprox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ComboBoxAprox extends JPanel{
    String[] AproxStrings = { "Butter", "Chebyshev", "Inverse Chevyshev", "Legendre", "Cauer", "Bessel", "Gauss" };
    JComboBox AproxList = new JComboBox(AproxStrings);

    public ComboBoxAprox(){
        AproxList.setSelectedIndex(0); //Select Butter by Default
        AproxList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(AproxList);
    }
}
