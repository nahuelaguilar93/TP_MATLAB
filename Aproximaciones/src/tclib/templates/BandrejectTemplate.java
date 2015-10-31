package tclib.templates;

import static java.lang.Math.sqrt;

public class BandrejectTemplate extends SuperTemplate {

    public   final double wpp;
    public final double wap;
    public final double wpm;
    public final double wam;
    public final double B;
    public final double wo;

    public BandrejectTemplate(double wpp, double wap, double wpm, double wam, double Ap, double Aa) {
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
        this.wan = (wpp-wpm)/(wap-wam);
    }
}