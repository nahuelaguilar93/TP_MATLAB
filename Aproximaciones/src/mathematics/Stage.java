package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;

/**
 * Created by Nahuel on 7/11/2015.
 */
public class Stage {
    private Complex[] poles;
    private Complex[] zeros;
    private double gain;
    private TransferFunction TF;
    private String details;
    public Stage(Complex p) { this(p, Complex.INF, Complex.INF, 0); }
    public Stage(Complex p, Complex z1) { this(p, z1, Complex.INF, 0); }
    public Stage(Complex p, Complex z1, Complex z2) { this(p, z1, z2, 0); }
    public Stage(Complex p, Complex z1, Complex z2, double g) {
        if(p.getImaginary() == 0)
            poles = new Complex[]{p};
        else poles = new Complex[]{p, p.conjugate()};
        if(z2.isInfinite()) {
            if (z1.isInfinite())
                zeros = new Complex[]{};
            else if (z1.getImaginary() == 0)
                zeros = new Complex[]{z1};
            else zeros = new Complex[]{z1, z1.conjugate()};
        }
        else if (z1.isInfinite()) {
            if (z2.getImaginary() == 0)
                zeros = new Complex[]{z2};
            else zeros = new Complex[]{z2, z2.conjugate()};
        }
        else zeros = new Complex[]{z1,z2};
        this.gain = g;
        TF = new TransferFunction(poles, zeros);
    }

    public TransferFunction getTF() { return TF; }
    public String getDetails() { return "AGUS PUTOOOO"; }
    public Complex[] getZeros() { return zeros; }
    public Complex[] getPoles() { return poles; }
}