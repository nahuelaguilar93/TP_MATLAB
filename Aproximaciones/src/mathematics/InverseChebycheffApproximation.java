package mathematics;

import java.lang.Math;

import org.apache.commons.math3.util.FastMath;
import tclib.*;
import org.apache.commons.math3.*;
import org.apache.commons.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

/**
 * Created by Augusto on 8/10/2015.
 */
public class InverseChebycheffApproximation {

    public InverseChebycheffApproximation() {
        double Ap = 4;
        double Aa = 50;
        double wp = 10;
        double wa = 1000;
        double wan = wa / wp;

        /*Here starts the aproximation function*/
        double epsilon = 1 / (Math.sqrt(Math.pow(10, Aa / 10) - 1));

        /*Using again Math.log(x + Math.sqrt(x*x - 1.0)) as acosh*/
        /*Also using Math.log(x + Math.sqrt(x*x + 1.0)) as asinh*/


        int minOrder = (int) Math.ceil((Math.log((1 / (epsilon * Math.sqrt(Math.pow(10, Ap / 10)))) + Math.sqrt((1 / (epsilon * Math.sqrt(Math.pow(10, Ap / 10)))) * (1 / (epsilon * Math.sqrt(Math.pow(10, Ap / 10)))) - 1.0))) / (Math.log(wan + Math.sqrt(wan * wan - 1.0))));
                /*ACA LA LOGICA DE SI EL USUARIO INGRESO O NO UN ORDEN*/
        int order = minOrder;

        double beta = Math.abs((1 / order) * Math.log((1 / epsilon) + Math.sqrt((1 / epsilon) * (1 / epsilon) + 1.0)));

        List<Complex> PolesOfTransferFunction = new ArrayList<>();
        List<Complex> ZeroesOfTransferFunction = new ArrayList<>();

        double alpha=0;
        for(int k=1;k<=order;k++){

            alpha= Math.PI*((2*k-1)/(2*order));
            double denominador=Math.pow(Math.sin(alpha)*Math.sinh(beta),2)+Math.pow(Math.cos(alpha)*Math.cosh(beta),2); //This is to maintain a decent size for next line

            PolesOfTransferFunction.add(new Complex(-wan*((Math.sin(alpha)*Math.sinh(beta))/(denominador)),wan*((Math.cos(alpha)*Math.cosh(beta))/denominador)));


            if(2*order==2*(2*k-1) || 6*order==2*(2*k-1)){
                continue;   //This is because in the next calculation, using this will end up giving a NaN
            } else {
                ZeroesOfTransferFunction.add(new Complex(0,wan/(Math.cos(alpha))));
            }
        }
    }
}
