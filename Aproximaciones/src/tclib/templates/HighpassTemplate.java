package tclib.templates;

public class HighpassTemplate extends SuperTemplate {

    private final double wp;
    private final double wa;

    public double getWp() { return wp; }
    public double getWa() { return wa; }

    public HighpassTemplate(double Ap, double Aa, double G, double wp, double wa) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.G = G;
        this.wp = wp;
        this.wa = wa;
        normalize();
    }

    @Override
    protected void normalize() {
        this.wan = wp/wa;
    }

    @Override
    public boolean equals(SuperTemplate t) {
        if(t instanceof HighpassTemplate) {
            HighpassTemplate that = (HighpassTemplate) t;
            if (this.Ap == that.Ap && this.Aa == that.Aa && this.G == that.G && this.wp == that.wp && this.wa == that.wa)
                return true;
            else return false;
        } else return false;
    }
}