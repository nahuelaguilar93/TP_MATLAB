package gui.FirstStage.Config.Aprox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ButtonAprox extends JPanel{
    JButton buttonAprox = new JButton("Add Aproximation");
    public ButtonAprox() {
        buttonAprox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(buttonAprox);
    }
}
