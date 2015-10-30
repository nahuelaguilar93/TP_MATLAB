package tclib.templates;

/**
 * Created by kdewald on 8/10/15.
 */
public class BandrejectTemplate extends SuperTemplate {

    public double bandwidth;
    public double centerFrequency;

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.BANDREJECT;
    }
}
