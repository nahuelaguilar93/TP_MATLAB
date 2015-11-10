package secondstage;

import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

class Singleton_S2 {
    private static final Singleton_S2 INSTANCE = new Singleton_S2();
    private Singleton_S2() {}
    public static Singleton_S2 getInstance() {
        return INSTANCE;
    }

    private List<Complex> poleArray = new ArrayList<>();
    private List<Complex> zeroArray = new ArrayList<>();
    private StagePanel stagePanel = null;
    private PoleZeroPanel poleZeroPanel = null;
    private PlotPoleZeroPanel plotPoleZeroPanel = null;
    private PoleZeroListsPanel poleZeroListsPanel = null;
    private PlotStagePanel plotStagePanel = null;
    private StagePlotModePanel stagePlotModePanel = null;
    private StageVisualizerPanel stageVisualizerPanel = null;
    private StageProperties stageProperties = null;

    public StageVisualizerPanel getStageVisualizerPanel() {
        if (stageVisualizerPanel == null)
            stageVisualizerPanel = new StageVisualizerPanel();
        return stageVisualizerPanel;
    }
    public StageProperties getStageProperties() {
        if (stageProperties == null)
            stageProperties = new StageProperties();
        return stageProperties;
    }
    public StagePlotModePanel getstagePlotModePanel() {
        if (stagePlotModePanel == null)
            stagePlotModePanel = new StagePlotModePanel();
        return stagePlotModePanel;
    }
    public PlotStagePanel getPlotStagePanel() {
        if (plotStagePanel == null)
            plotStagePanel = new PlotStagePanel();
        return plotStagePanel;
    }
    public StagePanel getStagePanel() {
        if (stagePanel == null)
            stagePanel = new StagePanel();
        return stagePanel;
    }
    public PoleZeroPanel getPoleZeroPanel() {
        if (poleZeroPanel == null)
            poleZeroPanel = new PoleZeroPanel();
        return poleZeroPanel;
    }
    public PlotPoleZeroPanel getPlotPoleZeroPanel() {
        if (plotPoleZeroPanel == null)
            plotPoleZeroPanel = new PlotPoleZeroPanel();
        return plotPoleZeroPanel;
    }
    public PoleZeroListsPanel getPoleZeroListsPanel() {
        if (poleZeroListsPanel == null)
            poleZeroListsPanel = new PoleZeroListsPanel();
        return poleZeroListsPanel;
    }
    public List<Complex> getPoleArray() {
        if (poleArray == null)
            poleArray = new ArrayList<>();
        return poleArray;
    }
    public List<Complex> getZeroArray() {
        if (zeroArray == null)
            zeroArray = new ArrayList<>();
        return zeroArray;
    }
}
