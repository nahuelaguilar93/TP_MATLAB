package thirdstage;

import Data.Singleton;
import mathematics.Stage;
import secondstage.PlotPoleZeroPanel;
import secondstage.PoleZeroListsPanel;
import secondstage.StageProperties;

import java.awt.*;
import java.awt.List;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StagePanel extends JPanel{
    private DefaultListModel<String> stagesListModel = new DefaultListModel<>();
    private JList<String> stagesList = new JList<>(stagesListModel);
    private StageProperties stageProperties;
    private PlotPoleZeroPanel plotPoleZeroPanel;
	
	StagePanel(){
		Singleton_S3 s = Singleton_S3.getInstance();
        //Create the list
        stagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stagesList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane stagesListScroller = new JScrollPane(stagesList);

        stagesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //TODO: poner que cambie la lista de filtros
                if (!stagesList.isSelectionEmpty()) {
                    s.getStageProperties().updateLabels(stagesList.getSelectedIndex());
                }
            }
        });

        stageProperties = s.getStageProperties();
        plotPoleZeroPanel = s.getPlotPoleZeroPanel();


        //Setup layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup().addComponent(stagesListScroller).addComponent(stageProperties))
                    .addComponent(plotPoleZeroPanel)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup().addComponent(stagesListScroller).addComponent(stageProperties))
                    .addComponent(plotPoleZeroPanel)
        );

	}

    public void updateList() {
        Singleton s = Singleton.getInstance();
        stagesListModel.removeAllElements();
        java.util.List<Stage> currentStageList = s.getUserData().getStageList();
        for (Stage x : currentStageList) {
            stagesListModel.addElement( x.getDetails() );
        }
    }
}
