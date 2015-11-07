package tclib.templates;

import static java.lang.Math.sqrt;

public class BandrejectTemplate extends SuperTemplate {

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

    public BandrejectTemplate(double Ap, double Aa, double G, double wpm, double wam, double wpp, double wap) {
        this.Ap = Ap;
        this.Aa = Aa;
        this.G = G;
        this.wpp = wpp;
        this.wap = wap;
        this.wpm = wpm;
        this.wam = wam;
        this.wo = sqrt(wam*wap);
        this.B = (wap-wam)/wo;
        normalize();
    }

    @Override
    protected void normalize() {
        if(wam*wap > wpm*wpp) {
            double wpm2 = wam * wap / wpp;
            this.wan = (wpp-wpm2)/(wap-wam);
        } else {
            double wpp2 = wam * wap / wpm;
            this.wan = (wpp2-wpm)/(wap-wam);
        }
    }

    @Override
    public boolean equals(SuperTemplate t) {
        if(t instanceof BandrejectTemplate) {
            BandrejectTemplate that = (BandrejectTemplate) t;
            if (this.Ap == that.Ap && this.Aa == that.Aa && this.G == that.G && this.wpp == that.wpp &&
                    this.wap == that.wap && this.wpm == that.wpm && this.wam == that.wam)
                return true;
            else return false;
        } else return false;
    }
}