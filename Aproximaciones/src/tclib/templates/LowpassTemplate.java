package tclib.templates;

import tclib.TransferFunction;

/**
 * Created by kdewald on 8/10/15.
 */
public class LowpassTemplate extends AbstractTemplate {

    public double omegaP;
    public double omegaA;
    public double attenuationP;
    public double attenuationA;

    public LowpassTemplate(double omegaP, double omegaA, double attenuationP, double attenuationA) {
        this.omegaP = omegaP;
        this.omegaA = omegaA;
        this.attenuationP = attenuationP;
        this.attenuationA = attenuationA;
    }

    @Override
    public TemplateType getTemplateType() {
        return TemplateType.LOWPASS;
    }

}
