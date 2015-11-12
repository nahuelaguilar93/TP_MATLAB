package tclib.templates;

import static java.lang.Math.sqrt;

public class BandpassTemplate extends SuperTemplate {

    private final double wpp;
    private final double wap;
    private final double wpm;
    private final double wam;
    private final double B;
    private final double wo;

    public double getWpp() { return wpp; }
    public double getWap() { return wap; }
    public double getWpm() { return wpm; }
    public double getWam() { return wam; }
    public double getB() { return B; }
    public double getWo() { return wo; }

    public BandpassTemplate(double Ap, double Aa, double G, double wpm, double wam, double wpp, double wap) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.G = G;
        this.wpp = wpp;
        this.wap = wap;
        this.wpm = wpm;
        this.wam = wam;
        this.wo = sqrt(wpm*wpp);
        this.B = (wpp-wpm)/wo;
        normalize();
    }

    @Override
    protected void normalize() {
        if(wpm*wpp > wam*wap) {
            double wam2 = wpm * wpp / wap;
            this.wan = (wap-wam2)/(wpp-wpm);
        } else {
            double wap2 = wpm * wpp / wam;
            this.wan = (wap2 - wam) / (wpp - wpm);
        }
    }

    @Override
    public boolean equals(SuperTemplate t) {
        if(t instanceof BandpassTemplate) {
            BandpassTemplate that = (BandpassTemplate) t;
            if (this.Ap == that.Ap && this.Aa == that.Aa && this.G == that.G && this.wpp == that.wpp &&
                    this.wap == that.wap && this.wpm == that.wpm && this.wam == that.wam)
                return true;
            else return false;
        } else return false;
    }

    @Override
    public double getWmax() { return wap; }
    @Override
    public double getWmin() { return wam; }
    @Override
    public double[] getBand() { return new double[] {wpm, wpp}; }
}