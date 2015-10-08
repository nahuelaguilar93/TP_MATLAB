package gui.firststage.Config.Filter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 6/10/2015.
 */
public class ComboBoxFilterType extends JPanel{
    String[] FilterStrings = { "Band Pass", "Low Pass", "High Pass", "Reject Band" };
    JComboBox filterList = new JComboBox(FilterStrings);

    public ComboBoxFilterType(){
        filterList.setSelectedIndex(1); //Low Pass as Default
        filterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String SelectedFilter = (String)cb.getSelectedItem();
            }
        });

        this.add(filterList);
    }

}
