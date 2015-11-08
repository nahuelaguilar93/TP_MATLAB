package secondstage;

import javax.swing.*;

/**
 * Created by NEGU on 8/11/2015.
 */
public class PoleZeroListsPanel extends JPanel {
    private DefaultListModel<String> polesListModel = new DefaultListModel<>();
    private JList<String> unmatchedPolesList = new JList<>(polesListModel);

    private DefaultListModel<String> zerosListModel = new DefaultListModel<>();
    private JList<String> unmatchedZeroList = new JList<>(zerosListModel);

    private DefaultListModel<String> stagesListModel = new DefaultListModel<>();
    private JList<String> stagesList = new JList<>(stagesListModel);

    public PoleZeroListsPanel() {
        //Only one string can be selected at the same time
        unmatchedPolesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        unmatchedZeroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //List Layout Orientation
        unmatchedPolesList.setLayoutOrientation(JList.VERTICAL);
        unmatchedZeroList.setLayoutOrientation(JList.VERTICAL);
        stagesList.setLayoutOrientation(JList.VERTICAL);
        //Maximum Row Count
        unmatchedPolesList.setVisibleRowCount(5);
        unmatchedZeroList.setVisibleRowCount(5);
        stagesList.setVisibleRowCount(5);
        //Create Scrollers
        JScrollPane unmatchedPolesListScroller = new JScrollPane(unmatchedPolesList);
        JScrollPane unmatchedZeroListScroller = new JScrollPane(unmatchedZeroList);
        JScrollPane stagesListScroller = new JScrollPane(stagesList);

        this.add(unmatchedPolesListScroller);
        this.add(unmatchedZeroListScroller);
        this.add(stagesListScroller);
    }
}
