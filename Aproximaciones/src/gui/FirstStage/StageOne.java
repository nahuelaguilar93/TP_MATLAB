package gui.FirstStage;

import gui.FirstStage.Config.PanelConfig;
import Data.UserData;

import javax.swing.*;
import java.awt.*;

public class StageOne extends JPanel {
    JPanel panelConfig;
    JPanel panel2;
    UserData ud = null;

    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);

        //Configuration Panel
        panelConfig = new JPanel();
        panelConfig.setMaximumSize(new Dimension(300, 1080));
        panelConfig.setMinimumSize(new Dimension(300, 768));
        panelConfig.setPreferredSize(new Dimension(300, 768));

        panelConfig.setLayout(new BoxLayout(panelConfig, BoxLayout.Y_AXIS));
        panelConfig.setBorder(BorderFactory.createTitledBorder("Configuration"));

        panelConfig.add(PanelConfig.getpanelFiltro());
        panelConfig.add(PanelConfig.getPanelAprox());
        panelConfig.add(PanelConfig.getPanelList());

        //Plot Panel
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        //panel2.setMaximumSize(new Dimension(724, 768));
        //panel2.setMinimumSize(new Dimension(724, 768));
        panel2.setPreferredSize(new Dimension(724, 768));

        panel2.add(Panel2.getPlotPanel());
        panel2.add(Panel2.getChoosePlotPanel());

        this.add(panelConfig);
        this.add(panel2);
    }
}
