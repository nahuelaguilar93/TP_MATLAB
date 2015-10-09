package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListOfAprox extends JPanel{
    DefaultListModel listModel = new DefaultListModel();
    private static int LIST_HEIGHT = 40;
    private static int LIST_WIDTH = 40;

    public ListOfAprox () {
        String listData[] = {"Ejemplo 1", "Ejemplo 2", "Ejemplo 3"};
        //Configure listpanel
        JList listOfAprox = new JList(listData);
        listOfAprox.setLayoutOrientation(JList.VERTICAL);
        listOfAprox.setMaximumSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
        listOfAprox.setMinimumSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
        listOfAprox.setVisibleRowCount(-1);
        listOfAprox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(listOfAprox);

        this.add(listOfAprox);
    }
}
