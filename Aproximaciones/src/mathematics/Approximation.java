package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approximation {

    private static final List<String> lowpassList = Arrays.asList("LP-List","Butterworth","Chebyshev","Bessel","Legendre");
    private static final List<String> highpassList = Arrays.asList("HP-List","Butterworth","Chebyshev","Legendre");
    private static final List<String> bandpassList = Arrays.asList("BP-List","Butterworth","Chebyshev","Legendre");
    private static final List<String> bandrejectList = Arrays.asList("BR-List","Butterworth","Chebyshev","Legendre");
    private static final List<String> delayList = Arrays.asList("DelayList","Butterworth","Chebyshev","Legendre");

    //Este método lo llama el botón SetTemplate, y el constructor de esta clase.
    public static List<String> getStringsToComboBox(SuperTemplate template) {
        if(template instanceof LowpassTemplate)
            return lowpassList;
        else if(template instanceof HighpassTemplate)
            return highpassList;
        else if(template instanceof BandpassTemplate)
            return bandpassList;
        else if(template instanceof BandrejectTemplate)
            return bandrejectList;
        else if(template instanceof DelayTemplate)
            return delayList;
        else return lowpassList;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    private TransferFunction NTF;
    private TransferFunction TF;
    private double maxQobtained;
    private int Order;
    private String Details;

    public String getDetails() { return Details; }

    public Approximation(int index, SuperTemplate temp) { this(index, temp, 0, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, int setOrder) { this(index, temp, setOrder, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, int setOrder, double maxQ){
        this(index, temp, setOrder, maxQ, 0, 0);
    }

    public Approximation(int index, SuperTemplate temp, int setOrder, double maxQ, double delay, double psi) {
        List<String> approxList = getStringsToComboBox(temp);
        if(approxList.get(index).equals("Butterworth")) {
            Butter(temp, setOrder, maxQ);
            Details = "Butterworth " + "/ Orden " + Order + " / Max Q " + maxQobtained;
        }
        else /*if (approxList.get(index).equals("Chebyshev"))*/ {
            Cheby1(temp, setOrder, maxQ);
            Details = "Cheby 1 " + "/ Orden " + Order + " / Max Q " + maxQobtained;
        }

    }

    public void Denormalize(SuperTemplate temp) {
        if(temp instanceof LowpassTemplate)
            TF = NTF.denormalize((LowpassTemplate) temp);
        else if(temp instanceof HighpassTemplate)
            TF = NTF.denormalize((HighpassTemplate) temp);
        else if(temp instanceof BandpassTemplate)
            TF = NTF.denormalize((BandpassTemplate) temp);
        else if(temp instanceof BandrejectTemplate)
            TF = NTF.denormalize((BandrejectTemplate) temp);
    }

/*ATENCIÓN AUGUSTO:
    Necesito que pases las aproximaciones que hiciste a este archivo. Las mismas deben ser Métodos de esta clase
    y van a ser llamados de la siguiente manera: Butterworth(SuperTemplate temp, int setOrder, double maxQ)
    Explicación de los parámetros:
        SuperTemplate es una clase en la cual vas a encontrar los valores normalizados de la plantilla. Los vas a
        llamar de la siguiente forma al comienzo de tu función y listo:
            double Ap = temp.Ap;    //En dB
            double Aa = temp.Aa;    //En dB
            double wan = temp.wan;  //En rad/s

        setOrder es el valor especificado del orden. Si el valor ingresado es 0, significa que no se declaró ningún
        valor de orden máximo. IDEM para maxQ.

        Por último, los métodos no tienen que tener Output, pero tienen que setear las siguientes variables
        propias de la clase:
            this.NTF     (TransferFunction)  //La Función Transferencia resultante.
            this.maxQobtained   (double)    //El Q más alto de la función Transferencia.
            this.Order          (int)       //El orden alcanzado.
 */

    private void Butter(SuperTemplate temp, int setOrder, double maxQ){
        double Ap = temp.Ap;
        double Aa = temp.Aa;
        double wan = temp.wan;

        double eps = Math.sqrt(Math.pow(10., Ap / 10) - 1);
        this.Order = setOrder;
        if(this.Order == 0)
            this.Order = (int) Math.ceil(Math.log10((Math.pow(10., Aa / 10) - 1) / (Math.pow(eps, 2))) / (2 * Math.log10(wan)));

        List<Complex> Poles = new ArrayList<>();

        double arg = Math.PI / 2 + Math.PI / (2 * this.Order);
        double module = Math.pow(1. / eps, 1. / this.Order);
        for (int i = 0; i < this.Order; i++) {
            Poles.add(new Complex(module * Math.cos(arg), module * Math.sin(arg)));
            arg += Math.PI / this.Order;
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray,PolesArray);
        this.maxQobtained = 1./(2*Math.sin(Math.PI/(2*this.Order)));
    }


    private void Cheby1(SuperTemplate temp, int setOrder, double maxQ){
        double[] numer = {1};
        double[] denom = {1,2,1};
        this.NTF = new TransferFunction(numer, denom);
        this.Order = 7;
        this.maxQobtained = 8.3;
    }
    private void Cheby2(SuperTemplate temp, int setOrder, double maxQ){}
    private void Legendre(SuperTemplate temp, int setOrder, double maxQ){}
    private void Bessel(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
    private void Gauss(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
}