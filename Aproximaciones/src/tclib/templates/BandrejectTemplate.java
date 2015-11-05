package tclib.templates;

import static java.lang.Math.sqrt;

public class BandrejectTemplate extends SuperTemplate {

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

    public BandrejectTemplate(double wpm, double wam, double wpp, double wap, double Ap, double Aa) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.wpp = wpp;
        this.wap = wap;
        this.wpm = wpm;
        this.wam = wam;
        this.wo = sqrt(wam*wap);
        this.B = (wap-wam)/wo;
        Normalize();
    }

    @Override
    protected void Normalize() {
        if(wam*wap > wpm*wpp) {
            double wpm2 = wam * wap / wpp;
            this.wan = (wpp-wpm2)/(wap-wam);
        } else {
            double wpp2 = wam * wap / wpm;
            this.wan = (wpp2-wpm)/(wap-wam);
        }
    }
}