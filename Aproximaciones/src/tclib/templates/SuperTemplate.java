package tclib.templates;

public abstract class SuperTemplate {
    public double wan;
    public double Ap;
    public double Aa;

    protected abstract void Normalize();   //To be called by each subclass initialization.
}

/*
    double psi;     //Zero if it's not defined
    int maxOrder;   //Zero if it's not defined
    double maxQ;

*/