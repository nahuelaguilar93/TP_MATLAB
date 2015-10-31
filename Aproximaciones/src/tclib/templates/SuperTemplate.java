package tclib.templates;

public abstract class SuperTemplate {
    public double wan;
    public double Ap;
    public double Aa;

    public double getWan() { return wan; }
    public double getAp() { return Ap; }
    public double getAa() { return Aa; }

    protected abstract void Normalize();   //To be called by each subclass initialization.
}