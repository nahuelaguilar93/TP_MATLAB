package gui.FirstStage.Plot;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PlotFactory {
    private static PlotPlot plot = null;

    public static PlotPlot getPlot() {
        if (plot == null) {
            plot = new PlotPlot();
        }
        return plot;
    }
}
