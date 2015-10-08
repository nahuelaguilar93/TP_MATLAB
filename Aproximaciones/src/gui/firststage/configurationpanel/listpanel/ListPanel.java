package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListPanel extends JPanel{
    private static ListOfAprox listOfAprox = null;
    private static SliderDesnorm sliderDesnorm = null;

    public ListPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Aproximation List"));

        this.add(ListPanel.getListOfAprox());
        this.add(ListPanel.getSliderDesnorm());
    }

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
