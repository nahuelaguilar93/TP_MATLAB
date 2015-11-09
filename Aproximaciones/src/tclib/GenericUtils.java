package tclib;

import org.apache.commons.math3.complex.Complex;

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
        String str = "Zero ";
        if(isPole) str = "Pole ";
        double real = x.getReal();
        double imag = x.getImaginary();
        double Q = getQ(x);
        return str + "r: " + String.format("%.1f", real) + " i: " + String.format("%.2f", imag) + " Q: " + String.format("%.2f", Q);
    }
}