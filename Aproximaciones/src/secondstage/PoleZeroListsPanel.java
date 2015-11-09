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

    int joker; //This is used to know if I have to use all zeros twice or not

    public PoleZeroListsPanel() {
        //Only one string can be selected at the same time
        unmatchedPolesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        unmatchedZeroList.setSelectionModel(new MySelectionModel(unmatchedZeroList, 2));
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
        joker = 0;
        for ( Complex x : currentUnmatchedPoles) {
            polesListModel.addElement(genericUtils.getPZString(x, true));
            if ( x.getImaginary() == 0 ) { joker += 1; }
            else { joker += 2; }
        }
        int originZeroCount = 0;
        for (Complex x : currentUnmatchedZeros) {
            zerosListModel.addElement(genericUtils.getPZString(x, false));
            if ( x.getImaginary() == 0 ) {
                joker -= 1;
                originZeroCount++;
            }
            else { joker -= 2; }
        }

        if ( originZeroCount % 2 == 0) {
            if ( joker > 0  ) { zerosListModel.addElement("<None>"); }
        }
        else {
            if ( joker > 1  ) { zerosListModel.addElement("<None>"); }
        }
        for (Stage x : currentStageList) {
            stagesListModel.addElement( x.getDetails() );
        }
    }

    private void matchPolesZeros() {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
        List<Stage> stageList = s.getUserData().getStageList();

        int selectedPoleIndex = unmatchedPolesList.getSelectedIndex();

        int firstIndex = unmatchedZeroList.getMinSelectionIndex();
        int secondIndex = unmatchedZeroList.getMaxSelectionIndex();

        if (checkIndexes(firstIndex, secondIndex)) {

            if ( firstIndex == secondIndex ) {  //Only One Selected
                if (firstIndex == unmatchedZeros.size()) {  //If its None
                    stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex)));
                    unmatchedPoles.remove(selectedPoleIndex);
                } else {
                    stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstIndex)));
                    unmatchedPoles.remove(selectedPoleIndex);
                    unmatchedZeros.remove(firstIndex);
                }
            }
            else {  //There are two Selections
                if (firstIndex == unmatchedZeros.size()) {
                    stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(secondIndex)));
                    unmatchedPoles.remove(selectedPoleIndex);
                    unmatchedZeros.remove(secondIndex);
                }
                else if (secondIndex == unmatchedZeros.size()) {
                    stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstIndex)));
                    unmatchedPoles.remove(selectedPoleIndex);
                    unmatchedZeros.remove(firstIndex);
                }
                else {
                    stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstIndex), unmatchedZeros.get(secondIndex)));
                    unmatchedPoles.remove(selectedPoleIndex);
                    unmatchedZeros.remove(secondIndex);             //First remove the bigger index so that pointed zero by firstIndex is not changed
                    unmatchedZeros.remove(firstIndex);
                }
            }
            updateLists();
        }
    }

    private boolean checkIndexes( int firstIndex, int secondIndex) {
        //Fot debug
        System.out.println("fist Index: " + firstIndex);
        System.out.println("Second Index: " + secondIndex);
        System.out.println("Joker: " + joker);
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();

        //TODO: Hay que hacer que se pueda seleccionar dos polos si son simples! lrpm Q

        if ( unmatchedPolesList.isSelectionEmpty() || unmatchedZeroList.isSelectionEmpty()) {   //If there is no Selection
            return false;     //And error frame is annoying... maybe is better just to do nothing. The user will realize
            //JInternalFrame frame = new JInternalFrame();
            //JOptionPane.showMessageDialog(frame, "There must be selected a pole and at least one zero", "No pole or zero found", JOptionPane.ERROR_MESSAGE);
        }
        else if ( firstIndex != secondIndex) {   //If there are two different selections
            if (((unmatchedZeros.get(firstIndex).getImaginary() != 0) && (firstIndex !=  unmatchedZeros.size())) || (unmatchedZeros.get(secondIndex).getImaginary() != 0) && (secondIndex !=  unmatchedZeros.size())) {
                //If one of them is a conjugate complex then you can't select both
                return false;
            }
            if ( (unmatchedPoles.get(unmatchedPolesList.getSelectedIndex()).getImaginary() == 0) ) {    //If the pole is simple then you cant selecto two zeros
                return false;
            }
            return true;
        }
        else if (firstIndex == secondIndex) {  //If there is only one selection
            if ( firstIndex < unmatchedZeros.size() ) {     //If its not none
                if  ( (unmatchedPoles.get(unmatchedPolesList.getSelectedIndex()).getImaginary() == 0) ) { //If its a single pole
                    if (unmatchedZeros.get(firstIndex).getImaginary() != 0) {   //And I chose a conjugate complex
                        return false;                                           //Then I cant choose it
                    }
                    else { return true; }                                                //If is a single pole but a single zero then OK.
                }
                else {  //If it wasn't a single pole
                    if ( unmatchedZeros.get(firstIndex).getImaginary() == 0 ) { return (joker > 0); } //And I choose a simple zero (none never reach this if)
                    else { return true; }   //Then its a conjugate pole with a conjugate zero!
                }
            }
            else {      //I choose none
                if (unmatchedPoles.get(unmatchedPolesList.getSelectedIndex()).getImaginary() == 0) {        //And I have a single pole
                    return true;
                }
                else { return (joker > 1); }  //Othewise only possible if I have joker left
            }
        }
        return false;
    }

    private static class MySelectionModel extends DefaultListSelectionModel {
        private JList list;
        private int maxCount;

        private MySelectionModel(JList list,int maxCount) {
            this.list = list;

            this.maxCount = maxCount;
        }

        @Override
        public void setSelectionInterval(int index0, int index1) {
            if (index1 - index0 >= maxCount)
            {
                index1 = index0 + maxCount - 1;
            }
            super.setSelectionInterval(index0, index1);
        }

        @Override
        public void addSelectionInterval(int index0, int index1) {
            int selectionLength = list.getSelectedIndices().length;
            if (selectionLength >= maxCount)
                return;

            if (index1 - index0 >= maxCount - selectionLength)
            {
                index1 = index0 + maxCount - 1 - selectionLength;
            }
            if (index1 < index0)
                return;
            super.addSelectionInterval(index0, index1);
        }
    }
}

