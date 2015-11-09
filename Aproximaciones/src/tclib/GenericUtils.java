package tclib;

import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class GenericUtils {

    public static double[] linspace(double start, double end, int pointCount){
        double[] result = new double[pointCount];
        double step = (end - start) / pointCount;
        for (int i = 0; i < pointCount; i++) {
            result[i] = (start + i * step);
        }
        return result;
    }

    public static double[] logspace(double start, double end, int pointCount){
        double[] result = linspace(Math.log(start), Math.log(end), pointCount);

        for (int i = 0; i < pointCount; i++) {
            result[i] = Math.exp(result[i]);
        }
        return result;
    }

    public static double getQ(Complex x){
        return Math.abs(1./(2.*Math.cos(x.getArgument())));
    }

    public static String getPZString(Complex x, boolean isPole){
        if ( x == Complex.INF) return "No Zero";
        String str = "Zero ";
        if(isPole) str = "Pole ";
        double real = x.getReal();
        double imag = x.getImaginary();
        double Q = getQ(x);
        return str + "r: " + String.format("%.1f", real) + " i: " + String.format("%.2f", imag) + " Q: " + String.format("%.2f", Q);
    }

    public static double dynamicRangeLoss(List<Stage> stages, double minW, double maxW) {
        return dynamicRangeLoss(stages, minW, maxW, 10000);
    }
    public static double dynamicRangeLoss(List<Stage> stages, double minW, double maxW, int points) {
        if(stages.size() == 0) return 0;
        double freq[] = linspace(minW, maxW, points);
        double dynRangeLoss[] = new double[points];
        List<TransferFunction> acumTF =  new ArrayList<>();
        acumTF.add(stages.get(0).getTF());
        for(int i = 1; i < stages.size(); i++){
            TransferFunction nextTF = new TransferFunction(acumTF.get(i-1));
            nextTF.multiply(stages.get(i).getTF());
            acumTF.add(nextTF);
        }
        double stageGain[] = new double[stages.size()];
        for(int i = 0; i < points; i++) {
            for (int j = 0; j < stages.size(); j++)
                stageGain[j] = acumTF.get(j).evaluateApproximationAtOmega(freq[i]).abs();
            double max = stageGain[0];
            double min = stageGain[0];
            for(double x : stageGain){
                if(max < x) max = x;
                if(min > x) min = x;
            }
            dynRangeLoss[i] =  max/min;
        }
        double maxLoss = dynRangeLoss[0];
        for(double x : dynRangeLoss){
            if(maxLoss < x) maxLoss = x;
        }
        return maxLoss;
    }
}