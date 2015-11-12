package mathematics;

import Data.Singleton;
import tclib.GenericUtils;
import tclib.MathUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.List;

public class StageDisposition {
    private List<Stage> stageList;
    private double DRL = 0;
    public List<Stage> getStageList() { return stageList; };

    public StageDisposition() { this(new ArrayList<>()); }
    public StageDisposition(List<Stage> stages) { stageList = stages; }

    public void gainCorrection() {
        if(stageList.size() == 0) return;
        SuperTemplate temp = Singleton.getInstance().getUserData().getCurrentTemplate();
        double wEval = temp.getBand()[0];

        TransferFunction finalTF = new TransferFunction(Singleton.getInstance().getUserData().getTransferFunction());
        TransferFunction acumTF = new TransferFunction(stageList.get(0).getTF());
        for(int i = 1; i < stageList.size(); i++)
            acumTF.multiply(stageList.get(i).getTF());

        double correction = finalTF.evaluateApproximationAtOmega(wEval).abs()
                / acumTF.evaluateApproximationAtOmega(wEval).abs();
 //       System.out.println("WEval: " + wEval + "  finalTF: " + finalTF.evaluateApproximationAtOmega(wEval).abs()
 //       + "  acumTF: " + acumTF.evaluateApproximationAtOmega(wEval).abs());
        stageList.get(0).getTF().multiply(correction);
    }

    public void minimizeDRL(){
        if(stageList.size() == 0) return;

        double lowestDRL = 1000;
        double bandRange[] = Singleton.getInstance().getUserData().getCurrentTemplate().getBand();
        List<TransferFunction> bestTFList =  new ArrayList<>();
        List<TransferFunction> sortedTFList = new ArrayList<>();
        List<TransferFunction> acumTFList =  new ArrayList<>();

        int permutations[][] = MathUtils.getAllPermutations(stageList.size());
        for(int[] x : permutations) {
            for(int i : x) sortedTFList.add(new TransferFunction(stageList.get(i).getTF()));
//            for(int i = 0; i<stageList.size(); i++) sortedTFList.add(new TransferFunction(stageList.get(i).getTF()));////////////////Debuging

            acumTFList.add(new TransferFunction(sortedTFList.get(0)));
            for(int i = 1; i < sortedTFList.size(); i++){
                TransferFunction nextTF = new TransferFunction(acumTFList.get(i-1));
                nextTF.multiply(sortedTFList.get(i));
                acumTFList.add(nextTF);
            }
            SuperTemplate temp = Singleton.getInstance().getUserData().getCurrentTemplate();
            double wMax = temp.getWmax();
            double wMin = temp.getWmin();
//        System.out.println("wMin: " + wMin + "  wMax: " + wMax);
            double auxW = getMaxGainFreq(sortedTFList.get(0), wMin / 100, wMax * 100);
//        System.out.println("maxGainFreq: " + auxW);
            double prevAcumStageMax = sortedTFList.get(0).evaluateApproximationAtOmega(auxW).abs();
            for (int k = 1; k < sortedTFList.size(); k++) {
                auxW = getMaxGainFreq(acumTFList.get(k), wMin/100, wMax*100);
//            System.out.println("maxGainFreq: " + auxW);
                double nextAcumStageMax = acumTFList.get(k).evaluateApproximationAtOmega(auxW).abs();
                double correction = prevAcumStageMax/nextAcumStageMax;
                sortedTFList.get(k).multiply(correction);
                sortedTFList.get(0).multiply(1./correction);
//            System.out.println("prevAcumStageMax: " + prevAcumStageMax);
//            System.out.println("nextAcumStageMax: " + nextAcumStageMax);
                prevAcumStageMax = nextAcumStageMax;
            }

            double newDRL = GenericUtils.dynamicRangeLoss(sortedTFList, bandRange[0], bandRange[1], 12345);
            if(bandRange.length == 4)
                newDRL = Math.max(auxW,GenericUtils.dynamicRangeLoss(sortedTFList, bandRange[2], bandRange[3], 12345));

//            System.out.println("newDRL: " + 20.*Math.log10(newDRL) + "  " + newDRL);
            if(newDRL < lowestDRL){
                lowestDRL = newDRL;
                bestTFList = new ArrayList<>(sortedTFList);
            }
            sortedTFList.clear();
            acumTFList.clear();
        }
        this.DRL = lowestDRL;
        this.stageList.clear();
        for(TransferFunction x : bestTFList)
            this.stageList.add(new Stage(x));
    }

    public double getDRL() { return this.DRL; }
    private double getMaxGainFreq(TransferFunction TF, double wMin, double wMax){
        int pointsQuantity = (int)(Math.log10(wMax/wMin)*100);  //Evalúo 100 puntos por década.
        double freq[] = GenericUtils.logspace(wMin,wMax,pointsQuantity);
        double module[] = TF.getModule(freq);

        double min = module[0];
        int minFreqIndex = 0;
        for (int i = 1; i < module.length; i++) {
            if (module[i] < min) {
                min = module[i];
                minFreqIndex = i;
            }
        }
//        System.out.println("getMaxGainFreq. wMin: " + wMin + " wMax: " + wMax + " Points: " + pointsQuantity);
//        System.out.println("getMaxGainFreq. module.length: " + module.length + " module[0]: " + module[0]
//        + " module[fin]: " + module[module.length-1] + " module[mid]: " + module[module.length/2] + " min: " + min + " index: " + minFreqIndex);

        if (minFreqIndex == 0)
            return wMin*1e-6;
        else if (minFreqIndex == freq.length-1)
            return wMax*1e6;
        else return MathUtils.gss(TF, freq[minFreqIndex - 1], freq[minFreqIndex + 1], 1e-2);
    }
}