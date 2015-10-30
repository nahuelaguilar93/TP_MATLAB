package tclib.templates;

/**
 * Created by kdewald on 8/10/15.
 */
public class SuperTemplate {

    public enum TemplateTypeEnum {
        NORMALIZED,
        LOWPASS,
        HIGHPASS,
        BANDPASS,
        BANDREJECT,
        DELAY,
        UNDEFINED,
    }
    TemplateTypeEnum templateType;
    public TemplateTypeEnum getTemplateType(){
        return templateType;
    }
    //public abstract Normalize();
}
