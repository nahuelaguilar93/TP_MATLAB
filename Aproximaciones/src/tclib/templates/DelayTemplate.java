package tclib.templates;

public class DelayTemplate extends SuperTemplate {

    private final double wp;
    private final double wa;
    private final double delay;
    private final double psi;

    public double getWp() { return wp; }
    public double getWa() { return wa; }
    public double getDelay() { return delay; }
    public double getPsi() { return psi; }

    public DelayTemplate(double Ap, double Aa, double G, double wp, double wa, double delay, double psi) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.G = G;
        this.wp = wp;
        this.wa = wa;
        this.delay = delay;
        this.psi = psi;
        normalize();
    }

    @Override
    protected void normalize() {
        this.wan = wa/wp;
    }

    @Override
    public boolean equals(SuperTemplate t) {
        if(t instanceof DelayTemplate) {
            DelayTemplate that = (DelayTemplate) t;
            if (this.Ap == that.Ap && this.Aa == that.Aa && this.G == that.G && this.wp == that.wp &&
                    this.wa == that.wa && this.delay == that.delay && this.psi == that.psi)
                return true;
            else return false;
        } else return false;
    }
}