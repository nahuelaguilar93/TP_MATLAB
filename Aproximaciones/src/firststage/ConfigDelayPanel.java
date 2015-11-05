package firststage;

import javax.swing.*;
import java.awt.*;

class ConfigDelayPanel extends ConfiguratorPanel {
    private final int TEXT_HEIGHT = 40;
    private final int TEXT_WIDTH = 70;
    private JTextField textFilterWp = new JTextField();
    private JTextField textFilterWa = new JTextField();

    //TODO: Todavía no tengo idea como se hace un filtro de Delay, Yo lo dejaría para el final..
    public ConfigDelayPanel() {
        textFilterWp.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        textFilterWa.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
        JLabel labelWp = new JLabel("Wp:");
        JLabel labelWa = new JLabel("Wa:");

        this.setBackground(Color.BLUE);

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
            double wp = Integer.parseInt(textFilterWp.getText());
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
    public String getFilterWpValue() { return textFilterWp.getText(); }
    public String getFilterWaValue() { return textFilterWa.getText(); }
}