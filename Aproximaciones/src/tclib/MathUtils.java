package tclib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdewald on 13/10/15.
 */
public class MathUtils {

    public static double asinh(double x) { return Math.log(x + Math.sqrt(x*x + 1.0)); }
    public static double acosh(double x) { return Math.log(x + Math.sqrt(x*x - 1.0)); }
    public static double atanh(double x) { return Math.log( (x + 1.0)/(x - 1.0) ) / 2; }

    public static int[][] getAllPossibleArrangements(int n){
        int array[][] = new int[0][];
        List<List<Integer>> myList = new ArrayList<>();



        return array;
    }


}
