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
    private StageConfigPanel stageconfigpanel = null;
    private ComponentsPanel componentspanel= null;
    private SensitivitiesPanel sensitivitiespanel= null;
    private StagePanel stagepanel= null;
    private PlotPanel plotpanel= null;
    private StageInfoPanel stageinfopanel= null;
    
    public StageInfoPanel getStageInfoPanel() {
        if (stageinfopanel == null)
        	stageinfopanel = new StageInfoPanel();
        return stageinfopanel;
    }
    
    public PlotPanel getPlotPanel() {
        if (plotpanel == null)
        	plotpanel = new PlotPanel();
        return plotpanel;
    }
    
    public StagePanel getStagePanel() {
        if (stagepanel == null)
        	stagepanel = new StagePanel();
        return stagepanel;
    }
    
    public SensitivitiesPanel getSensitivitiesPanel() {
        if (sensitivitiespanel == null)
        	sensitivitiespanel = new SensitivitiesPanel();
        return sensitivitiespanel;
    }
    
    public ComponentsPanel getComponentsPanel() {
        if (componentspanel == null)
        	componentspanel = new ComponentsPanel();
        return componentspanel;
    }
    
    public StageConfigPanel getStageConfigPanel() {
        if (stageconfigpanel == null)
        	stageconfigpanel = new StageConfigPanel();
        return stageconfigpanel;
    }
    
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
