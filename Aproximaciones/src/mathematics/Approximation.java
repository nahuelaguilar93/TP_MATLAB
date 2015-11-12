package mathematics;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import tclib.MathUtils;
import tclib.TransferFunction;
import tclib.templates.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approximation {
    private static final List<String> lowpassList = Arrays.asList("Butterworth", "Chebyshev I", "Chebyshev II", "Legendre", "Cauer", "Bessel");
    private static final List<String> highpassList = Arrays.asList("Butterworth", "Chebyshev I", "Chebyshev II", "Legendre", "Cauer");
    private static final List<String> bandpassList = Arrays.asList("Butterworth", "Chebyshev I", "Chebyshev II", "Legendre", "Cauer");
    private static final List<String> bandrejectList = Arrays.asList("Butterworth", "Chebyshev I", "Chebyshev II", "Legendre", "Cauer");
    private static final List<String> delayList = Arrays.asList("Bessel", "Gauss");

    //Este método lo llama el botón SetTemplate, y el constructor de esta clase.
    public static List<String> getStringsToComboBox(SuperTemplate template) {
        if (template instanceof LowpassTemplate)
            return lowpassList;
        else if (template instanceof HighpassTemplate)
            return highpassList;
        else if (template instanceof BandpassTemplate)
            return bandpassList;
        else if (template instanceof BandrejectTemplate)
            return bandrejectList;
        else if (template instanceof DelayTemplate)
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
    public boolean equals(Approximation that) { return this.TF.equals(that.TF); }

    public Approximation(int index, SuperTemplate temp) { this(index, temp, 0, 0, 0); }
    public Approximation(int index, SuperTemplate temp, double denormPerc) { this(index, temp, denormPerc, 0, 0); }
    public Approximation(int index, SuperTemplate temp, double denormPerc, int setOrder) { this(index, temp, denormPerc, setOrder, 0); }
    public Approximation(int index, SuperTemplate temp, double denormPerc, int setOrder, double maxQ) {
        List<String> approxList = getStringsToComboBox(temp);
        boolean inverseDenormalization = false;
        int denormPrint = (int) (denormPerc * 100);
        if (approxList.get(index).equals("Butterworth")) {
            Butter(temp, setOrder, maxQ);
            ApproxName = "Butterworth";
        } else if (approxList.get(index).equals("Chebyshev I")) {
            Cheby1(temp, setOrder, maxQ);
            ApproxName = "Cheby 1";
        } else if (approxList.get(index).equals("Bessel")) {
            Bessel(temp, setOrder, maxQ);
            ApproxName = "Bessel";
        } else /*if (approxList.get(index).equals("Chebyshev II"))*/ {
            Cheby2(temp, setOrder, maxQ);
            ApproxName = "Cheby 2";
            inverseDenormalization = true;
            denormPerc = 1. - denormPerc;
        }
        double range = getDenormalizationRange(temp, inverseDenormalization);
        double denorm = Math.pow(range, denormPerc);
        TF = NTF.denormalize(temp, denorm);
        TF.multiply(Math.pow(10, temp.getG() / 20));
        Details = ApproxName + " / Order " + Order + " / Max Q: " + String.format("%.2f", maxQobtained) + " / " + denormPrint + "%";
    }

    private double NTFminusAp(double x, double Aa) {
        return -20 * Math.log10(NTF.evaluateApproximationAtOmega(x).abs()) - Aa;
    }

    private double getDenormalizationRange(SuperTemplate temp, boolean inverseDenormalization) {
        //By using the Bisection Method, it gets the frequency at which the approximation equals Aa.
        double Aa = temp.getAa() * 0.99999;    //Para evitar errores en los valles de Cauer y Cheby II.
        if (inverseDenormalization == true) Aa = temp.getAp() * 0.99999;
        double a = 1;   // 1 rad/s    Wp Normalizado.
        double b = temp.getWan();
        int sa = (int) Math.signum(NTFminusAp(a, Aa));
        int sb = (int) Math.signum(NTFminusAp(b, Aa));
        if (sa == sb)
            return 1;  //La función no cumple plantilla, por lo cual no acepta un corrimiento de desnormalización.
        double maxIter = 100000;
        double tol = Math.pow((b / a), 1. / 10000);
        for (int i = 0; i < maxIter; i++) {
            double c = Math.sqrt(a * b);
            int sc = (int) Math.signum(NTFminusAp(c, Aa));
            if (sc == sa)
                a = c;  //b = b;
            else
                b = c;  //a = a;
            if (b / a < tol)
                break;
        }
        if (inverseDenormalization == true) return 1. / Math.sqrt(a * b);
        else return temp.getWan() / Math.sqrt(a * b);
    }

    ////////////////////////////////////APPROXIMATIONS//////////////////////////////////////////////

    private void Butter(SuperTemplate temp, int setOrder, double maxQ) {
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();
        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);

        this.Order = setOrder;
        if (maxQ > 0.5) {
            //<editor-fold defaultstate="collapsed" desc="Deduction of maxOrder given maxQ">
        /* MaxQobtained = 1 / 2sin(pi/2n) < MaxQ
           2sin(pi/2n) > 1/MaxQ
           sin(pi/2n) > 1/2MaxQ
           pi/2n > asin(1/2MaxQ)
           n < pi/2asin(1/2Q)
         */
            //</editor-fold>
            int maxOrder = (int) Math.floor(Math.PI / (2 * Math.asin(1. / (2 * maxQ))));
            if (setOrder == 0)
                this.Order = maxOrder;
            else this.Order = Math.min(setOrder, maxOrder);
        } else if (setOrder == 0)
            this.Order = (int) Math.ceil(Math.log10((Math.pow(10, Aa / 10) - 1) / (Math.pow(eps, 2))) / (2 * Math.log10(wan)));

        List<Complex> Poles = new ArrayList<>();

        double arg = Math.PI / 2 + Math.PI / (2 * this.Order);
        double module = Math.pow(1. / eps, 1. / this.Order);
        for (int i = 0; i < this.Order; i++) {
            double real = module * Math.cos(arg);
            double imag = module * Math.sin(arg);
            if (Math.abs(imag) < 10e-3) imag = 0;    //Floating point error correction.
            Poles.add(new Complex(real, imag));
            arg += Math.PI / this.Order;
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray, PolesArray);
        this.maxQobtained = 1. / (2 * Math.sin(Math.PI / (2 * this.Order)));
    }

    private void Cheby1(SuperTemplate temp, int setOrder, double maxQ) {
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();

        double eps = Math.sqrt(Math.pow(10, Ap / 10) - 1);
        this.Order = setOrder;
        if (this.Order == 0)
            this.Order = (int) Math.ceil(MathUtils.acosh(Math.sqrt(Math.pow(10, Aa / 10) - 1) / eps) / MathUtils.acosh(wan));

        List<Complex> Poles = new ArrayList<>();
        double beta = (1. / this.Order) * MathUtils.asinh(1. / eps);

        for (int i = 0; i < this.Order; i++) {
            double alpha = Math.PI * ((2. * i + 1) / (2 * this.Order));
            double imag = Math.cos(alpha) * Math.cosh(beta);
            double real = -Math.sin(alpha) * Math.sinh(beta);
            if (Math.abs(imag) < 10e-3) imag = 0;   //Floating point error correction.
            Poles.add(new Complex(real, imag));
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = new Complex[0];
        this.NTF = new TransferFunction(ZerosArray, PolesArray);
        double origin = this.NTF.getModuleAtOrigin();
        if (this.Order % 2 == 0) origin /= Math.pow(10, -Ap / 20);
        this.NTF.multiply(1. / origin);

        this.maxQobtained = Math.abs(1. / (2. * Math.sin(PolesArray[0].getArgument() - Math.PI / 2)));
        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Cheby1(temp, this.Order - 1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }

    private void Cheby2(SuperTemplate temp, int setOrder, double maxQ) {
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();

        double eps = 1. / Math.sqrt(Math.pow(10, Aa / 10) - 1);
        this.Order = setOrder;
        if (this.Order == 0)
            this.Order = (int) Math.ceil(MathUtils.acosh(1. / (Math.sqrt(Math.pow(10, Ap / 10) - 1) * eps)) / MathUtils.acosh(wan));

        List<Complex> Poles = new ArrayList<>();
        List<Complex> Zeros = new ArrayList<>();
        double beta = Math.abs((1. / this.Order) * MathUtils.asinh(1. / eps));

        for (int i = 0; i < this.Order; i++) {
            double alpha = Math.PI * ((2. * i + 1) / (2 * this.Order));
            double imag = Math.cos(alpha) * Math.cosh(beta);
            double real = -Math.sin(alpha) * Math.sinh(beta);
            if (Math.abs(imag) < 10e-3) imag = 0;   //Floating point error correction.
            Poles.add(new Complex(real, imag).reciprocal().multiply(wan));
            if (2 * i + 1 != this.Order) //alpha != pi/2
                Zeros.add(new Complex(0, wan / Math.cos(alpha)));
        }
        Complex[] PolesArray = Poles.toArray(new Complex[Poles.size()]);
        Complex[] ZerosArray = Zeros.toArray(new Complex[Zeros.size()]);
        this.NTF = new TransferFunction(ZerosArray, PolesArray);
        double origin = this.NTF.getModuleAtOrigin();
        this.NTF.multiply(1. / origin);

        this.maxQobtained = Math.abs(1. / (2. * Math.sin(PolesArray[0].getArgument() - Math.PI / 2)));
        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Cheby2(temp, this.Order - 1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }

    private void Legendre(SuperTemplate temp, int setOrder, double maxQ) {}

    private void Bessel(SuperTemplate temp, int setOrder, double maxQ) {
        double Ap = temp.getAp();
        double Aa = temp.getAa();
        double wan = temp.getWan();

        TransferFunction finalTf = new TransferFunction(new double[]{1}, new double[]{1});
        PolynomialFunction besselNum;
        PolynomialFunction besselDen;
        double[] testArray = {0};
        int i = 0;
        if(setOrder>15){
            this.Order=15;
        } else {
            this.Order = setOrder;
        }

        if (this.Order == 0) {            //If the order is not specified,
            final int MAX_ORDER = 15;            //Setting maximum order at 15
            for (i = 1; i <= MAX_ORDER; i++) {
                besselDen = GetNOrderBesselTF(i);
                testArray[0] = besselDen.getCoefficients()[0];
                besselNum = new PolynomialFunction(testArray);
                finalTf = new TransferFunction(besselNum, besselDen);

                // 20*(Math.log10(finalTf.evaluateApproximationAtOmega(1).abs())) < Ap  esto se fija si cumple plantilla para fp
                // 20*(Math.log10(finalTf.evaluateApproximationAtOmega(wan).abs())) > Aa esto, si cumple en fa

                if ((20 * (Math.log10(finalTf.evaluateApproximationAtOmega(1).abs())) < Ap)
                        && (20 * (Math.log10(finalTf.evaluateApproximationAtOmega(wan).abs())) > Aa)) break;
            }
            if(i==MAX_ORDER+1){i=15;}
            this.Order = i;
            this.NTF = finalTf;
        } else {        //If the order is specified,
            besselDen = GetNOrderBesselTF(this.Order);
            testArray[0] = besselDen.getCoefficients()[0];
            besselNum = new PolynomialFunction(testArray);
            finalTf = new TransferFunction(besselNum, besselDen);
            this.NTF = finalTf;
        }

        Complex[] polos = this.NTF.getPoles();  //Esto puede causar problemas si el orden es 1.
        double midQ;
        for (i = 0; i < polos.length; i++) {
            midQ = Math.abs(1. / (2. * Math.cos(polos[i].getArgument())));
            this.maxQobtained = Math.max(this.maxQobtained, midQ);
        }

        if (maxQ > 0.5 && this.maxQobtained > maxQ)
            Bessel(temp, this.Order - 1, maxQ);        //If maxQ is overflowed, retry with a minor order.
    }

    private void Bessel(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi) {/*
        //This Bessel aproximation function is the one that denormalizes using the group delay parameters

        //NAHUEL/AGUSTIN FALTA PEDIR UN W donde se quiere que finalize la tolerancia psi
        double Wgd = 20;
        double Wgdnorm = Wgd * delay;

        double[] relleno = {1};
        TransferFunction finalTf = new TransferFunction(relleno, relleno);
        PolynomialFunction besselNum;
        PolynomialFunction besselDen;
        double[] testArray = {0};
        this.Order = setOrder;
        if (this.Order == 0) {
            for (int i = 1; i <= 15; i++) {
                besselDen = GetNOrderBesselTF(i);
                testArray[0] = besselDen.getCoefficients()[0];
                besselNum = new PolynomialFunction(testArray);
                finalTf = new TransferFunction(besselNum, besselDen);
                //IF DEL RETARDO DE GRUPO
            }
        } else {
            besselDen = GetNOrderBesselTF(this.Order);
            testArray[0] = besselDen.getCoefficients()[0];
            besselNum = new PolynomialFunction(testArray);
            finalTf = new TransferFunction(besselNum, besselDen);
            this.NTF = finalTf;
        }*/
    }

    private PolynomialFunction GetNOrderBesselTF(int order) {
        if (order == 0) return new PolynomialFunction(new double[] {1});
        else if (order == 1) return new PolynomialFunction(new double[] {1, 1});
        else {
            double[] array = {2 * order - 1};
            double[] array2 = {0, 0, 1};
            /*Polinomios verdaderos de Bessel
            PolynomialFunction poly1 = (GetNOrderBesselTF(order - 1).multiply(new PolynomialFunction(array))).multiply(new PolynomialFunction(array2));
            PolynomialFunction poly2 = GetNOrderBesselTF(order - 2);
            */
            /*Polinomios inversos de Bessel*/
            PolynomialFunction poly1 = GetNOrderBesselTF(order - 1).multiply(new PolynomialFunction(array));
            PolynomialFunction poly2 = GetNOrderBesselTF(order - 2).multiply(new PolynomialFunction(array2));


            //La forumla es (2n-1)*x*Bn-1+Bn-2

            // o la otra que hay dando vueltas es (2*n-1)Bn-1+s^2*Bn-2
            return poly1.add(poly2);
        }
    }

    private void Gauss(SuperTemplate temp, int setOrder, double maxQ, double delay, double psi) {
/*        double[] testArray = {1};
        PolynomialFunction gaussDen;
        PolynomialFunction gaussNom = new PolynomialFunction(testArray);
        TransferFunction prueba;
        Complex[] polosPrueba;
        List<Complex> polosTransfer = new ArrayList<>();
        //Complex[] polosTrans, zerosTranf;
        this.Order = setOrder;
        int i = 0;
        int j = 0;
        if (this.Order == 0) {
            for (i = 1; i <= 10; i++) {
                int denOrder = 2 * i;
                double[] denominador = new double[denOrder + 1];
                denominador[0] = 1;
                for (j = 1; j <= denOrder; j++) {
                    if ((j % 2) == 1) {   //valores impares los coeficientes dan 0
                        denominador[j] = 0;
                        continue;
                    } else        // los valores pares dan (s^i)/(i/2)!
                        denominador[j] = Math.pow(delay, j) * (1. / (double) MathUtils.factorial((j / 2)));
                }
                //////////////barro todos los polos obtenidos y veo simplemente los de el semiplano izquierdo///////////////
                prueba = new TransferFunction(testArray, denominador);
                polosPrueba = prueba.getPoles();
                for (j = 0; j < polosPrueba.length; j++) {
                    if (polosPrueba[j].getReal() >= 0) continue;
                    else polosTransfer.add(polosPrueba[j]);
                }
                Complex[] PolesArray = polosTransfer.toArray(new Complex[polosTransfer.size()]);
                Complex[] ZerosArray = new Complex[0];
                this.NTF = new TransferFunction(ZerosArray, PolesArray); //Acá tengo la transferFunction

                if (CUMPLERETARDODEGRUPO) break;
            }
            this.Order = i;

        } else { // Si no me pasan el orden
            int denOrder = 2 * this.Order;
            double[] denominador = new double[denOrder + 1];
            denominador[0] = 1;
            for (i = 1; i <= denOrder; i++) {
                if ((i % 2) == 1) {   //valores impares los coeficientes dan 0
                    denominador[i] = 0;
                    continue;
                } else        // los valores pares dan (s^i)/(i/2)!
                    denominador[i] = Math.pow(delay, i) * (1. / (double) MathUtils.factorial((i / 2)));
            }
            //////////////barro todos los polos obtenidos y veo simplemente los de el semiplano izquierdo///////////////
            prueba = new TransferFunction(testArray, denominador);
            polosPrueba = prueba.getPoles();
            for (j = 0; j < polosPrueba.length; j++) {
                if (polosPrueba[j].getReal() >= 0) continue;
                else polosTransfer.add(polosPrueba[j]);
            }
            //Acá ya sacó los polos de la transferencia

            Complex[] PolesArray = polosTransfer.toArray(new Complex[polosTransfer.size()]);
            Complex[] ZerosArray = new Complex[0];
            this.NTF = new TransferFunction(ZerosArray, PolesArray);
            //Lo pase recien a TF
        }
*/    }
}
