package firststage;

class Singleton_S1 {
    private static final Singleton_S1 INSTANCE = new Singleton_S1();
    private Singleton_S1() {}
    public static Singleton_S1 getInstance() {
        return INSTANCE;
    }

    private ApproxButton approxButton = null;
    private ApproxComboBox approxComboBox = null;
    private ApproximationPanel approximationPanel = null;
    private ApproxRadioButton approxRadioButton = null;
    private CheckBoxChoosePlot checkBoxChoosePlot = null;
    private ConfigAmplitudePanel configAmplitudePanel = null;
    private ConfigBandPassPanel configBandPassPanel = null;
    private ConfigBandRejectPanel configBandRejectPanel = null;
    private ConfigDelayPanel configDelayPanel = null;
    private ConfigHighPassPanel configHighPassPanel = null;
    private ConfigLowPassPanel configLowPassPanel = null;
    private ConfigPanel configPanel = null;
    private ConfigTemplatePanel configTemplatePanel = null;
    private DrawingPanel drawingPanel = null;
    private FilterList filterList = null;
    private ListButtonsPanel listButtonsPanel = null;
    private ListPanel listPanel = null;
    private PlotPlot plotPlot = null;
    private SliderDesnorm sliderDesnorm = null;

    public ApproxButton getApproxButton() {
        if (approxButton == null)
            approxButton = new ApproxButton();
        return approxButton;
    }
    public ApproxComboBox getApproxComboBox() {
        if (approxComboBox == null)
            approxComboBox = new ApproxComboBox();
        return approxComboBox;
    }
    public ApproximationPanel getApproximationPanel() {
        if (approximationPanel == null)
            approximationPanel = new ApproximationPanel();
        return approximationPanel;
    }
    public ApproxRadioButton getApproxRadioButton() {
        if (approxRadioButton == null)
            approxRadioButton = new ApproxRadioButton();
        return approxRadioButton;
    }
    public CheckBoxChoosePlot getCheckBoxChoosePlot() {
        if (checkBoxChoosePlot == null)
            checkBoxChoosePlot = new CheckBoxChoosePlot();
        return checkBoxChoosePlot;
    }
    public ConfigAmplitudePanel getConfigAmplitudePanel() {
        if (configAmplitudePanel == null)
            configAmplitudePanel = new ConfigAmplitudePanel();
        return configAmplitudePanel;
    }
    public ConfigBandPassPanel getConfigBandPassPanel() {
        if (configBandPassPanel == null)
            configBandPassPanel = new ConfigBandPassPanel();
        return configBandPassPanel;
    }
    public ConfigBandRejectPanel getConfigBandRejectPanel() {
        if (configBandRejectPanel == null)
            configBandRejectPanel = new ConfigBandRejectPanel();
        return configBandRejectPanel;
    }
    public ConfigDelayPanel getConfigDelayPanel() {
        if (configDelayPanel == null)
            configDelayPanel = new ConfigDelayPanel();
        return configDelayPanel;
    }
    public ConfigHighPassPanel getConfigHighPassPanel() {
        if (configHighPassPanel == null)
            configHighPassPanel = new ConfigHighPassPanel();
        return configHighPassPanel;
    }
    public ConfigLowPassPanel getConfigLowPassPanel() {
        if (configLowPassPanel == null)
            configLowPassPanel = new ConfigLowPassPanel();
        return configLowPassPanel;
    }
    public ConfigPanel getConfigPanel() {
        if (configPanel == null)
            configPanel = new ConfigPanel();
        return configPanel;
    }
    public ConfigTemplatePanel getConfigTemplatePanel() {
        if (configTemplatePanel == null)
            configTemplatePanel = new ConfigTemplatePanel();
        return configTemplatePanel;
    }
    public DrawingPanel getDrawingPanel() {
        if (drawingPanel == null)
            drawingPanel = new DrawingPanel();
        return drawingPanel;
    }
    public FilterList getFilterList() {
        if (filterList == null)
            filterList = new FilterList();
        return filterList;
    }
    public ListButtonsPanel getListButtonsPanel() {
        if (listButtonsPanel == null)
            listButtonsPanel = new ListButtonsPanel();
        return listButtonsPanel;
    }
    public ListPanel getListPanel() {
        if (listPanel == null)
            listPanel = new ListPanel();
        return listPanel;
    }
    public PlotPlot getPlotPlot() {
        if (plotPlot == null)
            plotPlot = new PlotPlot();
        return plotPlot;
    }
    public SliderDesnorm getSliderDesnorm() {
        if (sliderDesnorm == null)
            sliderDesnorm = new SliderDesnorm();
        return sliderDesnorm;
    }
}