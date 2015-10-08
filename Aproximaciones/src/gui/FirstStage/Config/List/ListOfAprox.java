package gui.FirstStage.Config.List;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListOfAprox extends JPanel{
    DefaultListModel listModel = new DefaultListModel();
    JList listOfAprox = new JList();


    public ListOfAprox () {
        //Configure List
        listOfAprox.setLayoutOrientation(JList.VERTICAL);
        listOfAprox.setVisibleRowCount(-1);
        listOfAprox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(listOfAprox);
        listScroller.setPreferredSize(new Dimension(250, 80));



/*      //Adding Elements to the list
        listModel.addElement("Butter Orden 5");
        listModel.addElement("Bessel Orden 15");
        listModel.addElement("Cheby Orden 3");
        listModel.addElement("Cauer Orden 2");

        listOfAprox = listOfAprox(listModel);*/

        this.add(listOfAprox);
    }
}
