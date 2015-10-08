package gui.firststage;

import gui.firststage.Config.PanelConfig;
import data.UserData;

import javax.swing.*;
import java.awt.*;

public class StageOne extends JPanel {
    JPanel panelConfig;
    JPanel drawingPanel;
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

        panelConfig.add(PanelConfig.getPanelFiltro());
        panelConfig.add(PanelConfig.getPanelAprox());
        panelConfig.add(PanelConfig.getPanelList());

        //Plot Panel
        drawingPanel = new JPanel();
        drawingPanel.setLayout(new BoxLayout(drawingPanel, BoxLayout.Y_AXIS));
        drawingPanel.setMaximumSize(new Dimension(724, 768));
        drawingPanel.setMinimumSize(new Dimension(724, 768));
        drawingPanel.setPreferredSize(new Dimension(724, 768));

        drawingPanel.add(Panel2.getPlotPanel());
        drawingPanel.add(Panel2.getChoosePlotPanel());

        this.add(panelConfig);
        this.add(drawingPanel);
    }
}
