package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.util.Hashtable;

/**
 * Created by NEGU on 7/10/2015.
 */
public class SliderDesnorm extends JPanel {
    JSlider sliderDesnorm = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    NumberFormatter formatter = new NumberFormatter();
    JFormattedTextField textField;
    Hashtable labelTable = new Hashtable();

    public SliderDesnorm(){
        sliderDesnorm.setPaintTicks(true);
        sliderDesnorm.setMajorTickSpacing(10);
        //Sets the numbers displayed
        labelTable.put(0, new JLabel("0%"));
        labelTable.put(100, new JLabel("100%"));
        labelTable.put(50, new JLabel("50%"));
        sliderDesnorm.setLabelTable(labelTable);
        sliderDesnorm.setPaintLabels(true);
        // Formats the max and min value of the Slider
        formatter.setMaximum(100);
        formatter.setMinimum(0);
        //Makes an editable Text Field
        textField = new JFormattedTextField(formatter);
        textField.setValue(0);
        textField.setColumns(5);

        this.add(sliderDesnorm);
        this.add(textField);
    }
}
