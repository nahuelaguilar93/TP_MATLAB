package gui.firststage.configurationpanel.filterselectpanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 6/10/2015.
 */
public class ButtonFilterType extends JPanel {
    JButton buttonFilterType = new JButton("Create Template");

    public ButtonFilterType() {
        buttonFilterType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (checkData(index)) {
                    /*
                    ud.setB(filterData.B);
                    ud.setAa(filterData.Aa);
                    ud.setAp(filterData.Ap);
                    ud.setWa(filterData.Wa);
                    ud.setWp(filterData.Wp);
                    ud.setWo(filterData.Wo);
                    */
                //}
            }
        });
        this.add(buttonFilterType);
    }
/*
    public boolean checkData(int index) {
        //Check Data only has to check if the values are valid compare to each other. If they are valid doubles has already been checked
        if (filterData.Ap < filterData.Aa) {
            //Checks data, as each case returns true or false, no break is needed.
            switch (index) {
                case 0:
                    return lowPassCheck();
                case 1:
                    return highPassCheck();
                case 2:
                    return passBandCheck();
                case 3:
                    return rejectBandCheck();
                default:
                    return false;
            }
        }
        else {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Ap must be lower than Aa", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean lowPassCheck() {
        if ( filterData.Wp < filterData.Wa) {
            return true;
        }
        else {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Wp must be lower than Wa", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private boolean highPassCheck() {
        if ( filterData.Wp > filterData.Wa) {
            return true;
        }
        else {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Wa must be lower than Wp", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private boolean passBandCheck() {
        return true;
    }
    private boolean rejectBandCheck() {
        return true;
    }*/
}


