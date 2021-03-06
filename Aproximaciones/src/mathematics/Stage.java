package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.GenericUtils;
import tclib.TransferFunction;

import java.util.Arrays;
import java.util.List;

public class Stage {
    public static enum filterType {LOWPASS2, HIGHPASS2, BANDPASS, BANDREJ, LOWPASS1, HIGHPASS1 }
    private static final List<String> lowpass2List = Arrays.asList("LPAckerberg Mossberg", "LPFleischer Tow", "LPKerwin Huelsman Newcomb", "LPRauch", "LPSallen", "LPTow Thomas");
    private static final List<String> highpass2List = Arrays.asList("HPFleischer Tow", "HPKerwin Huelsman Newcomb", "HPRauch", "HPSallen");
    private static final List<String> bandpassList = Arrays.asList("BPAckerberg Mossberg", "BPFleischer Tow", "BPKerwin Huelsman Newcomb", "BPRauch", "BPSallen", "BPTow Thomas");
    private static final List<String> bandrejectList = Arrays.asList("BSAckerberg Mossberg", "BSFleischer Tow", "BSKerwin Huelsman Newcomb");
    private static final List<String> lowpass1List = Arrays.asList("LPRC");
    private static final List<String> highpass1List = Arrays.asList("HPRC");

    private filterType type;
    private Complex[] poles;
    private Complex[] zeros;
    private double gain;
    private TransferFunction TF;
    private String details;
    public Stage(TransferFunction TF) {
        this.TF = new TransferFunction(TF);
        this.poles = TF.getPoles();
        this.zeros = TF.getZeros();
        for ( int i = 0; i < poles.length; i++ ) {
            if ( i != 0 ) details = details + " + ";
            details = details + GenericUtils.getPZString(poles[i], true);
        }
        for ( Complex x : zeros ) {
            details = details + " + " + GenericUtils.getPZString(x, false);
        }
    }
    public Stage(Complex p1) { this(p1, Complex.INF, Complex.INF, Complex.INF, 1); }
    public Stage(Complex p1, Complex z1) { this(p1, z1, Complex.INF, Complex.INF, 1); }
    public Stage(Complex p1, Complex z1, Complex p2) { this(p1, z1, p2, Complex.INF, 1); }
    public Stage(Complex p1, Complex z1, Complex p2, Complex z2) { this(p1, z1, p2, z2, 1); }
    public Stage(Complex p1, Complex z1, Complex p2, Complex z2, double g) {
        boolean twoPoles = false, noZeros = false, singleZero = false, doubleOriginZeros = false;
        if(p2.isInfinite()) {
            if (p1.isInfinite())
                poles = new Complex[]{};
            else {
                if (p1.getImaginary() == 0)
                    poles = new Complex[]{p1};
                else {
                    poles = new Complex[]{p1, p1.conjugate()};
                    twoPoles = true;
                }
                details = GenericUtils.getPZString(p1, true);
            }
        }
        else if (p1.isInfinite()) {
            if (p2.getImaginary() == 0)
                poles = new Complex[]{p2};
            else {
                poles = new Complex[]{p2, p2.conjugate()};
                twoPoles = true;
            }
            details = GenericUtils.getPZString(p2, true);
        }
        else {
            poles = new Complex[]{p1, p2};
            details = GenericUtils.getPZString(p1, true) + " + " + GenericUtils.getPZString(p2, true);
            twoPoles = true;
        }

        if(z2.isInfinite()) {
            if (z1.isInfinite()) {
                zeros = new Complex[]{};
                noZeros = true;
            } else {
                if (z1.getImaginary() == 0) {
                    zeros = new Complex[]{z1};
                    singleZero = true;
                }
                else zeros = new Complex[]{z1, z1.conjugate()};
                details = details + " + " + GenericUtils.getPZString(z1, false);
                System.out.println(details);
            }
        }
        else if (z1.isInfinite()) {
            if (z2.getImaginary() == 0) {
                zeros = new Complex[]{z2};
                singleZero = true;
            }
            else zeros = new Complex[]{z2, z2.conjugate()};
            details = details + " + " + GenericUtils.getPZString(z2, false);
        }
        else if (z1.abs() == 0 && z2.abs() == 0) {
            zeros = new Complex[]{z1, z2};
            doubleOriginZeros = true;
            details = details + " + " + GenericUtils.getPZString(z1, false) + " + " + GenericUtils.getPZString(z1.conjugate(), false);
        } else {
            zeros = new Complex[]{z1, z1.conjugate()};
            details = details + " + " + GenericUtils.getPZString(z1, false) + " + " + GenericUtils.getPZString(z1.conjugate(), false);
        }
        TF = new TransferFunction(zeros, poles);
        setG(g);

        if(twoPoles) {
            if(noZeros) type = filterType.LOWPASS2;
            else if(singleZero) type = filterType.BANDPASS;
            else if(doubleOriginZeros) type = filterType.HIGHPASS2;
            else type = filterType.BANDREJ;
        } else {
            if(noZeros) type = filterType.LOWPASS1;
            else type = filterType.HIGHPASS1;
        }
    }

    private double getG() {
        double wp;
        double[] denom = TF.getDenominatorCopy().getCoefficients();
        if(denom.length == 3)
            wp = Math.sqrt(denom[0]/denom[2]);
        else if(denom.length == 2)
            wp = denom[0] / denom[1];
        else return 0;

        double[] numer = TF.getNumeratorCopy().getCoefficients();
        if(numer.length == 1)
            return numer[0] / (denom[denom.length-1]) / Math.pow(wp,denom.length-1);
        else if(numer.length == 2) {
            System.out.println(numer[1] / (wp * denom[denom.length - 1]));
            return numer[1] / (denom[denom.length-1])/Math.pow(wp,denom.length-2);
        }
        else if(numer.length == 3)
            return numer[2]/denom[denom.length-1];
        else return 0;
    }

    public void setG(double newG) {
        double g = getG();
        TF.multiply(newG/g);
    }
    public double getQ() { return GenericUtils.getQ(poles[0]); }
    public double getGdB() { return 20.*Math.log10(getG()); }
    public TransferFunction getTF() { return TF; }
    public String getDetails() { return details; }
    public Complex[] getZeros() { return zeros; }
    public Complex[] getPoles() { return poles; }
    public filterType getType() { return type; }
    public List<String> getList() {
        if(type == filterType.LOWPASS2) return lowpass2List;
        else if(type == filterType.HIGHPASS2) return highpass2List;
        else if(type == filterType.BANDPASS) return bandpassList;
        else if(type == filterType.BANDREJ) return bandrejectList;
        else if(type == filterType.LOWPASS1) return lowpass1List;
        else return highpass1List;
    }
}