package firststage;

import mathematics.Approximation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import UserData;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxComboBox extends JPanel{
    private JComboBox approxList = new JComboBox();

    public ApproxComboBox(){
        updateList();
//        approxList.setSelectedIndex(0); //Select Butter by Default
        approxList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(approxList);
    }

    public int getIndex() { return approxList.getSelectedIndex(); }

    public void updateList() {
        //Here I update the ComboBox. ex. I don't want the Aproximation list to have Bessel if I have a HighPass template
        List<String> approxString = Approximation.getStringsToComboBox(UserData.CurrentTemplate);
        approxList.removeAllItems();
        for (String x : approxString)
            approxList.addItem(x);
    }
}