package secondstage;

import Data.Singleton;
import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;
import tclib.GenericUtils;
import tclib.TransferFunction;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
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

    int joker;      //This is used to know if I have to use all zeros twice or not

    public PoleZeroListsPanel() {
        Singleton_S2 s2 = Singleton_S2.getInstance();
        //Only one string can be selected at the same time
        unmatchedPolesList.setSelectionModel(new MySelectionModel(unmatchedZeroList, 2));
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
                s2.getstagePlotModePanel().updateStagePlot();
                s2.getPlotPoleZeroPanel().updatePoleZeroPlot();
            }
        });
        automaticSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automaticSelection();
                s2.getstagePlotModePanel().updateStagePlot();
                s2.getPlotPoleZeroPanel().updatePoleZeroPlot();
            }
        });

        stagesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                s2.getstagePlotModePanel().updateStagePlot();
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

    private void automaticSelection() {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
        int tempPoloIndex1;
        int tempPoloIndex2;
        int tempZeroIndex1;
        int tempZeroIndex2;
        while ( unmatchedPoles.size() > 0) {
            tempPoloIndex1 = findMaxQIndex(unmatchedPoles);
            //System.out.println("tempPoloIndex1: " + tempPoloIndex1);

            //This is used to asign the second index in case there are two real poles
            if ( (unmatchedPoles.get(tempPoloIndex1).getImaginary() == 0) && (unmatchedPoles.size() > 1) ) {
                if (tempPoloIndex1 == 0) { tempPoloIndex2 = 1; }
                else { tempPoloIndex2 = 0; }
            }
            else { tempPoloIndex2 = tempPoloIndex1; }

            //System.out.println("tempPoloIndex2: " + tempPoloIndex2);

            //Now I get the closest Zero Index number 1
            tempZeroIndex1 = findClosestZero(unmatchedZeros, unmatchedPoles.get(tempPoloIndex1), unmatchedPoles.get(tempPoloIndex2));
            //System.out.println("tempZeroIndex1: " + tempZeroIndex1);
            tempZeroIndex2 = tempZeroIndex1;

            if (!unmatchedZeros.isEmpty()) {
                if (unmatchedZeros.get(tempZeroIndex1).getImaginary() == 0) { //If and only if the zero has no imaginary part.
                    boolean onlyOnePole = (unmatchedPoles.get(tempPoloIndex1) == unmatchedPoles.get(tempPoloIndex2)) && (unmatchedPoles.get(tempPoloIndex1).getImaginary() == 0);
                    if (!onlyOnePole && unmatchedZeros.size() > 1) { //Only one Pole
                        for (int i = 0; i < unmatchedZeros.size(); i++) {
                            if (unmatchedZeros.get(i).getImaginary() == 0 && i != tempZeroIndex1) {
                                tempZeroIndex2 = i;
                            }
                        }
                    }
                }
            }
            //System.out.println("tempZeroIndex2: " + tempZeroIndex2);

            if ( tempPoloIndex1 == tempPoloIndex2 ) {
                //System.out.println("Only one polo");
                matchWithSingleSelection(tempZeroIndex1, tempZeroIndex2, tempPoloIndex1);
            } else {
                //System.out.println("Two poles");
                matchWithDoubleSelection(tempZeroIndex1, tempZeroIndex2, tempPoloIndex1, tempPoloIndex2);
            }
            updateLists();
        }
    }

    private int findMaxQIndex(List<Complex> unmatchedPoles) {
        int tempPoloIndex1 = 0;
        Complex higherPoleQ = unmatchedPoles.get(0);
        for ( int i = 0; i < unmatchedPoles.size(); i++) {
            if ( GenericUtils.getQ(unmatchedPoles.get(i)) > GenericUtils.getQ(higherPoleQ) ) {
                higherPoleQ = unmatchedPoles.get(i);
                tempPoloIndex1 = i;
            }
        }
        return tempPoloIndex1;
    }
    private int findClosestZero (List<Complex> unmatchedZeros, Complex Polo1, Complex Polo2) {
        int tempZeroIndex1 = 0;

        if (unmatchedZeros.isEmpty()) {
            return 0;
        }

        double distance = findComplexDistance(unmatchedZeros.get(0), Polo2);
        if ( Polo1 == Polo2 && Polo1.getImaginary() == 0 ) {    //Only one Pole
            for ( int i = 0; i < unmatchedZeros.size(); i++) {
                if ( unmatchedZeros.get(i).getImaginary() == 0 ) { tempZeroIndex1 = i; }
            }
        }
        else {      //Two poles
            for ( int i = 0; i < unmatchedZeros.size(); i++) {
                if ( findComplexDistance(unmatchedZeros.get(i), Polo1) < distance ) {
                    distance = findComplexDistance(unmatchedZeros.get(i), Polo1);
                    tempZeroIndex1 = i;
                }
            }
        }
        return tempZeroIndex1;
    }
    private double findComplexDistance ( Complex c1, Complex c2 ) {
        return Math.sqrt( Math.pow(c1.getReal()-c2.getReal(), 2) + Math.pow(c1.getImaginary()-c2.getImaginary(), 2) );
    }

    private boolean checkIndexes( int firstZeroIndex, int secondZeroIndex, int firstPoleIndex, int secondPoleIndex) {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();

        if ( unmatchedPolesList.isSelectionEmpty() || unmatchedZeroList.isSelectionEmpty()) {   //If there is no Selection
            return false;     //And error frame is annoying... maybe is better just to do nothing. The user will realize
            //JInternalFrame frame = new JInternalFrame();
            //JOptionPane.showMessageDialog(frame, "There must be selected a pole and at least one zero", "No pole or zero found", JOptionPane.ERROR_MESSAGE);
        }
        else if ( firstPoleIndex != secondPoleIndex ) { //Selected two poles
            return checkTwoPoles(firstZeroIndex, secondZeroIndex, firstPoleIndex, secondPoleIndex);
        }
        else {
            return checkOnePole(firstZeroIndex, secondZeroIndex, firstPoleIndex);
        }

    }
    private boolean checkTwoPoles(int firstZeroIndex, int secondZeroIndex, int firstPoleIndex, int secondPoleIndex) {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();

        if ( unmatchedPoles.get(firstPoleIndex).getImaginary() != 0 || unmatchedPoles.get(secondPoleIndex).getImaginary() != 0  ) {
            //They are not in the real axis
            return false;
        }
        else if ( firstZeroIndex != secondZeroIndex) {   //If there are two different zeros
            if (((unmatchedZeros.get(firstZeroIndex).getImaginary() != 0) && (firstZeroIndex != unmatchedZeros.size())) || (unmatchedZeros.get(secondZeroIndex).getImaginary() != 0) && (secondZeroIndex != unmatchedZeros.size())) {
                //If one of them is a conjugate complex then you can't select both
                return false;
            }
            return true;
        }
        else {  //If there is only one zero
            if ( firstZeroIndex < unmatchedZeros.size() ) {     //If its not none
                if ( unmatchedZeros.get(firstZeroIndex).getImaginary() != 0 ) {
                    return true;
                }
                else {
                    return (joker > 0);
                }
            }
            else { return (joker > 1); }
        }
    }
    private boolean checkOnePole(int firstZeroIndex, int secondZeroIndex, int selectedPoleIndex) {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();

        if ( firstZeroIndex != secondZeroIndex) {   //If there are two different zeros
            if (((unmatchedZeros.get(firstZeroIndex).getImaginary() != 0) && (firstZeroIndex !=  unmatchedZeros.size())) || (unmatchedZeros.get(secondZeroIndex).getImaginary() != 0) && (secondZeroIndex !=  unmatchedZeros.size())) {
                //If one of them is a conjugate complex then you can't select both
                return false;
            }
            if ( (unmatchedPoles.get(unmatchedPolesList.getSelectedIndex()).getImaginary() == 0) ) {    //If the pole is simple then you cant selecto two zeros
                return false;
            }
            return true;
        }
        else {  //If there is only one zero
            if ( firstZeroIndex < unmatchedZeros.size() ) {     //If its not none
                if  ( (unmatchedPoles.get(unmatchedPolesList.getSelectedIndex()).getImaginary() == 0) ) { //If its a single pole
                    if (unmatchedZeros.get(firstZeroIndex).getImaginary() != 0) {   //And I chose a conjugate complex
                        return false;                                           //Then I cant choose it
                    }
                    else { return true; }                                                //If is a single pole but a single zero then OK.
                }
                else {  //If it wasn't a single pole
                    if ( unmatchedZeros.get(firstZeroIndex).getImaginary() == 0 ) { return (joker > 0); } //And I choose a simple zero (none never reach this if)
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
    }

    private void matchPolesZeros() {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
        List<Stage> stageList = s.getUserData().getStageList();

        int firstZeroIndex = unmatchedZeroList.getMinSelectionIndex();
        int secondZeroIndex = unmatchedZeroList.getMaxSelectionIndex();
        int firstPoleIndex = unmatchedPolesList.getMinSelectionIndex();
        int secondPoleIndex = unmatchedPolesList.getMaxSelectionIndex();

        if (checkIndexes(firstZeroIndex, secondZeroIndex, firstPoleIndex, secondPoleIndex)) {
            if ( firstPoleIndex == secondPoleIndex) {
                matchWithSingleSelection(firstZeroIndex, secondZeroIndex, firstPoleIndex);
            }
            else {
                matchWithDoubleSelection(firstZeroIndex, secondZeroIndex, firstPoleIndex, secondPoleIndex);
            }
            updateLists();
        }
    }
    private void matchWithSingleSelection(int firstZeroIndex, int secondZeroIndex, int selectedPoleIndex) {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
        List<Stage> stageList = s.getUserData().getStageList();

        if ( firstZeroIndex == secondZeroIndex ) {  //Only One Zero Selected
            if (firstZeroIndex == unmatchedZeros.size()) {  //If its None
                stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex)));
                unmatchedPoles.remove(selectedPoleIndex);
            } else {
                stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstZeroIndex)));
                unmatchedPoles.remove(selectedPoleIndex);
                unmatchedZeros.remove(firstZeroIndex);
            }
        }
        else {  //There are two Zero Selections
            if (firstZeroIndex == unmatchedZeros.size()) {
                stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(secondZeroIndex)));
                unmatchedPoles.remove(selectedPoleIndex);
                unmatchedZeros.remove(secondZeroIndex);
            }
            else if (secondZeroIndex == unmatchedZeros.size()) {
                stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstZeroIndex)));
                unmatchedPoles.remove(selectedPoleIndex);
                unmatchedZeros.remove(firstZeroIndex);
            }
            else {
                stageList.add(new Stage(unmatchedPoles.get(selectedPoleIndex), unmatchedZeros.get(firstZeroIndex), Complex.INF, unmatchedZeros.get(secondZeroIndex)));
                unmatchedPoles.remove(selectedPoleIndex);
                unmatchedZeros.remove(secondZeroIndex);             //First remove the bigger index so that pointed zero by firstZeroIndex is not changed
                unmatchedZeros.remove(firstZeroIndex);
            }
        }
    }
    private void matchWithDoubleSelection(int firstZeroIndex, int secondZeroIndex, int firstPoleIndex, int secondPoleIndex) {
        List<Complex> unmatchedPoles = s.getUserData().getUnmatchedPoles();
        List<Complex> unmatchedZeros = s.getUserData().getUnmatchedZeros();
        List<Stage> stageList = s.getUserData().getStageList();
        int maxPoleIndex = Math.max(firstPoleIndex, secondPoleIndex);
        int minPoleIndex = Math.min(firstPoleIndex, secondPoleIndex);
        int maxZeroIndex = Math.max(firstZeroIndex, secondZeroIndex);
        int minZeroIndex = Math.min(firstZeroIndex, secondZeroIndex);

        if ( firstZeroIndex == secondZeroIndex ) {  //Only One Selected
            if (firstZeroIndex == unmatchedZeros.size()) {  //If its None
                stageList.add(new Stage(unmatchedPoles.get(firstPoleIndex), Complex.INF, unmatchedPoles.get(secondPoleIndex)));
                unmatchedPoles.remove(maxPoleIndex);     //I have to remove first the bigger one
                unmatchedPoles.remove(minPoleIndex);
            } else {
                stageList.add(new Stage(unmatchedPoles.get(firstPoleIndex), unmatchedZeros.get(firstZeroIndex), unmatchedPoles.get(secondPoleIndex)));
                unmatchedPoles.remove(maxPoleIndex);     //I have to remove first the bigger one
                unmatchedPoles.remove(minPoleIndex);
                unmatchedZeros.remove(maxZeroIndex);
            }
        }
        else {  //There are two Selections
            if (firstZeroIndex == unmatchedZeros.size()) {
                stageList.add(new Stage(unmatchedPoles.get(firstPoleIndex), unmatchedZeros.get(secondZeroIndex), unmatchedPoles.get(secondPoleIndex)));
                unmatchedPoles.remove(maxPoleIndex);     //I have to remove first the bigger one
                unmatchedPoles.remove(minPoleIndex);
                unmatchedZeros.remove(maxZeroIndex);
            }
            else if (secondZeroIndex == unmatchedZeros.size()) {
                stageList.add(new Stage(unmatchedPoles.get(firstPoleIndex), unmatchedZeros.get(firstZeroIndex), unmatchedPoles.get(secondPoleIndex)));
                unmatchedPoles.remove(maxPoleIndex);     //I have to remove first the bigger one
                unmatchedPoles.remove(minPoleIndex);
                unmatchedZeros.remove(maxZeroIndex);
            }
            else {
                stageList.add(new Stage(unmatchedPoles.get(secondPoleIndex), unmatchedZeros.get(firstZeroIndex), unmatchedPoles.get(secondPoleIndex), unmatchedZeros.get(secondZeroIndex)));
                unmatchedPoles.remove(maxPoleIndex);     //I have to remove firts the bigger one
                unmatchedPoles.remove(minPoleIndex);
                unmatchedZeros.remove(maxZeroIndex);     //First remove the bigger index so that pointed zero by firstZeroIndex is not changed
                unmatchedZeros.remove(minZeroIndex);
            }
        }

    }

    public int getStagesListIndex() {
        if ( stagesList.isSelectionEmpty() ) {
            return -1;
        }
        else {
            return stagesList.getSelectedIndex();
        }
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

