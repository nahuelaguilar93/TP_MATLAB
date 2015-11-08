package Data;

import firststage.StageOnePanel;
import secondstage.StageTwoPanel;
import thirdstage.StageThree;

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
    JPanel bigPanel;
    StageOnePanel stageOnePanel;
    StageTwoPanel stageTwoPanel;
    StageThree stageThree;
    CardLayout cardLayout;
    JPanel buttonPanel;
    JButton nextStageButton;
    JButton previousStageButton;

    public PrincipalPanel() {
        Singleton s = Singleton.getInstance();
        JPanel bigPanel = new JPanel();
        StageOnePanel stageOnePanel = s.getStageOnePanel();
        StageTwoPanel stageTwoPanel = s.getStageTwoPanel();
        StageThree stageThree = s.getStageThree();
        CardLayout cardLayout = new CardLayout();
        JPanel buttonPanel = new JPanel();
        JButton nextStageButton = new JButton("Next Stage");
        JButton previousStageButton = new JButton("Previous Stage");

        bigPanel.setLayout(cardLayout);
        bigPanel.add(stageOnePanel, "One");
        bigPanel.add(stageTwoPanel, "Two");
        bigPanel.add(stageThree, "Three");

        previousStageButton.setEnabled(false);

        nextStageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserData uData = Singleton.getInstance().getUserData();
                if(currentPanel == 0) {
                    if (uData.getApproximationList().size() == 1)
                        uData.setSelection(0);
                    if (uData.getSelection() == -1) {
                        JInternalFrame frame = new JInternalFrame();
                        JOptionPane.showMessageDialog(frame, "Please select a filter before moving on.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Singleton.getInstance().getStageTwo().set();
                }
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
