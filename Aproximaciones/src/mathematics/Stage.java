package mathematics;

import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;

/**
 * Created by Nahuel on 7/11/2015.
 */
public class Stage {
    private Complex pole;
    private Complex zero;
    private double gain;
    private TransferFunction TF;
    public Stage(Complex p) { this(p, Complex.INF, 0); }
    public Stage(Complex p, Complex z) { this(p, z, 0); }
    public Stage(Complex p, Complex z, double g) {
        this.pole = p;
        this.zero = z;
        this.gain = g;
        if (z.isInfinite())
            TF = new TransferFunction(new Complex[] {p}, new Complex[0]);
        TF = new TransferFunction(new Complex[] {p}, new Complex[] {z});
    }

    public Complex getZero() { return zero; }
    public Complex getPole() { return pole; }
}