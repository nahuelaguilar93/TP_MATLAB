package secondstage;

import Data.Singleton;
import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;
import tclib.GenericUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by NEGU on 8/11/2015.
 */
public class PoleZeroListsPanel extends JPanel {
    Singleton s = Singleton.getInstance();
    GenericUtils genericUtils = new GenericUtils();
    //Lists and Models
    private DefaultListModel<String> polesListModel = new DefaultListModel<>();
    private JList<String> unmatchedPolesList = new JList<>(polesListModel);
    private DefaultListModel<String> zerosListModel = new DefaultListModel<>();
    private JList<String> unmatchedZeroList = new JList<>(zerosListModel);
    private DefaultListModel<String> stagesListModel = new DefaultListModel<>();
    private JList<String> stagesList = new JList<>(stagesListModel);
    //Buttons
    private JButton selectGroupButton = new JButton("Create Stage");
    private JButton automaticSelectionButton = new JButton("Automatic Selection");
    private JButton deleteStage = new JButton("Delete Stage");

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

        selectGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matchPolesZeros();
            }
        });

        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(unmatchedPolesListScroller)
                                .addComponent(unmatchedZeroListScroller))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(selectGroupButton)
                                .addComponent(automaticSelectionButton))
                        .addComponent(stagesListScroller)
                        .addComponent(deleteStage)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(unmatchedPolesListScroller)
                                .addComponent(unmatchedZeroListScroller))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(selectGroupButton)
                                .addComponent(automaticSelectionButton))
                        .addComponent(stagesListScroller)
                        .addComponent(deleteStage)
        );
        layout.linkSize(SwingConstants.HORIZONTAL, unmatchedPolesListScroller, unmatchedZeroListScroller);
    }

    public void updateLists() {
        //First delete everything from the listPanels
        polesListModel.removeAllElements();
        zerosListModel.removeAllElements();
        stagesListModel.removeAllElements();
        //Get UserData information
        List<Complex> currentUnmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> currentUnmatchedZeros = s.getUserData().getUnmatchedZeros();
        List<Stage> currentStageList = s.getUserData().getStageList();
        //Update Lists with the userData information
        for ( Complex x : currentUnmatchedPoles) {
            polesListModel.addElement(genericUtils.getPZString(x, true));
        }
        for (Complex x : currentUnmatchedZeros) {
            zerosListModel.addElement(genericUtils.getPZString(x, false));
        }
        for (Stage x : currentStageList) {
            String poleString = genericUtils.getPZString(x.getPole(), true);
            String zeroString = genericUtils.getPZString(x.getZero(), false);
            stagesListModel.addElement( poleString + " + " + zeroString);
        }
    }

    private void matchPolesZeros() {
        if ( unmatchedPolesList.isSelectionEmpty() || unmatchedZeroList.isSelectionEmpty()) {
            //TODO: ver bien esto... igual lo de que el cero tenga que estar seleccionado no es si o si
        }
        else {
            List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
            List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
            List<Stage> stageList = s.getUserData().getStageList();

            int selectedPoleIndex = unmatchedPolesList.getSelectedIndex();
            int selectedZeroIndex = unmatchedZeroList.getSelectedIndex();

            stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(selectedZeroIndex)));
            unmatchedPoles.remove(selectedPoleIndex);
            unmatchedZeros.remove(selectedZeroIndex);

            updateLists();
        }
    }
}
