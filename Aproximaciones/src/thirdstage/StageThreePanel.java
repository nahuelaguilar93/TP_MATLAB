package thirdstage;



import javax.swing.*;
import java.awt.*;

public class StageThreePanel extends JPanel {
    private StagePropertiesPanel stagePropertiesPanel;
    private StagesSelectionPanel stagesSelectionPanel;
    private Singleton_S3 s = Singleton_S3.getInstance();

    public StageThreePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        stagePropertiesPanel = s.getStagePropertiesPanel();
        stagesSelectionPanel = s.getStagesSelectionPanel();

        this.add(stagesSelectionPanel);
        this.add(stagePropertiesPanel);
    }

    public void set(){
        s.getStagePanel().updateList();
        Singleton_S3.getInstance().getPlotPoleZeroPanel().updatePoleZeroPlot();
        Singleton_S3.getInstance().getPlotPoleZeroPanel().updatePoleZeroColour();
    }
}