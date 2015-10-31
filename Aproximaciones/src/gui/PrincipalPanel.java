package gui;

import gui.firststage.StageOne;
import gui.secondstage.StageTwo;
import gui.thirdstage.StageThree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 31/10/2015.
 */
public class PrincipalPanel extends JPanel {
    private static int currentPanel = 0;        //Registry of the stage I'm in.

    /*The configuration goes like this:
       *           There is a principalPanel with two Panels. One is the bigPanel where it has everything of the stage
       *           The other is a buttonPanel which only has two buttons to go back and forth between panels
        */

    public PrincipalPanel() {
        JPanel bigPanel = new JPanel();
        StageOne stageOne = new StageOne();
        StageTwo stageTwo = new StageTwo();
        StageThree stageThree = new StageThree();
        CardLayout cardLayout = new CardLayout();
        JPanel buttonPanel = new JPanel();
        JButton nextStageButton = new JButton("Next Stage");
        JButton previousStageButton = new JButton("Previous Stage");

        bigPanel.setLayout(cardLayout);
        bigPanel.add(stageOne, "One");
        bigPanel.add(stageTwo, "Two");
        bigPanel.add(stageThree, "Three");

        previousStageButton.setEnabled(false);

        //TODO: hacer que se ponga eneble solo cuando tengo la transferencia seleccionada.
        nextStageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(bigPanel);
                currentPanel++;
                previousStageButton.setEnabled(true);
                if (currentPanel == 2) {
                    nextStageButton.setEnabled(false);
                }
            }
        });
        previousStageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(bigPanel);
                currentPanel--;
                nextStageButton.setEnabled(true);
                if (currentPanel == 0) {
                    previousStageButton.setEnabled(false);
                }
            }
        });

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(nextStageButton, BorderLayout.LINE_END);
        buttonPanel.add(previousStageButton, BorderLayout.LINE_START);
        buttonPanel.setMaximumSize(new Dimension(1920, 400));         //TODO: Revisar este hardcode feo

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(bigPanel);
        this.add(buttonPanel);
    }
}
