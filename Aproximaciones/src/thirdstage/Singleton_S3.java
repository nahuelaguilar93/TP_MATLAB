package thirdstage;

/**
 * Created by NEGU on 10/11/2015.
 */
public class Singleton_S3 {
    private static final Singleton_S3 INSTANCE = new Singleton_S3();
    private Singleton_S3() {}
    public static Singleton_S3 getInstance() { return INSTANCE; }

    private FilterCircuitPanel filterCircuitPanel = null;
    private StagePropertiesPanel stagePropertiesPanel = null;
    private StagesSelectionPanel stagesSelectionPanel = null;

    public FilterCircuitPanel getFilterCircuitPanel() {
        if (filterCircuitPanel == null)
            filterCircuitPanel = new FilterCircuitPanel();
        return filterCircuitPanel;
    }
    public StagePropertiesPanel getStagePropertiesPanel() {
        if (stagePropertiesPanel == null)
            stagePropertiesPanel = new StagePropertiesPanel();
        return stagePropertiesPanel;
    }
    public StagesSelectionPanel getStagesSelectionPanel() {
        if (stagesSelectionPanel == null)
            stagesSelectionPanel = new StagesSelectionPanel();
        return stagesSelectionPanel;
    }
}
