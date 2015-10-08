package gui.FirstStage.Config.List;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListFactory {
    private static ListOfAprox listOfAprox = null;
    private static SliderDesnorm sliderDesnorm = null;

    public static ListOfAprox getListOfAprox() {
        if (listOfAprox == null) {
            listOfAprox = new ListOfAprox();
        }
        return listOfAprox;
    }
    public static SliderDesnorm getSliderDesnorm() {
        if (sliderDesnorm == null) {
            sliderDesnorm = new SliderDesnorm();
        }
        return sliderDesnorm;
    }
}
