package firststage;

import javax.swing.*;

class ConfigHighPassPanel extends ConfigLowPassPanel {
    public ConfigHighPassPanel() { super(); }

    @Override
    public boolean isParsable(){
        try {
            double wp = Double.parseDouble(textFilterWp.getText());
            double wa = Double.parseDouble(textFilterWa.getText());
            if(wa < wp) return true;
            else {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Wp must be grater than Wa.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Wp and Wa must be real positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}