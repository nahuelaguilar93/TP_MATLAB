package secondstage;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

class Singleton_S2 {
    private static final Singleton_S2 INSTANCE = new Singleton_S2();
    private Singleton_S2() {}
    public static Singleton_S2 getInstance() {
        return INSTANCE;
    }

    private List<Complex> poleArray = new ArrayList<>();
    private List<Complex> zeroArray = new ArrayList<>();

    public List<Complex> getPoleArray() {
        if (poleArray == null)
            poleArray = new ArrayList<>();
        return poleArray;
    }
    public List<Complex> getZeroArray() {
        if (zeroArray == null)
            zeroArray = new ArrayList<>();
        return zeroArray;
    }
}
