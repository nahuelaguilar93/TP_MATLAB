package tclib.templates;

/**
 * Created by kdewald on 8/10/15.
 */
public abstract class AbstractTemplate {

    public enum TemplateType{
        NORMALIZED,
        LOWPASS,
        HIGHPASS,
        BANDPASS,
        BANDREJECT,
        DELAY,
    }
    public abstract TemplateType getTemplateType();
    //public abstract Normalize();

}
