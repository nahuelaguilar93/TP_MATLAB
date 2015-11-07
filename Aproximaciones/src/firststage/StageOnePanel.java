package firststage;

import javax.swing.*;
import java.awt.*;

/*
*   Stage One is divided into two different big panels. One with all the configuration buttons and panels and another with the plot
*/

public class StageOnePanel extends JPanel {
    private ConfigPanel configPanel;
    private DrawingPanel drawingPanel;

    public StageOnePanel() {
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