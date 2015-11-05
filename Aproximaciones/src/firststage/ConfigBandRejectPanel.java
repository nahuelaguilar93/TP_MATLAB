package firststage;

import javax.swing.*;

class ConfigBandRejectPanel extends ConfigBandPassPanel {
    public ConfigBandRejectPanel() { super(); }

    @Override
    public boolean isParsable(){
        try {
            double wpm = Double.parseDouble(textFilterWpm.getText());
            double wam = Double.parseDouble(textFilterWam.getText());
            double wpp = Double.parseDouble(textFilterWpp.getText());
            double wap = Double.parseDouble(textFilterWap.getText());
            if( wpp > wpm ) {
                if ( wap > wam ) {
                    if ( (wap < wpp) && (wam > wpm) ) {
                        return true;
                    }
                    else {
                        JInternalFrame frame = new JInternalFrame();
                        JOptionPane.showMessageDialog(frame, "Ba must be contained inside Bp", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                else {
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "Wa+ must be grater than Wa-.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Wp+ must be grater than Wp-.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Frequencies must be real positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}