package secondstage;

import Data.Singleton;
import Data.UserData;
import org.apache.commons.math3.analysis.function.Sin;
import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;

import javax.swing.*;
import java.awt.*;

public class StageTwoPanel extends JPanel {
    private StagePanel stagePanel;
    private PoleZeroPanel poleZeroPanel;

    public StageTwoPanel() {
        Singleton_S2 s = Singleton_S2.getInstance();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.BLUE);

        stagePanel = s.getStagePanel();
        poleZeroPanel = s.getPoleZeroPanel();

        this.add(poleZeroPanel);
        this.add(stagePanel);
    }

    public void set(){
        UserData uData = Singleton.getInstance().getUserData();
        TransferFunction t = uData.getApproximationList().get(uData.getSelection()).getTF();
        if (t.equals(uData.getTransferFunction())) return;
        uData.setTransferFunction(t);
        uData.getUnmatchedPoles().clear();
        uData.getUnmatchedZeros().clear();
        uData.getStageList().clear();
        for(Complex x : t.getPoles())
            uData.getUnmatchedPoles().add(x);
        for(Complex x : t.getZeros())
            uData.getUnmatchedZeros().add(x);
    }
}
