package tclib.templates;

import static java.lang.Math.sqrt;

public class BandpassTemplate extends SuperTemplate {

    public final double wpp;
    public final double wap;
    public final double wpm;
    public final double wam;
    public final double B;
    public final double wo;

    public double getWpp() { return wpp; }
    public double getWap() { return wap; }
    public double getWpm() { return wpm; }
    public double getWam() { return wam; }
    public double getB() { return B; }
    public double getWo() { return wo; }

    public BandpassTemplate(double wpm, double wam, double wpp, double wap, double Ap, double Aa) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.wpp = wpp;
        this.wap = wap;
        this.wpm = wpm;
        this.wam = wam;
        this.wo = sqrt(wpm*wpp);
        this.B = (wpp-wpm)/wo;
        Normalize();
    }

    @Override
    protected void Normalize() {
        if(wpm*wpp > wam*wap) {
            double wam2 = wpm * wpp / wap;
            this.wan = (wap-wam2)/(wpp-wpm);
        } else {
            double wap2 = wpm * wpp / wam;
            this.wan = (wap2 - wam) / (wpp - wpm);
        }
    }
}