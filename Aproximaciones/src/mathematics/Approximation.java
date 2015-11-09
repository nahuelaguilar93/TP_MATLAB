package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.MathUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approximation {
    private static final List<String> lowpassList = Arrays.asList("Butterworth","Chebyshev I","Chebyshev II", "Legendre", "Cauer", "Bessel", "Better", "Letter", "Buby");
    private static final List<String> highpassList = Arrays.asList("Butterworth","Chebyshev I","Chebyshev II", "Legendre", "Cauer", "Better", "Letter", "Buby");
    private static final List<String> bandpassList = Arrays.asList("Butterworth","Chebyshev I","Chebyshev II", "Legendre", "Cauer", "Better", "Begendre", "Buby");
    private static final List<String> bandrejectList = Arrays.asList("Butterworth","Chebyshev I","Chebyshev II", "Legendre", "Cauer", "Better", "Letter", "Buby");
    private static final List<String> delayList = Arrays.asList("Bessel","Gauss");

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

    public Approximation(int index, SuperTemplate temp) { this(index, temp, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, double denormPerc) { this(index, temp, denormPerc, 0, 0); }
    public Approximation(int index, SuperTemplate temp, double denormPerc, int setOrder) { this(index, temp, denormPerc, setOrder, 0); }

    public Approximation(int index, SuperTemplate temp, double denormPerc, int setOrder, double maxQ) {
        List<String> approxList = getStringsToComboBox(temp);
        boolean  inverseDenormalization = false;
        if(approxList.get(index).equals("Butterworth")) {
            Butter(temp, setOrder, maxQ);
            ApproxName = "Butterworth";
        }
        else if (approxList.get(index).equals("Chebyshev I")) {
            Cheby1(temp, setOrder, maxQ);
            ApproxName = "Cheby 1";
        }
        else /*if (approxList.get(index).equals("Chebyshev II"))*/ {
            Cheby2(temp, setOrder, maxQ);
            ApproxName = "Cheby 2";
            inverseDenormalization = true;
            denormPerc = 1.-denormPerc;
        }
        double range = getDenormalizationRange(temp, inverseDenormalization);
        double denorm = Math.pow(range,denormPerc);
        TF = NTF.denormalize(temp, denorm);
        TF.multiply(temp.getG());
        Details = ApproxName + " / Orden " + Order + " / Max Q: " + String.format("%.2f", maxQobtained) + " / " + (int)(denormPerc*100);
    }

    public boolean equals(Approximation that) {
        if (this.TF.equals(that.TF)) return true;
        else return false;
    }

    private double NTFminusAp(double x, double Aa){
        return -20*Math.log10(NTF.evaluateApproximationAtOmega(x).abs()) - Aa;
    }
    private double getDenormalizationRange(SuperTemplate temp, boolean inverseDenormalization) {
        //By using the Bisection Method, it gets the frequency at which the approximation equals Aa.
        double Aa = temp.getAa()*0.9999;    //Para evitar errores en los valles de Cauer y Cheby II.
        if(inverseDenormalization == true) Aa = temp.getAp()*0.99999;
        double a = 1;   // 1 rad/s    Wp Normalizado.
        double b = temp.getWan();
        int sa = (int) Math.signum(NTFminusAp(a, Aa));
        int sb = (int) Math.signum(NTFminusAp(b, Aa));
        if(sa == sb) return 1;  //La función no cumple plantilla, por lo cual no acepta un corrimiento de desnormalización.
        double maxIter = 100000;
        double tol = Math.pow((b/a), 1./10000);
        for(int i = 0; i < maxIter; i++) {
            double c = Math.sqrt(a*b);
            int sc = (int) Math.signum(NTFminusAp(c, Aa));
            if(sc == sa)
                a = c;  //b = b;
            else
                b = c;  //a = a;
            if(b/a < tol)
                break;
        }
        if(inverseDenormalization == true) return 1./Math.sqrt(a*b);
        else return temp.getWan()/Math.sqrt(a*b);
    }

    ////////////////////////////////////APPROXIMATIONS//////////////////////////////////////////////

    private void Butter(SuperTemplate temp, int setOrder, double maxQ){
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();
        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);

        this.Order = setOrder;
        if(maxQ > 0.5) {
            //<editor-fold defaultstate="collapsed" desc="Deduction of maxOrder given maxQ">
        /* MaxQobtained = 1 / 2sin(pi/2n) < MaxQ
           2sin(pi/2n) > 1/MaxQ
           sin(pi/2n) > 1/2MaxQ
           pi/2n > asin(1/2MaxQ)
           n < pi/2asin(1/2Q)
         */
            //</editor-fold>
            int maxOrder = (int) Math.floor(Math.PI / (2 * Math.asin(1. / (2 * maxQ))));
            if(setOrder == 0)
                this.Order = maxOrder;
            else this.Order = Math.min(setOrder,maxOrder);
        }
        else if(setOrder == 0)
            this.Order = (int) Math.ceil(Math.log10((Math.pow(10, Aa / 10) - 1) / (Math.pow(eps, 2))) / (2 * Math.log10(wan)));

        List<Complex> Poles = new ArrayList<>();

        double arg = Math.PI / 2 + Math.PI / (2 * this.Order);
        double module = Math.pow(1. / eps, 1. / this.Order);
        for (int i = 0; i < this.Order; i++) {
            double real = module * Math.cos(arg);
            double imag = module * Math.sin(arg);
            if(Math.abs(imag) < 10e-3) imag = 0;    //Floating point error correction.
            Poles.add(new Complex(real, imag));
            arg += Math.PI / this.Order;
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray,PolesArray);
        this.maxQobtained = 1./(2*Math.sin(Math.PI/(2*this.Order)));
    }
    private void Cheby1(SuperTemplate temp, int setOrder, double maxQ){
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();

        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);
        this.Order = setOrder;
        if(this.Order == 0)
            this.Order = (int) Math.ceil( MathUtils.acosh( Math.sqrt(Math.pow(10, Aa / 10) - 1) / eps) / MathUtils.acosh(wan) );

        List<Complex> Poles = new ArrayList<>();
        double beta = (1. / this.Order) * MathUtils.asinh(1./eps);

        for (int i = 0; i < this.Order; i++) {
            double alpha = Math.PI*((2.*i+1)/(2*this.Order));
            double imag =   Math.cos(alpha) * Math.cosh(beta);
            double real = - Math.sin(alpha) * Math.sinh(beta);
            if (Math.abs(imag) < 10e-3) imag = 0;   //Floating point error correction.
            Poles.add(new Complex(real, imag));
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray,PolesArray);
        double origin = this.NTF.getModuleAtOrigin();
        if(this.Order%2 == 0) origin /= Math.pow(10, -Ap / 20);
        this.NTF.multiply(1./origin);

        this.maxQobtained = Math.abs(1./(2.*Math.sin(PolesArray[0].getArgument()-Math.PI/2)));
        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Cheby1(temp,this.Order-1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }
    private void Cheby2(SuperTemplate temp, int setOrder, double maxQ){
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();

        double eps = 1. / Math.sqrt(Math.pow(10, Aa / 10) - 1);
        this.Order = setOrder;
        if(this.Order == 0)
            this.Order = (int) Math.ceil( MathUtils.acosh( 1./ (Math.sqrt(Math.pow(10, Ap / 10) - 1) * eps)) / MathUtils.acosh(wan) );

        List<Complex> Poles = new ArrayList<>();
        List<Complex> Zeros = new ArrayList<>();
        double beta = Math.abs((1. / this.Order) * MathUtils.asinh(1./eps));

        for (int i = 0; i < this.Order; i++) {
            double alpha = Math.PI*((2.*i+1)/(2*this.Order));
            double imag =   Math.cos(alpha) * Math.cosh(beta);
            double real = - Math.sin(alpha) * Math.sinh(beta);
            if (Math.abs(imag) < 10e-3) imag = 0;   //Floating point error correction.
            Poles.add(new Complex(real, imag).reciprocal().multiply(wan));
            if(2*i+1 != this.Order) //alpha != pi/2
                Zeros.add(new Complex(0,wan/Math.cos(alpha)));
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = Zeros.toArray(new Complex[Zeros.size()]);
        this.NTF = new TransferFunction(ZerosArray,PolesArray);
        double origin = this.NTF.getModuleAtOrigin();
        this.NTF.multiply(1./origin);

        this.maxQobtained = Math.abs(1./(2.*Math.sin(PolesArray[0].getArgument()-Math.PI/2)));
        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Cheby2(temp, this.Order - 1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }

    private void Legendre(SuperTemplate temp, int setOrder, double maxQ){}
    private void Bessel(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
    private void Gauss(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi){}
}