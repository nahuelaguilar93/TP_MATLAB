package firststage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class ConfigPanel extends JPanel {
    private ListPanel listPanel;
    private ApproximationPanel approximationPanel;
    private ConfigTemplatePanel configTemplatePanel;

    public ConfigPanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        listPanel = s.getListPanel();
        approximationPanel = s.getApproximationPanel();
        configTemplatePanel = s.getConfigTemplatePanel();

        //Configuration Panel
        this.setMaximumSize(new Dimension(300, 2080));
        //this.setMinimumSize(new Dimension(300, 768));
        //this.setPreferredSize(new Dimension(300, 768));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new GridLayout(4,1));
        //configPanel.setBorder(BorderFactory.createTitledBorder("Configuration"));

        this.add(configTemplatePanel);
        this.add(approximationPanel);
        this.add(listPanel);
    }
}