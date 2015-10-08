package gui.firststage;

import gui.firststage.configurationpanel.PanelConfig;
import gui.firststage.drawingpanel.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class StageOne extends JPanel {
    PanelConfig panelConfig;
    DrawingPanel drawingPanel;

    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);

        //Configuration Panel
        panelConfig = new PanelConfig();
        this.add(panelConfig);

        //Plot Panel
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
    }
}
