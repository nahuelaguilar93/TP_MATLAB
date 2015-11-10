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
    public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static int[][] getAllPossibleArrangements(int n){
        int array[][] = new int[factorial(n)][];
        List<int[]> myList = new ArrayList<>();
        int I[] = new int[n];
        for(int i = 0; i < I.length; i++) I[i] = -1;
//        System.out.println("Hola");
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
            myList.add(I);
//            for(int x : I)
//                System.out.print(x);
//            System.out.println();
        }
        return array;
    }
}