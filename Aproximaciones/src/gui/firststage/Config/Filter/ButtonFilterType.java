package gui.firststage.Config.Filter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 6/10/2015.
 */
public class ButtonFilterType extends JPanel {
    JButton buttonFilterType = new JButton("Crear Plantilla");

    public ButtonFilterType() {
        buttonFilterType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(buttonFilterType);
    }
}