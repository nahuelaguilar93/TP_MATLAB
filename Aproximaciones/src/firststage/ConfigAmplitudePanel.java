package firststage;

import javax.swing.*;
import java.awt.*;

class ConfigAmplitudePanel extends JPanel {
    private final int TEXT_HEIGHT = 40;
    private final int TEXT_WIDTH = 70;
    private JTextField textFilterAp = new JTextField();
    private JTextField textFilterAa = new JTextField();
    private JTextField textFilterG = new JTextField();

    public ConfigAmplitudePanel() {
        textFilterAp.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterAa.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterG.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        JLabel labelAp = new JLabel("Ap:");
        JLabel labelAa = new JLabel("Aa:");
        JLabel labelG = new JLabel("Gain:");

//        this.setBackground(Color.ORANGE);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(labelAp)
                        .addComponent(labelAa)
                        .addComponent(labelG))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFilterAp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterAa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFilterG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAp)
                        .addComponent(textFilterAp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAa)
                        .addComponent(textFilterAa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelG)
                        .addComponent(textFilterG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
    public boolean isParsable(){
        try {
            double ap = Double.parseDouble(textFilterAp.getText());
            double aa = Double.parseDouble(textFilterAa.getText());
            double g = Double.parseDouble(textFilterG.getText());
            if(ap < aa) return true;
            else {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Aa must be grater than Ap.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "G, Ap and Aa must be real positive numbers. (G not necessarily positive)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public double getAp() {
        try { return Double.parseDouble(textFilterAp.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public double getAa() {
        try { return Double.parseDouble(textFilterAa.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public double getG() {
        try { return Double.parseDouble(textFilterG.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
}