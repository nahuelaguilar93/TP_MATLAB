package data;

import java.util.*;

import tclib.*;
import tclib.templates.AbstractTemplate;

/**
 * Created by NEGU on 7/10/2015.
 */

/*Nueva user data hecha por Augusto ( con U entre la A y la G )*/

public class UserData {  //No tiene que ser static? o no existen clases estaticas o no? quiero saber como la hago especificamente

    //public static List<Approximation> ApproximationList= new List<Approximation>();
    //Ayuda a kevin con esto... como las hago las listas de las aproximaciones?, hago listas de funciones transferencia?

    public static List<TransferFunction> ApproximationList = new ArrayList<>(); //Will be treated as LIFO? or how?
    public static AbstractTemplate CurrentTemplate = null;


    /*Esta es la que habia hecho agustin solo*/

    private static UserData ud = null;
    private static int FilterType = 0;
    private static int Aprox = 0;
    private static double B = 1;
    private static double Wp = 0;
    private static double Wa = 0;
    private static double Aa = 0;
    private static double Ap = 0;
    private static double Wo = 0;
    private static int n = 1;

    //Getters
    public static double getWo() {
        return Wo;
    }
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
    //Setters
    public static void setWo(double wo) {
        Wo = wo;
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
