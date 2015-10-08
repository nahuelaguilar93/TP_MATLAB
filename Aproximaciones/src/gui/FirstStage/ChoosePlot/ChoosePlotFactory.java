package gui.FirstStage.ChoosePlot;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ChoosePlotFactory {
    private static CheckBoxChoosePlot checkBoxChoosePlot = null;

    public static CheckBoxChoosePlot getCheckBoxChoosePlot() {
        if (checkBoxChoosePlot == null) {
            checkBoxChoosePlot = new CheckBoxChoosePlot();
        }
        return checkBoxChoosePlot;
    }
}
