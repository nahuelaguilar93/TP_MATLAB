package tclib;

/**
 * Created by kdewald on 24/09/15.
 */
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

}
