package gui.firststage.Config;

import gui.firststage.Config.Aprox.AproxFactory;
import gui.firststage.Config.List.ListFactory;
import gui.firststage.Config.Filter.FilterSelect;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PanelConfig extends JPanel {

    private static JPanel panelFiltro = null;
    private static JPanel panelAprox = null;
    private static JPanel panelList = null;

    public static JPanel getPanelFiltro() {
        if (panelFiltro == null) {
            //First Panel (Filter)
            panelFiltro = new JPanel(); //Panel where you select and configure the Type of Filter (BP, LP, etc)
            panelFiltro.setLayout(new BoxLayout(panelFiltro, BoxLayout.Y_AXIS));
            panelFiltro.setBorder(BorderFactory.createTitledBorder("Filter Configuration"));

            panelFiltro.add(FilterSelect.getComboBox());
            panelFiltro.add(FilterSelect.getTextFieldFilterType());
            panelFiltro.add(FilterSelect.getButtons());
        }
        return panelFiltro;
    }

    public static JPanel getPanelAprox() {
        if (panelAprox == null) {
            //Second Panel (Aprox)
            panelAprox = new JPanel(); //Panel to configure the Aproximation
            panelAprox.setLayout(new BoxLayout(panelAprox, BoxLayout.Y_AXIS));
            panelAprox.setBorder(BorderFactory.createTitledBorder("Aprox Configuration"));

            panelAprox.add(AproxFactory.getComboBox());
            panelAprox.add(AproxFactory.getCheckBoxAprox());
            panelAprox.add(AproxFactory.getButtonAprox());
        }
        return panelAprox;
    }

    public static JPanel getPanelList() {
        if (panelList == null) {
            //Third Panel (ListAprox)
            panelList = new JPanel();
            panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
            panelList.setBorder(BorderFactory.createTitledBorder("Aproximation List"));

            panelList.add(ListFactory.getListOfAprox());
            panelList.add(ListFactory.getSliderDesnorm());
        }
        return panelList;
    }
}
