package secondstage;

import Data.Singleton;
import Data.UserData;
import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;

import javax.swing.*;
import java.awt.*;

public class StageTwoPanel extends JPanel {
    private StagePanel stagePanel;
    private PoleZeroPanel poleZeroPanel;

    public StageTwoPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.BLUE);

        stagePanel = new StagePanel();
        poleZeroPanel = new PoleZeroPanel();

        this.add(stagePanel);
        this.add(poleZeroPanel);
    }

    void set(){
        UserData uData = Singleton.getInstance().getUserData();
        TransferFunction t = uData.getApproximationList().get(uData.getSelection()).getTF();
        for(Complex x : t.getPoles())
            uData.getUnmatchedPoles().add(x);
        for(Complex x : t.getZeros())
            uData.getUnmatchedZeros().add(x);
    }
}
