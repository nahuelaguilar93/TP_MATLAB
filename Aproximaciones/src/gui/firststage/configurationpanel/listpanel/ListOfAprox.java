package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListOfAprox extends JPanel{

    public ListOfAprox () {
        String listData[] = {};
        //Configure listpanel
        JList listOfAprox = new JList(listData);
        listOfAprox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //You can only choose one option
        listOfAprox.setLayoutOrientation(JList.VERTICAL);
        listOfAprox.setVisibleRowCount(5); //Acá va la máxima cantidad de nombres que tira sin el scroller. Si se pone -1 trata de poner todas las que entren.
        JScrollPane listScroller = new JScrollPane(listOfAprox);

        this.add(listScroller);
    }
}
