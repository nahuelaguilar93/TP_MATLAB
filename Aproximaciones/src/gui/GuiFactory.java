package gui;

import gui.firststage.drawingpanel.PlotPlot;
import gui.firststage.StageOne;
import gui.secondstage.StageTwo;
import gui.thirdstage.StageThree;

public class GuiFactory {

    public static int APP_WIDTH = 1024;
    public static int APP_HEIGHT = 768;



    private static StageOne stageOne = null;
    private static StageTwo stageTwo = null;
    private static StageThree stageThree = null;
    private static PlotPlot plot = null;

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

    public static PlotPlot getPlot() {
        if (plot == null) {
            plot = new PlotPlot();
        }
        return plot;
    }
}
