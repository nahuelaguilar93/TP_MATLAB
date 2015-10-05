package gui;

/**
 * Created by kdewald on 23/09/15.
 */
public class GuiFactory {

    private static StageOne stageOne = null;
    private static StageTwo stageTwo = null;
    private static StageThree stageThree = null;

    public static StageOne getStageOne() {
        if (stageOne == null) {
            stageOne = new StageOne();
        }
        return stageOne;
    }

    public static StageTwo getStageTwo() {
        if (stageTwo == null) {
            stageTwo = new StageTwo();
        }
        return stageTwo;
    }

    public static StageThree getStageThree() {
        if (stageThree == null)
            stageThree = new StageThree();
        return stageThree;
    }
}
