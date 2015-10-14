package mathematics;

import tclib.*; //import of the mathematics library

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Augusto on 8/10/2015.
 */
public class ButterworthApproximation extends Approximation {

    private TransferFunction ButterworthTransferFunction;


    public ButterworthApproximation() { //en el constructor se tiene que pedir la user Data, porque se necesita la plantilla desnormalizada, y el orden o Q m�ximo..
        double Ap = 3;
        double Aa = 40;   //Deben venir de user Data y se tomaran como que estan en dB
        double wp = 100;
        double wa = 1000;//Estos wp y wa deben venir de userData al igual que Ap y AA
        double wan = wa / wp;   //Supongo que el wanormalizado me lo da userData

        double epsilon = Math.sqrt(Math.pow((double) 10, Ap / 10) - 1);

        int minOrder = (int) Math.ceil(Math.log10((Math.pow(10, Aa / 10) - 1) / (Math.pow(epsilon, 2))) / (2 * Math.log10(wan)));   //Math ceil rounds up a decimal to upper integer
        //Si esta seteado, pongo el que se setea,,, esto lo tiene que manejar el que haga UserData
        int order = minOrder;
        List<Complex> PolesOfTransferFunction = new ArrayList<>();

        /*Ahora debo generar los polos pero tiene que haber alguna manera como para que yo pueda usar radio y phase para crear un complejo
        Como complex funciona como parte imaginaria, voy a hacer algo medio turbio:
        Modulo=sqrt(re^2+im^2)
        Fase=tan-1(im/real)  ->>> tan(fase)*real=im

        Modulo^2=real^2(1+tan(Fase))

        real=Modulo/(sqrt(1+tan(Fase)));    (1), lo remplazo en la primera del Modulo y obtengo que:


        im=sqrt(Modulo^2-Modulo/(sqrt(1+tan(Fase))));

                  ^
        Re villero| pero no se me ocurrio otra forma...
                  |
        */

        double firstArgument = Math.PI / 2 + Math.PI / order;
        double Argument = firstArgument;
        double module = Math.pow(1 / epsilon, 1 / order);

        for (int i = 0; i < minOrder; i++) {
            PolesOfTransferFunction.add(i, new Complex(module / Math.sqrt(1 + Math.tan(Argument)), Math.sqrt(Math.pow(module, 2) - module / Math.sqrt(1 + Math.tan(Argument)))));
            /*Esto de arriba esta bien? o lo tengo que asignar a Poles of transferfunction?*/
            Argument = Argument + Math.PI / order;
        }

        /*Aca estarian los polos pero como los paso a funcion transferencia?*/
        /*Hay que hacer una funcion que pase de raices a un polinomio como el que pide Transferfuncion para inicializarse!*/


    }

    @Override   //this is an override of the function that Approximation is asking
    protected TransferFunction getNormalizedTransferFunction() {
        return null;
    }
}
