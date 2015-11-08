package Data;

import firststage.StageOnePanel;
import secondstage.StageTwoPanel;
import thirdstage.StageThree;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return INSTANCE;
    }

    private UserData userData = null;
    private StageOnePanel stageOnePanel = null;
    private StageTwoPanel stageTwoPanel = null;
    private StageThree stageThree = null;

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
    public StageTwoPanel getStageTwoPanel() {
        if (stageTwoPanel == null)
            stageTwoPanel = new StageTwoPanel();
        return stageTwoPanel;
    }
    public StageThree getStageThree() {
        if (stageThree == null)
            stageThree = new StageThree();
        return stageThree;
    }
}
