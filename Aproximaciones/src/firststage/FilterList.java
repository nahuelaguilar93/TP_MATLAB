package firststage;

import Data.Singleton;
import Data.UserData;
import mathematics.Approximation;

import javax.swing.*;
import java.awt.*;

class FilterList extends JPanel{
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private JList<String> approxList = new JList<String>(listModel);

    public FilterList() {
        approxList.setCellRenderer(new MyCellRenderer());
        approxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //You can only choose one option.
        approxList.setLayoutOrientation(JList.VERTICAL);
        approxList.setVisibleRowCount(4); //Acá va la máxima cantidad de nombres que tira sin el scroller. Si se pone -1 trata de poner todas las que entren.
        JScrollPane listScroller = new JScrollPane(approxList);

        this.add(listScroller);
    }

    public void updateList() {
        int lastIndex = approxList.getSelectedIndex();              //Save selected index and erase the list.
        listModel.removeAllElements();

        UserData uData = Singleton.getInstance().getUserData();
        for(Approximation x : uData.getApproximationList())         //Get the list from the userData List.
            listModel.addElement(x.getDetails());

        if(lastIndex < approxList.getModel().getSize())             //Reset the last index.
            approxList.setSelectedIndex(lastIndex);
        else approxList.setSelectedIndex(approxList.getModel().getSize()-1);    //Or select the last element on list.
        approxList.ensureIndexIsVisible(approxList.getSelectedIndex());         //Make sure it is visible.
    }

    public int getIndex() {return approxList.getSelectedIndex();}
    public boolean isAnItemSelected() { return !approxList.isSelectionEmpty(); }

    private class MyCellRenderer extends DefaultListCellRenderer {  //Sets the selected filter on Bold.
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (index == Singleton.getInstance().getUserData().getSelection())
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            else
                c.setFont(c.getFont().deriveFont(Font.PLAIN));
            return c;
        }
    }
}
