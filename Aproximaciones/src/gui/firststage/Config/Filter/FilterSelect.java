package gui.firststage.Config.Filter;

/**
 * Created by NEGU on 6/10/2015.
 */
public class FilterSelect {
    private static ButtonFilterType buttonFilterType = null;
    private static ComboBoxFilterType comboBoxFilterType = null;
    private static TextFieldFilterType textFieldFilterType = null;

    public static ButtonFilterType getButtons() {
        if (buttonFilterType == null) {
            buttonFilterType = new ButtonFilterType();
        }
        return buttonFilterType;
    }
    public static ComboBoxFilterType getComboBox(){
        if (comboBoxFilterType == null) {
            comboBoxFilterType = new ComboBoxFilterType();
        }
        return comboBoxFilterType;
    }
    public static TextFieldFilterType getTextFieldFilterType(){
        if (textFieldFilterType == null) {
            textFieldFilterType= new TextFieldFilterType();
        }
        return textFieldFilterType;
    }
}
