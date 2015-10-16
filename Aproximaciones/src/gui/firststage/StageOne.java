package gui.firststage;

import data.UserData;
import gui.firststage.configurationpanel.PanelConfig;
import gui.firststage.drawingpanel.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class StageOne extends JPanel {
    public static UserData ud = new UserData();
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
