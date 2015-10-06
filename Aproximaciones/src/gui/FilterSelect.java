package gui;

import javax.swing.*;
import javax.swing.JButton;

/**
 * Created by NEGU on 6/10/2015.
 */
public class FilterSelect {
    private static ButtonFilterType buttonFilterType= null;

    public static ButtonFilterType getButtons() {
        if (buttonFilterType == null) {
            buttonFilterType = new ButtonFilterType();
        }
        return buttonFilterType;
    }
}
