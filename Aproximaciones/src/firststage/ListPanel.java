package firststage;

import javax.swing.*;

/**
 * Created by NEGU on 7/10/2015.
 */
class ListPanel extends JPanel{
    private ApproxList approxList;

    public ListPanel() {
        Singleton_S1 s = Singleton_S1.getInstance();
        approxList = s.getApproxList();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Aproximation List"));
        this.add(approxList);
    }

    //public void addItemToList(String aproximationToAdd) { approxList.AddToList(aproximationToAdd); }
    public ApproxList getApproxList() { return approxList; }
}
