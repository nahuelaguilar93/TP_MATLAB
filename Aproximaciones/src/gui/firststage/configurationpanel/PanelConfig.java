package gui.firststage.configurationpanel;

import gui.firststage.StageOne;
import gui.firststage.configurationpanel.approximationpanel.AproximationPanel;
import gui.firststage.configurationpanel.filterselectpanel.FilterSelectPanel;
import gui.firststage.configurationpanel.listpanel.ListPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class PanelConfig extends JPanel {
    private ListPanel panelList = new ListPanel();
    private AproximationPanel panelAprox = new AproximationPanel(this.panelList.getListOfAprox());
    private FilterSelectPanel panelFiltro = new FilterSelectPanel(this.panelAprox.getComboBoxAprox());

    public PanelConfig() {
        //Configuration Panel
        this.setMaximumSize(new Dimension(300, 2080));
        //this.setMinimumSize(new Dimension(300, 768));
        //this.setPreferredSize(new Dimension(300, 768));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new GridLayout(4,1));
        //panelConfig.setBorder(BorderFactory.createTitledBorder("Configuration"));

        this.add(panelFiltro);
        this.add(panelAprox);
        this.add(panelList);
    }
}