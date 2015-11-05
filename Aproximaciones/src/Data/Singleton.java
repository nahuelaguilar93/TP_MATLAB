package Data;

import firststage.drawingpanel.PlotPlot;
import firststage.StageOnePanel;
import secondstage.StageTwo;
import thirdstage.StageThree;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return INSTANCE;
    }

    private UserData userData = null;
    private StageOnePanel stageOnePanel = null;
    private StageTwo stageTwo = null;
    private StageThree stageThree = null;
    private PlotPlot plot = null;

    public UserData getUserData() {
        if (userData == null)
            userData = new UserData();
        return userData;
    }
    public StageOnePanel getStageOnePanel() {
        if (stageOnePanel == null)
            stageOnePanel = new StageOnePanel();
        return stageOnePanel;
    }
    public StageTwo getStageTwo() {
        if (stageTwo == null)
            stageTwo = new StageTwo();
        return stageTwo;
    }
    public StageThree getStageThree() {
        if (stageThree == null)
            stageThree = new StageThree();
        return stageThree;
    }
}
