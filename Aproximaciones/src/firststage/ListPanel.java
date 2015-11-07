package firststage;

import javax.swing.*;

/*
 * Created by NEGU on 7/10/2015.
 */
class ListPanel extends JPanel{
    private FilterList filterList;
    private ListButtonsPanel listButtonsPanel;

    public ListPanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        filterList = s.getFilterList();
        listButtonsPanel = s.getListButtonsPanel();

        this.setBorder(BorderFactory.createTitledBorder("Aproximation List"));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(filterList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listButtonsPanel)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(filterList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listButtonsPanel)
        );
    }

    //public void addItemToList(String aproximationToAdd) { filterList.AddToList(aproximationToAdd); }
    public FilterList getFilterList() { return filterList; }
}
