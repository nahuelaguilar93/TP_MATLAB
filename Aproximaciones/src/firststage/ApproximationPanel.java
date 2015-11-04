package firststage;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproximationPanel extends JPanel{
    private ApproxComboBox approxComboBox;
    private ApproxRadioButton approxRadioButton;
    private ApproxButton approxButton;

    public ApproxComboBox getApproxComboBox() {
        return approxComboBox;
    }

    public ApproximationPanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        approxComboBox = s.getApproxComboBox();
        approxRadioButton = s.getApproxRadioButton();
        approxButton = new ApproxButton();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Aproximation Configuration"));

        this.add(approxComboBox);
        this.add(approxRadioButton);
        this.add(approxButton);
    }
}