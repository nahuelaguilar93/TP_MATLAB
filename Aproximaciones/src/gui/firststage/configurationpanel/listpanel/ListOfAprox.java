package gui.firststage.configurationpanel.listpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by NEGU on 7/10/2015.
 */
public class ListOfAprox extends JPanel{
    DefaultListModel listModel = new DefaultListModel();
    //Configure listpanel
    JList listOfAprox = new JList(listModel);

    public ListOfAprox () {
        listOfAprox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //You can only choose one option
        listOfAprox.setLayoutOrientation(JList.VERTICAL);
        listOfAprox.setVisibleRowCount(5); //Acá va la máxima cantidad de nombres que tira sin el scroller. Si se pone -1 trata de poner todas las que entren.
        JScrollPane listScroller = new JScrollPane(listOfAprox);

        this.add(listScroller);
    }

    public void AddToList(String aproximationToAdd) { listModel.insertElementAt(aproximationToAdd, 0); }
    public void DeleteElementFromList (int index) {
        listModel.remove(index);
        //ToDo: Acá hay que hacer botones para eleminar elementos. Una buena practica sería no dejar eliminar si no hay elementos.
    }
}