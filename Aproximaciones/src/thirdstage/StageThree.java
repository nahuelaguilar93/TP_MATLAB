package thirdstage;



import javax.swing.*;
import java.awt.*;

public class StageThree extends JPanel {
    StagePropertiesPanel stagePropertiesPanel;
    StagesSelectionPanel stagesSelectionPanel;

    public StageThree() {
        Singleton_S3 s = Singleton_S3.getInstance();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.RED);

        stagePropertiesPanel = s.getStagePropertiesPanel();
        stagesSelectionPanel = s.getStagesSelectionPanel();

        this.add(stagesSelectionPanel);
        this.add(stagePropertiesPanel);
    }
}