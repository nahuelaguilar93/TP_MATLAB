package firststage;

import javax.swing.*;
import java.awt.*;

/*
*   Stage One is divided into two different big panels. One with all the configuration buttons and panels and another with the plotA
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

        for(int i=0; i<20; i++){
            JInternalFrame frame = new JInternalFrame();
            Dimension desktopSize = this.getSize();
            Dimension jInternalFrameSize = frame.getSize();
            frame.setLocation((int)((desktopSize.width - jInternalFrameSize.width) * Math.random()),
                    (int)((desktopSize.height - jInternalFrameSize.height) * Math.random()));
            JOptionPane.showMessageDialog(frame, "AGUSTÍN PUTO DE MIERDAAAA!!", "FORRO", JOptionPane.ERROR_MESSAGE);

        }

    }
}