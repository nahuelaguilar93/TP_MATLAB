package tclib.templates;

/**
 * Created by kdewald on 8/10/15.
 */
public class HighpassTemplate extends SuperTemplate {

    public double omegaP;
    public double omegaA;
    public double attenuationP;
    public double attenuationA;

    public HighpassTemplate(double omegaP, double omegaA, double attenuationP, double attenuationA) {
        this.omegaP = omegaP;
        this.omegaA = omegaA;
        this.attenuationP = attenuationP;
        this.attenuationA = attenuationA;
    }

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.HIGHPASS;
    }
}
