package gui.firststage.configurationpanel.filterselectpanel;

import javax.swing.*;

/**
 * Created by NEGU on 8/10/2015.
 */
public class FilterSelectPanel extends JPanel{
    private static ComboBoxFilterType comboBoxFilterType = new ComboBoxFilterType();
    private static ButtonFilterType buttonFilterType = new ButtonFilterType();
    private static TextFieldFilterType textFieldFilterType = new TextFieldFilterType();

    public FilterSelectPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Filter Configuration"));

        this.add(comboBoxFilterType);
        this.add(textFieldFilterType);
        this.add(buttonFilterType);
    }
}
