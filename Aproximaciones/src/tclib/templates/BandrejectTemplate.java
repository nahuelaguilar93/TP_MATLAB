package tclib.templates;

/**
 * Created by kdewald on 8/10/15.
 */
public class BandrejectTemplate extends AbstractTemplate {

    public double bandwidth;
    public double centerFrequency;

    @Override
    public TemplateType getTemplateType() {
        return TemplateType.BANDREJECT;
    }
}
