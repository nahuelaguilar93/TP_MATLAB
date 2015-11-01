package tclib.templates;

public abstract class SuperTemplate implements TemplatesInterface {
    public double wan;  //rad/s
    public double Ap;   //dB
    public double Aa;   //dB

    public double getWan() { return wan; }
    public double getAp() { return Ap; }
    public double getAa() { return Aa; }

    protected abstract void Normalize();   //To be called by each subclass initialization.
}