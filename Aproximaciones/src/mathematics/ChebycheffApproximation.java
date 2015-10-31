package mathematics;

import org.apache.commons.math3.complex.Complex;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augusto on 8/10/2015.
 */
public class ChebycheffApproximation {
    public ChebycheffApproximation() {
        /*Notese que al igual que en Butter esto debe modificarse al modificar el flow del programa con userdata y los templates*/
        double Ap = 3;
        double Aa = 40;   //Deben venir de user Data y se tomaran como que estan en dB
        double wp = 100;
        double wa = 1000;//Estos wp y wa deben venir de userData al igual que Ap y AA
        double wan = wa / wp;   //Supongo que el wanormalizado me lo da userData
        /*Hasta aca*/

        double epsilon = Math.sqrt(Math.pow((double) 10, Ap / 10) - 1); //same as butterworth

        // Kevin: Use class FastMath from Apache Math.
        // Example: Fastmath.asinh(3);

        /* acosh function does not exist in java's Math library, therefore I will use some other Math's function to
        * recreate it like this:
        * Math.log(x + Math.sqrt(x*x - 1.0))        where x is the value which goes in the acosh
        *
        * also asinh is made up of:
        * Math.log(x + Math.sqrt(x*x + 1.0));
        * */


        int minOrder = (int) Math.ceil(Math.log(Math.sqrt((Math.pow(10, Aa / 10) - 1) / epsilon) + Math.sqrt(Math.sqrt((Math.pow(10, Aa / 10) - 1) / epsilon) * Math.sqrt((Math.pow(10, Aa / 10) - 1) / epsilon) - 1.0)) / Math.log(wan + Math.sqrt(wan * wan - 1.0)));


        //Aca va una logica para obtener el orden que ingreso el usuario, si es que lo hizo
        int order = minOrder;


        List<Complex> PolesOfTransferFunction = new ArrayList<>();
        double realPart = 0.0;
        double imaginaryPart = 0.0;


        for (int i = 0; i < order; i++) {
            imaginaryPart = Math.cos(Math.PI * ((2 * i - 1) / (2 * order))) * Math.cosh((1 / order) * Math.log((1 / epsilon) + Math.sqrt((1 / epsilon) * (1 / epsilon) - 1.0)));
            realPart = Math.sin(Math.PI * ((2 * i - 1) / (2 * order))) * Math.sinh((1 / order) * Math.log((1 / epsilon) + Math.sqrt((1 / epsilon) * (1 / epsilon) + 1.0)));
            //It has to be checked that the real part has to be negative
            if (realPart > 0) {
                realPart = -realPart;
            }
            PolesOfTransferFunction.add(new Complex(realPart, imaginaryPart));
        }
        //I have poles, and it has no zeroes, therefore I would only have format the poles to fit in with TransferFunction

    }
}
