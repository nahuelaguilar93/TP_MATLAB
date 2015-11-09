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

    double dynamicRangeLoss(List<Stage> stages, double minW, double maxW) {
        return dynamicRangeLoss(stages, minW, maxW, 10000);
    }
    double dynamicRangeLoss(List<Stage> stages, double minW, double maxW, int points) {
        if(stages.size() == 0) return 0;
        double freq[] = linspace(minW, maxW, points);
        double dynRange[] = new double[points];
        List<TransferFunction> acumTF =  new ArrayList<>();
//        acumTF.add(stages.get(0))
        for(int i = 0; i < points; i++)
            dynRange[i] = 0;
        return 4.;
    }
}