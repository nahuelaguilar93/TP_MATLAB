package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListPanel extends JPanel{
    private ListOfAprox listOfAprox = new ListOfAprox();
    private SliderDesnorm sliderDesnorm = new SliderDesnorm();

    public ListPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Aproximation List"));

        this.add(listOfAprox);
        this.add(sliderDesnorm);
    }

    //public void addItemToList(String aproximationToAdd) { listOfAprox.AddToList(aproximationToAdd); }
    public ListOfAprox getListOfAprox() { return listOfAprox; }
    public SliderDesnorm getSliderDesnorm() { return sliderDesnorm; }
}
