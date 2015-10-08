package gui.firststage.Config.Aprox;

/**
 * Created by NEGU on 7/10/2015.
 */
public class AproxFactory {
    private static ComboBoxAprox comboBoxAprox = null;
    private static ButtonAprox buttonAprox = null;
    private static CheckBoxAprox checkBoxAprox = null;

    public static ComboBoxAprox getComboBox(){
        if (comboBoxAprox == null) {
            comboBoxAprox = new ComboBoxAprox();
        }
        return comboBoxAprox;
    }
    public static ButtonAprox getButtonAprox(){
        if (buttonAprox == null) {
            buttonAprox = new ButtonAprox();
        }
        return buttonAprox;
    }
    public static CheckBoxAprox getCheckBoxAprox() {
        if (checkBoxAprox == null) {
            checkBoxAprox = new CheckBoxAprox();
        }
        return checkBoxAprox;
    }
}
