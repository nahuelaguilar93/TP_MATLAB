package tclib;

import tclib.templates.*;
import java.util.Arrays;
import java.util.List;

public class Approximation {

    private static final List<String> lowpassList = Arrays.asList("LPList","Butterworth","Bessel","Legendre");
    private static final List<String> highpassList = Arrays.asList("LHList","Butterworth","Legendre");
    private static final List<String> bandpassList = Arrays.asList("BPList","Butterworth","Legendre");
    private static final List<String> bandrejectList = Arrays.asList("BRList","Butterworth","Legendre");
    private static final List<String> delayList = Arrays.asList("DelayList","Butterworth","Legendre");

    //Este m�todo lo llama el bot�n SetTemplate, y el constructor de esta clase.
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

    private TransferFunction TF;
    private double maxQobtained;
    private int Order;

    public Approximation(int index, SuperTemplate temp, int maxOrder, double maxQ){
        this(index, temp, maxOrder, maxQ, 0, 0);
    }

    public Approximation(int index, SuperTemplate temp, int maxOrder, double maxQ, double delay, double psi){
        List<String> approxList = getStringsToComboBox(temp);
        if(approxList.get(index).equals("Butterworth"));
//            Butterworth(temp, maxQ, maxOrder)
        else if (approxList.get(index).equals("Chebyshev"));
//            Cheby1(temp, maxQ, maxOrder)
    }

/*ATENCI�N AUGUSTO:
    Necesito que pases las aproximaciones que hiciste a este archivo. Las mismas deben ser M�todos de esta clase
    y van a ser llamados de la siguiente manera: Butterworth(SuperTemplate temp, int maxOrder, double maxQ)
    Explicaci�n de los par�metros:
        SuperTemplate es una clase en la cual vas a encontrar los valores normalizados de la plantilla. Los vas a
        llamar de la siguiente forma al comienzo de tu funci�n y listo:
            double Ap = temp.Ap;    //En dB
            double Aa = temp.Aa;    //En dB
            double wan = temp.wan;  //En rad/s

        maxOrder es el valor m�ximo del orden. Si el valor ingresado es 0, significa que no se declar� ning�n
        valor de orden m�ximo. IDEM para maxQ.

        Por �ltimo, los m�todos no tienen que tener Output, pero tienen que setear las siguientes variables
        propias de la clase:
            this.TF     (TransferFunction)  //La Funci�n Transferencia resultante.
            this.maxQobtained   (double)    //El Q m�s alto de la funci�n Transferencia.
            this.Order          (int)       //El orden alcanzado.
 */

    private void Butter(SuperTemplate temp, int maxOrder, double maxQ){}
    private void Cheby1(SuperTemplate temp, int maxOrder, double maxQ){}
    private void Cheby2(SuperTemplate temp, int maxOrder, double maxQ){}
    private void Legendre(SuperTemplate temp, int maxOrder, double maxQ){}
    private void Bessel(SuperTemplate temp, int maxOrder, double maxQ, double delay, double psi){}
    private void Gauss(SuperTemplate temp, int maxOrder, double maxQ, double delay, double psi){}
}

