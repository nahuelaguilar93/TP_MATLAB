package tclib.templates;

public class LowpassTemplate extends SuperTemplate {

    public final double wp;
    public final double wa;

    public double getWp() { return wp; }
    public double getWa() { return wa; }

    public LowpassTemplate(double wp, double wa, double Ap, double Aa) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.wp = wp;
        this.wa = wa;
        Normalize();
    }

    @Override
    protected void Normalize() {
        this.wan = wa/wp;
    }
}