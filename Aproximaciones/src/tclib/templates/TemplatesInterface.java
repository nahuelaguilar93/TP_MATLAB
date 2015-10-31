package tclib.templates;

/**
 * Created by NAHUEL on 31/10/2015.
 */
public interface TemplatesInterface {
    static String[] templateStrings = {"Low Pass", "High Pass", "Band Pass", "Band Reject", "Delay"};
    enum templateType {LOWPASS, HIGHPASS, BANDPASS, BANDREJECT, DELAY}

}
