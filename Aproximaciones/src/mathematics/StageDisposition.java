package mathematics;

import Data.Singleton;
import tclib.GenericUtils;
import tclib.MathUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel on 10/11/2015.
 */
public class StageDisposition {
    private List<Stage> stageList;
    public List<Stage> getStageList() { return stageList; };
    private double wMax;
    private double wMin;
    SuperTemplate temp;
    TransferFunction finalTF;

    StageDisposition(List<Stage> stages) {
        stageList = stages;
        SuperTemplate temp = Singleton.getInstance().getUserData().getCurrentTemplate();
        finalTF = Singleton.getInstance().getUserData().getTransferFunction();
        wMax = temp.getWmax();
        wMin = temp.getWmin();
        gainCorrection();
    }
    public void gainCorrection() {
        if(stageList.size() == 0) return;
        double wEval;
        if (temp instanceof LowpassTemplate) wEval = ((LowpassTemplate) temp).getWp();
        else if (temp instanceof HighpassTemplate) wEval = ((HighpassTemplate) temp).getWp();
        else if (temp instanceof DelayTemplate) wEval = ((DelayTemplate) temp).getWp();
        else if (temp instanceof BandpassTemplate) wEval = ((BandpassTemplate) temp).getWo();
        else wEval = ((BandrejectTemplate) temp).getWpp();

        TransferFunction acumTF = stageList.get(0).getTF();
        for(int i = 1; i < stageList.size(); i++)
            acumTF.multiply(stageList.get(i).getTF());
        double correction = finalTF.evaluateApproximationAtOmega(wEval).abs()
                / acumTF.evaluateApproximationAtOmega(wEval).abs();
        stageList.get(0).getTF().multiply(correction);
    }

    public void minimizeDRL(){
        if(stageList.size() == 0) return;
        int permutations[][] = MathUtils.getAllPermutations(stageList.size());
        double lowestDRL = Double.POSITIVE_INFINITY;
        List<TransferFunction> sortedTFList = new ArrayList<>();

        double M = finalTF.evaluateApproximationAtOmega(getMaxGainFreq(finalTF,wMin/100,wMax*100)).abs();

        for(int[] x : permutations) {
            for(int i : x) sortedTFList.add(new TransferFunction(stageList.get(i).getTF()));

            sortedTFList.clear();
        }
    }

    private double getMaxGainFreq(TransferFunction TF, double wMin, double wMax){
        int pointsQuantity = (int)(Math.log10(wMax/wMin)*100);  //Evalúo 100 puntos por década.
        double freq[] = GenericUtils.logspace(wMin,wMin,pointsQuantity);
        double module[] = TF.getModule(freq);

        double max = module[0];
        int maxFreqIndex = 0;
        for (int i = 1; i < module.length; i++) {
            if (module[i] > max) {
                max = module[i];
                maxFreqIndex = i;
            }
        }

        if (maxFreqIndex == 0)
            return wMin*1e-6;
        else if (maxFreqIndex == freq.length)
            return wMax*1e6;
        else return MathUtils.gss(TF, freq[maxFreqIndex - 1], freq[maxFreqIndex + 1], 1e-2);
    }
}