package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.MathUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approximation {

    private static final List<String> lowpassList = Arrays.asList("LP-List","Butterworth","Chebyshev");
    private static final List<String> highpassList = Arrays.asList("HP-List","Butterworth","Chebyshev");
    private static final List<String> bandpassList = Arrays.asList("BP-List","Butterworth","Chebyshev");
    private static final List<String> bandrejectList = Arrays.asList("BR-List","Butterworth","Chebyshev");
    private static final List<String> delayList = Arrays.asList("DelayList","Butterworth","Chebyshev");

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

    private TransferFunction NTF;   //Normalized Transfer Function
    private TransferFunction TF;    //Denormalized Transfer Function
    private double maxQobtained;
    private int Order;
    private String Details;
    private String ApproxName;

    public String getDetails() { return Details; }
    public TransferFunction getNTF() { return NTF; }
    public TransferFunction getTF() { return TF; }
    public double getMaxQobtained() { return maxQobtained; }
    public int getOrder() { return Order; }

    public Approximation(int index, SuperTemplate temp) { this(index, temp, 0, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, int setOrder) { this(index, temp, setOrder, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, int setOrder, double maxQ){
        this(index, temp, setOrder, maxQ, 0, 0);
    }

    public Approximation(int index, SuperTemplate temp, int setOrder, double maxQ, double delay, double psi) {
        List<String> approxList = getStringsToComboBox(temp);
        if(approxList.get(index).equals("Butterworth")) {
            Butter(temp, setOrder, maxQ);
            ApproxName = "Butterworth";
        }
        else /*if (approxList.get(index).equals("Chebyshev"))*/ {
            Cheby1(temp, setOrder, maxQ);
            ApproxName = "Cheby 1";
        }
        TF = NTF.denormalize(temp);
        Details = ApproxName + " / Orden " + Order + " / Max Q " + String.format("%.2f", maxQobtained);

    }

    private void Butter(SuperTemplate temp, int setOrder, double maxQ){
        double Ap = temp.Ap;
        double Aa = temp.Aa;
        double wan = temp.wan;
        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);

        this.Order = setOrder;
        if(maxQ > 0.5) {
            //<editor-fold defaultstate="collapsed" desc="Deduction of minOrder given maxQ">
        /* MaxQobtained = 1 / 2sin(pi/2n) < MaxQ
           2sin(pi/2n) > 1/MaxQ
           sin(pi/2n) > 1/2MaxQ
           pi/2n > asin(1/2MaxQ)
           n < pi/2asin(1/2Q)
         */
            //</editor-fold>
            int minOrder = (int) Math.floor(Math.PI / (2 * Math.asin(1. / (2 * maxQ))));
            if(setOrder == 0)
                this.Order = minOrder;
            else this.Order = Math.min(setOrder,minOrder);
        }
        else if(setOrder == 0)
            this.Order = (int) Math.ceil(Math.log10((Math.pow(10, Aa / 10) - 1) / (Math.pow(eps, 2))) / (2 * Math.log10(wan)));

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
        double Ap = temp.Ap;
        double Aa = temp.Aa;
        double wan = temp.wan;

        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);
        this.Order = setOrder;
        if(this.Order == 0)
            this.Order = (int) Math.ceil( MathUtils.acosh( Math.sqrt(Math.pow(10, Aa / 10) - 1) / eps) / MathUtils.acosh(wan) );

        List<Complex> Poles = new ArrayList<>();
        double realPart = 0.0;
        double imaginaryPart = 0.0;

        for (int i = 0; i < this.Order; i++) {
            imaginaryPart = Math.cos(Math.PI * ((2. * i + 1) / (2. * this.Order))) * Math.cosh((1. / this.Order) * MathUtils.asinh(1./eps));
            realPart = Math.sin(Math.PI * ((2. * i + 1) / (2. * this.Order))) * Math.sinh((1. / this.Order) * MathUtils.asinh(1./eps));
            if (realPart > 0)   //It has to be checked that the real part has to be negative
                realPart = -realPart;
            Poles.add(new Complex(realPart, imaginaryPart));
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray,PolesArray);
        this.maxQobtained = 1./(2*Math.sin(PolesArray[0].getArgument()-Math.PI/2));
        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Cheby1(temp,this.Order-1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }

    private void Cheby2(SuperTemplate temp, int setOrder, double maxQ){}
    private void Legendre(SuperTemplate temp, int setOrder, double maxQ){}
    private void Bessel(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
    private void Gauss(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
}