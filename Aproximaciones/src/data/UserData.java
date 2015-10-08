package data;

/**
 * Created by NEGU on 7/10/2015.
 */
public class UserData {
    private static UserData ud = null;
    private static int FilterType=0;
    private static int Aprox=0;
    private static double B=1;
    private static double Wp=0;
    private static double Wa=0;
    private static double Aa=0;
    private static double Ap=0;
    private static int n=1;

    public static int getFilterType() {
        return FilterType;
    }

    public static double getAa() {
        return Aa;
    }

    public static double getAp() {
        return Ap;
    }

    public static double getB() {
        return B;
    }

    public static double getWa() {
        return Wa;
    }

    public static double getWp() {
        return Wp;
    }

    public static int getAprox() {
        return Aprox;
    }

    public static int getN() {
        return n;
    }

    public static void setAa(double aa) {
        Aa = aa;
    }

    public static void setAp(double ap) {
        Ap = ap;
    }

    public static void setAprox(int aprox) {
        Aprox = aprox;
    }

    public static void setB(double b) {
        B = b;
    }

    public static void setFilterType(int filterType) {
        FilterType = filterType;
    }

    public static void setN(int n) {
        UserData.n = n;
    }

    public static void setWa(double wa) {
        Wa = wa;
    }

    public static void setWp(double wp) {
        Wp = wp;
    }

}
