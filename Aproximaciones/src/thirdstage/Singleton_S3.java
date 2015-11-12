package thirdstage;

import secondstage.PlotPoleZeroPanel;
import secondstage.PoleZeroListsPanel;
import secondstage.StageProperties;

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
    private TopologyConfigPanel stageconfigpanel = null;
    private ComponentsPanel componentspanel= null;
    private SensitivitiesPanel sensitivitiespanel= null;
    private StagePanel stagepanel= null;
    private PlotPanel plotpanel= null;
    private PoleZeroListsPanel poleZeroListsPanel = null;
    private PlotPoleZeroPanel plotPoleZeroPanel = null;
    private StageProperties stageProperties = null;
    private ScaledImageLabel scaledImageLabel = null;

    public ScaledImageLabel getScaledImageLabel() {
        if (scaledImageLabel == null)
            scaledImageLabel = new ScaledImageLabel();
        return scaledImageLabel;
    }
    public PoleZeroListsPanel getPoleZeroListsPanel() {
        if (poleZeroListsPanel == null)
            poleZeroListsPanel = new PoleZeroListsPanel();
        return poleZeroListsPanel;
    }
    public PlotPoleZeroPanel getPlotPoleZeroPanel(){
        if (plotPoleZeroPanel == null)
            plotPoleZeroPanel = new PlotPoleZeroPanel();
        return plotPoleZeroPanel;
    }
    public StageProperties getStageProperties() {
        if ( stageProperties == null)
            stageProperties = new StageProperties();
        return stageProperties;
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
    public TopologyConfigPanel getTopologyConfigPanel() {
        if (stageconfigpanel == null)
        	stageconfigpanel = new TopologyConfigPanel();
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
