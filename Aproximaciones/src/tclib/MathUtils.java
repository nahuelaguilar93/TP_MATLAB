package tclib;

/**
 * Created by kdewald on 13/10/15.
 */
public class MathUtils {

    public static double asinh(double x) { return Math.log(x + Math.sqrt(x*x + 1.0)); }
    public static double acosh(double x) { return Math.log(x + Math.sqrt(x*x - 1.0)); }
    public static double atanh(double x) { return Math.log( (x + 1.0)/(x - 1.0) ) / 2; }
    public static int factorial(int n) {
        int fact = 1; // this  will be the result.
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static int[][] getAllPermutations(int n){
        int array[][] = new int[factorial(n)][n];
        if(n < 0) return array;

        int element = 0;
        int I[] = new int[n];
        for(int i = 0; i < I.length; i++) I[i] = -1;

        mainLoop:
        for(int j = 0; j > -1; j--){
            I[j]++;
            if(I[j] >= n){
                I[j] = -1;
                continue;
            }
            for(int k=0; k<j; k++)
                if(I[j]==I[k]) {
                    j++;
                    continue mainLoop;
                }
            j++;
            if(++j <= n) continue;
            else j--;
            System.arraycopy( I, 0, array[element++], 0, n );
        }

/*        for(int[] x : array) {
            for (int i : x)
                System.out.print(i);
            System.out.println();
        }                                   */
        return array;
    }

    private static final double gr=(Math.sqrt(5)-1)/2;

    public static double gss(TransferFunction TF, double a, double b, double tol) {
        tol=1e-2;
        double c = b - gr * (b - a);
        double d = a + gr * (b - a);
        while (Math.abs(c - d) > tol) {
            double fc = TF.evaluateApproximationAtOmega(c).abs();
            double fd = TF.evaluateApproximationAtOmega(d).abs();
            if (fc < fd) {
                b = d;
                d = c;
                c = b - gr * (b - a);
            } else {
                a = c;
                c = d;
                d = a + gr * (b - a);
            }
        }
        return (b + a) / 2;
    }
}