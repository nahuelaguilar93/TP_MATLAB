import gui.firststage.StageOne;
import gui.secondstage.StageTwo;
import gui.thirdstage.StageThree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NEGU on 30/10/2015.
 */
public class PrincipalPanel extends JPanel{
    StageOne stageOne = new StageOne();
    StageTwo stageTwo = new StageTwo();
    StageThree stageThree = new StageThree();
    CardLayout cardLayout = new CardLayout();
    JButton nextStageButton = new JButton();


    public PrincipalPanel() {
        this.setLayout(cardLayout);
        this.add(stageOne, "One");
        this.add(stageTwo, "Two");
        this.add(stageThree, "Three");

        //cardLayout.show(this, "Two");

    }
}
