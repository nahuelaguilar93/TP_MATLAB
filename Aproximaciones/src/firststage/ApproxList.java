package firststage;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class ApproxList extends JPanel{
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    //Configure listpanel
    JList<String> approxList = new JList<String>(listModel);

    public ApproxList() {
        approxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //You can only choose one option
        approxList.setLayoutOrientation(JList.VERTICAL);
        approxList.setVisibleRowCount(5); //Acá va la máxima cantidad de nombres que tira sin el scroller. Si se pone -1 trata de poner todas las que entren.
        JScrollPane listScroller = new JScrollPane(approxList);

        this.add(listScroller);
    }

    public void AddToList(String aproximationToAdd) { listModel.insertElementAt(aproximationToAdd, approxList.getModel().getSize()); }
    public void DeleteElementFromList () { listModel.remove(approxList.getSelectedIndex()); }
    public boolean IsAnItemSelected() { return !approxList.isSelectionEmpty(); }
    public void DeleteAll() { listModel.removeAllElements(); }
}