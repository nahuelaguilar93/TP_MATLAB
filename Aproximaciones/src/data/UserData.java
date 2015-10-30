package data;

import java.util.*;

import tclib.*;
<<<<<<< HEAD
import tclib.templates.AbstractTemplate;
import tclib.templates.BandpassTemplate;
=======
import tclib.templates.SuperTemplate;
>>>>>>> Este no funciona, borre cosas y todavia no las arreglé para que compile.

/**
 * Created by NEGU on 7/10/2015.
 */

public class UserData {
<<<<<<< HEAD
=======

    public static List<Approximation> ApproximationList2 = new ArrayList<Approximation>();
>>>>>>> Este no funciona, borre cosas y todavia no las arreglé para que compile.
    public static List<TransferFunction> ApproximationList = new ArrayList<>(); //Will be treated as LIFO? or how?
    public static SuperTemplate CurrentTemplate = null;

<<<<<<< HEAD
=======

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
<<<<<<< HEAD
=======
    private static SuperTemplate template = new SuperTemplate() {
        @Override
        public TemplateTypeEnum getTemplateType() {
            return null;
        }
    };
>>>>>>> Este no funciona, borre cosas y todavia no las arreglé para que compile.

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


>>>>>>> Este no funciona, borre cosas y todavia no las arreglé para que compile.
}
