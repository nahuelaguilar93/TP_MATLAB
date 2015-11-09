package firststage;

import javax.swing.*;
import java.awt.*;

class ConfigBandPassPanel extends JPanel {
    protected final int TEXT_HEIGHT = 24;
    protected final int TEXT_WIDTH = 70;
    protected JTextField textFilterWpm = new JTextField();
    protected JTextField textFilterWam = new JTextField();
    protected JTextField textFilterWpp = new JTextField();
    protected JTextField textFilterWap = new JTextField();

    public ConfigBandPassPanel() {
        setTextBoxes(1200,1000,4000,4800);
        textFilterWpm.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWam.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWpp.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWap.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        JLabel labelWpm = new JLabel("Wp-:");
        JLabel labelWam = new JLabel("Wa-:");
        JLabel labelWpp = new JLabel("Wp+:");
        JLabel labelWap = new JLabel("Wa+:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelWpm)
                        .addComponent(labelWam)
                        .addComponent(labelWpp)
                        .addComponent(labelWap))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFilterWpm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterWam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterWpp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterWap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWpm)
                        .addComponent(textFilterWpm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWam)
                        .addComponent(textFilterWam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWpp)
                        .addComponent(textFilterWpp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWap)
                        .addComponent(textFilterWap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }

    public boolean isParsable(){
       try {
           double wpm = Double.parseDouble(textFilterWpm.getText());
           double wam = Double.parseDouble(textFilterWam.getText());
           double wpp = Double.parseDouble(textFilterWpp.getText());
           double wap = Double.parseDouble(textFilterWap.getText());
           if( wpp > wpm ) {
               if ( wap > wam ) {
                   if ( (wpp < wap) && (wpm > wam) ) {
                       return true;
                   }
                   else {
                       JInternalFrame frame = new JInternalFrame();
                       JOptionPane.showMessageDialog(frame, "Bp must be contained inside Ba", "Input Error", JOptionPane.ERROR_MESSAGE);
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

    public Double getWpm() {
        try { return Double.parseDouble(textFilterWpm.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public Double getWam() {
        try { return Double.parseDouble(textFilterWam.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public Double getWpp() {
        try { return Double.parseDouble(textFilterWpp.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public Double getWap() {
        try { return Double.parseDouble(textFilterWap.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public void setTextBoxes(double wpm, double wam, double wpp, double wap){
        textFilterWpm.setText(String.valueOf(wpm));
        textFilterWam.setText(String.valueOf(wam));
        textFilterWpp.setText(String.valueOf(wpp));
        textFilterWap.setText(String.valueOf(wap));
    }
}