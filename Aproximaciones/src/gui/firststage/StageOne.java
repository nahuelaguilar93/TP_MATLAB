package gui.firststage;

import gui.firststage.configurationpanel.ConfigPanel;
import gui.firststage.drawingpanel.DrawingPanel;

import javax.swing.*;
import java.awt.*;

/*
*   Stage One is divided into two different big panels. One with all the configuration buttons and panels and another with the plot
 */

public class StageOne extends JPanel {
    public ConfigPanel configPanel;
    public DrawingPanel drawingPanel;

    public StageOne() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.ORANGE);   //No se debería ver. Lo hago para darme cuenta si me queda corto algún panel o siempre lo cubro

        //Configuration Panel
        configPanel = new ConfigPanel();
        this.add(configPanel);

        //Plot Panel
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
    }
}