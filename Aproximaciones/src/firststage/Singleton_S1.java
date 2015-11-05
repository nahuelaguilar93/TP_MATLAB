package firststage;

/**
 * Created by Nahuel on 3/11/2015.
 */
class Singleton_S1 {
    private static final Singleton_S1 INSTANCE = new Singleton_S1();
    private Singleton_S1() {}
    public static Singleton_S1 getInstance() {
        return INSTANCE;
    }

    private ApproxButton approxButton = null;
    private ApproxComboBox approxComboBox = null;
    private ApproximationPanel approximationPanel = null;
    private ApproxList approxList = null;
    private ApproxRadioButton approxRadioButton = null;
    private ConfigAmplitudePanel configAmplitudePanel = null;
    private ConfigBandPassPanel configBandPassPanel = null;
    private ConfigBandRejectPanel configBandRejectPanel = null;
    private ConfigDelayPanel configDelayPanel = null;
    private ConfigHighPassPanel configHighPassPanel = null;
    private ConfigLowPassPanel configLowPassPanel = null;
    private ConfigPanel configPanel = null;
    private ConfigTemplatePanel configTemplatePanel = null;
    private ListPanel listPanel = null;
    private ListButtonsPanel listButtonsPanel = null;

    public ListButtonsPanel getListButtonsPanel() {
        if (listButtonsPanel == null)
            listButtonsPanel = new ListButtonsPanel();
        return listButtonsPanel;
    }
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
    public ApproxList getApproxList() {
        if (approxList == null)
            approxList = new ApproxList();
        return approxList;
    }
    public ApproxRadioButton getApproxRadioButton() {
        if (approxRadioButton == null)
            approxRadioButton = new ApproxRadioButton();
        return approxRadioButton;
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
    public ListPanel getListPanel() {
        if (listPanel == null)
            listPanel = new ListPanel();
        return listPanel;
    }

}