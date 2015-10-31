package tclib.templates;

public class HighpassTemplate extends SuperTemplate {

    public final double wp;
    public final double wa;

    public double getWp() { return wp; }
    public double getWa() { return wa; }

    public HighpassTemplate(double wp, double wa, double Ap, double Aa) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.wp = wp;
        this.wa = wa;
        Normalize();
    }

    @Override
    protected void Normalize() {
        this.wan = wp/wa;
    }
}