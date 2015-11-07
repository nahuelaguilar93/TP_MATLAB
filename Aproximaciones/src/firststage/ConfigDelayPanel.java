package firststage;

import javax.swing.*;
import java.awt.*;

class ConfigDelayPanel extends JPanel {
    protected final int TEXT_HEIGHT = 24;
    protected final int TEXT_WIDTH = 70;
    private JTextField textFilterWp = new JTextField();
    private JTextField textFilterWa = new JTextField();
    private JTextField textFilterDelay = new JTextField();
    private JTextField textFilterPsi = new JTextField();

    //TODO: Todavía no tengo idea como se hace un filtro de Delay, Yo lo dejaría para el final..
    public ConfigDelayPanel() {
        textFilterWp.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWa.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterDelay.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterPsi.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        JLabel labelWp = new JLabel("Wp:");
        JLabel labelWa = new JLabel("Wa:");
        JLabel labelDelay = new JLabel("T(ms):");
        JLabel labelPsi = new JLabel("\u03C8:"); //Psi


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(labelWp)
                                .addComponent(labelWa)
                                .addComponent(labelDelay)
                                .addComponent(labelPsi))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(textFilterWa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFilterWp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFilterDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFilterPsi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelWp)
                                .addComponent(textFilterWp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelWa)
                                .addComponent(textFilterWa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelDelay)
                                .addComponent(textFilterDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPsi)
                                .addComponent(textFilterPsi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
    public boolean isParsable(){
        try {
            double wp = Double.parseDouble(textFilterWp.getText());
            double wa = Double.parseDouble(textFilterWa.getText());
            double delay = Double.parseDouble(textFilterDelay.getText());
            double psi = Double.parseDouble(textFilterPsi.getText());
            if(wp > 0 && wa > 0 && delay > 0 && psi > 0) {
                if (wp < wa) return true;
                else {
                    JInternalFrame frame = new JInternalFrame();
                    JOptionPane.showMessageDialog(frame, "Wa must be grater than Wp.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JInternalFrame frame = new JInternalFrame();
                JOptionPane.showMessageDialog(frame, "Wp, Wa, T and psi must be positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JInternalFrame frame = new JInternalFrame();
            JOptionPane.showMessageDialog(frame, "Wp, Wa, T and psi must be real positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
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
    public Double getDelay() {
        try { return Double.parseDouble(textFilterDelay.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public Double getPsi() {
        try { return Double.parseDouble(textFilterPsi.getText()); }
        catch (NumberFormatException e) { return 0.; }
    }
    public void setTextBoxes(double wp, double wa, double delay, double psi){
        textFilterWp.setText(String.valueOf(wp));
        textFilterWa.setText(String.valueOf(wa));
        textFilterDelay.setText(String.valueOf(delay));
        textFilterPsi.setText(String.valueOf(psi));
    }
}