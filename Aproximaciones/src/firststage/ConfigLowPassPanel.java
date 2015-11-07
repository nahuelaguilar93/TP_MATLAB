package firststage;

import javax.swing.*;
import java.awt.*;

class ConfigLowPassPanel extends JPanel {
    protected final int TEXT_HEIGHT = 34;
    protected final int TEXT_WIDTH = 70;
    protected JTextField textFilterWp = new JTextField();
    protected JTextField textFilterWa = new JTextField();

    public ConfigLowPassPanel() {
        textFilterWp.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWa.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        JLabel labelWp = new JLabel("Wp:");
        JLabel labelWa = new JLabel("Wa:");
        JLabel units = new JLabel("rad/seg");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelWp)
                        .addComponent(labelWa))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFilterWa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterWp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWp)
                        .addComponent(textFilterWp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWa)
                        .addComponent(textFilterWa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
    public boolean isParsable(){
        try {
            double wp = Double.parseDouble(textFilterWp.getText());
            double wa = Double.parseDouble(textFilterWa.getText());
            if(wp < wa) return true;
            else {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Wa must be grater than Wp.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Wp and Wa must be real positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public Double getWp() {
        try { return Double.parseDouble(textFilterWp.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public Double getWa() {
        try { return Double.parseDouble(textFilterWa.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public void setTextBoxes(double wp, double wa){
        textFilterWp.setText(String.valueOf(wp));
        textFilterWa.setText(String.valueOf(wa));
    }
}