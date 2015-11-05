package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;

import javax.swing.*;
import java.util.List;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxComboBox extends JPanel{
    private JComboBox approxList = new JComboBox();

    public ApproxComboBox(){
        this.add(approxList);
        updateList();
    }

    public int getIndex() { return approxList.getSelectedIndex(); }

    public void updateList() {
        //Here I update the ComboBox. ex. I don't want the Aproximation list to have Bessel if I have a HighPass template
        UserData uData = Singleton.getInstance().getUserData();
        List<String> approxString = Approximation.getStringsToComboBox(uData.CurrentTemplate);
        approxList.removeAllItems();
        for (String x : approxString)
            approxList.addItem(x);
    }
}