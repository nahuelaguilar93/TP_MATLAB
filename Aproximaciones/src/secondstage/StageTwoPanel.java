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
        //System.out.println("Empiezo a pasar a la etapa 2");
        UserData uData = Singleton.getInstance().getUserData();
        TransferFunction t = uData.getApproximationList().get(uData.getSelection()).getTF();
        if (t.equals(uData.getTransferFunction())) return;
        uData.setTransferFunction(t);
        uData.getUnmatchedPoles().clear();
        uData.getUnmatchedZeros().clear();
        uData.getStageList().clear();
        //System.out.println("Vamos a obtener los polos...");
        for(Complex x : t.getPoles()) {
            if(Math.abs(x.getImaginary()) < Math.pow(10,-6)) x = new Complex(x.getReal(),0);
            if (x.getImaginary() >= 0)  //Only the positive conjugated are stored because I only need one of the two
                uData.getUnmatchedPoles().add(x);
        }
        //System.out.println("Vamos a obtener los ceros...");
        //TODO KEVIN: muere en getZeros cuando hacemos un butter orden 9 o mayor para el RechazaBanda
        //TODO KEVIN: cuando hacemos un rechaza banda butter calcula mal los ceros (no están sobre el eje imaginario)
        for(Complex x : t.getZeros()){
            //System.out.println("x = " + x);
            if(Math.abs(x.getImaginary()) < Math.pow(10,-6)) x = new Complex(0,0);
            if (x.getImaginary() >= 0)  //Only the positive conjugated are stored because I only need one of the two
                uData.getUnmatchedZeros().add(x);
        }
        //System.out.println("Ya obtuve todo :)");
        Singleton_S2.getInstance().getPoleZeroListsPanel().updateLists();
        Singleton_S2.getInstance().getPlotPoleZeroPanel().updatePoleZeroPlot();
    }
}
