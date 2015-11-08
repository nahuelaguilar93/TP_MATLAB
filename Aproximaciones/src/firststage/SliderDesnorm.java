package firststage;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Arc2D;
import java.text.ParseException;
import java.util.Hashtable;

class SliderDesnorm extends JPanel{
    private JSlider sliderDesnorm = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
    //private NumberFormatter formatter = new NumberFormatter();
//    JFormattedTextField textField;
    Hashtable labelTable = new Hashtable();

    public SliderDesnorm(){
        sliderDesnorm.setPaintTicks(true);
        sliderDesnorm.setMajorTickSpacing(2);
        sliderDesnorm.setMinorTickSpacing(1);

        //Sets the numbers displayed
        labelTable.put(0, new JLabel("0%"));
        labelTable.put(10, new JLabel("50%"));
        labelTable.put(20, new JLabel("100%"));
        sliderDesnorm.setLabelTable(labelTable);
        sliderDesnorm.setPaintLabels(true);

//        formatter.setMaximum(100);  //Formats the max and min value of the TextField
//        formatter.setMinimum(0);

        /*textField = new JFormattedTextField(formatter); //Makes an editable Text Field
        textField.setValue(0);
        textField.setColumns(5);    //Get some space*/
/*
        textField.addPropertyChangeListener(this);

       /* //Here I create a KeyBinding
        textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "check");
        textField.getActionMap().put("check", AbstractAction);
*/
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(sliderDesnorm);
//        this.add(textField);
    }

    public double getSliderValue() {
        return ((double)sliderDesnorm.getValue())/20;
    }

    //TODO: QUE MIERDA ES ESTO?
/*    abstract class AbstractAction extends SliderDesnorm{

        public void acionPerformed(ActionEvent e) {
            if (!textField.isEditValid()) {     //The text is invalid
                Toolkit.getDefaultToolkit().beep();
                textField.selectAll();
            }
            else {
                try {
                    textField.commitEdit();
                } catch (ParseException exc) {}
            }
        }
    }*/
}

