package gui.firststage;

import data.UserData;
import gui.firststage.configurationpanel.PanelConfig;
import gui.firststage.drawingpanel.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class StageOne extends JPanel {
    public PanelConfig panelConfig;
    public DrawingPanel drawingPanel;

    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);   //No se debería ver. Lo hago para darme cuenta si me queda corto algún panel o siempre lo cubro

        //Configuration Panel
        panelConfig = new PanelConfig();
        this.add(panelConfig);

        //Plot Panel
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
    }
}