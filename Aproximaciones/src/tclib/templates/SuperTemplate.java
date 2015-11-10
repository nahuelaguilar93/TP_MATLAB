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

    public double getWmax() {
        if (this instanceof LowpassTemplate)
            return ((LowpassTemplate) this).getWa();
        else if (this instanceof HighpassTemplate)
            return ((HighpassTemplate) this).getWp();
        else if (this instanceof BandpassTemplate)
            return ((BandpassTemplate) this).getWap();
        else if (this instanceof BandrejectTemplate)
            return ((BandrejectTemplate) this).getWpp();
        else return ((DelayTemplate) this).getWa();
    }

    double getWmin() {
        if (this instanceof LowpassTemplate)
            return  ((LowpassTemplate) this).getWp();
        else if (this instanceof HighpassTemplate)
            return ((HighpassTemplate) this).getWa();
        else if (this instanceof BandpassTemplate)
            return ((BandpassTemplate) this).getWam();
        else if (this instanceof  BandrejectTemplate)
            return ((BandrejectTemplate) this).getWpm();
        else return ((DelayTemplate) this).getWp();
    }
}