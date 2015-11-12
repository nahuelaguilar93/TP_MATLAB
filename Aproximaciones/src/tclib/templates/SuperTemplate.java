package tclib.templates;

public abstract class SuperTemplate implements TemplatesInterface {
    protected double wan;   //rad/s
    protected double Ap;    //dB
    protected double Aa;    //dB
    protected double G;     //dB

    public double getWan() { return wan; }
    public double getAp() { return Ap; }
    public double getAa() { return Aa; }
    public double getG() { return G; }

    protected abstract void normalize();   //To be called by each subclass initialization.
    public abstract boolean equals(SuperTemplate t);

    public abstract double getWmax();
    public abstract double getWmin();
    public abstract double[] getBand();
}