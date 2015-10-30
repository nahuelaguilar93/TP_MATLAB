package tclib;

import tclib.templates.*;

/**
 * Created by kdewald on 8/10/15.
 */
public abstract class Approximation {

    public TransferFunction getDenormalized(LowpassTemplate lowpassTemplate)
    {
        return null;
    }

    public TransferFunction getDenormalized(HighpassTemplate highpassTemplate)
    {
        return null;
    }

    public TransferFunction getDenormalized(BandpassTemplate bandpassTemplate)
    {
        return null;
    }

    public TransferFunction getDenormalized(BandrejectTemplate bandrejectTemplate)
    {
        return null;
    }

    public TransferFunction getDenormalized(DelayTemplate delayTemplate)
    {
        return null;
    }

    //que mierda hace esta función Acá? La Normalización y desnormalización de una transferencia son independientes de las aproximaciones
    //protected abstract TransferFunction getNormalizedTransferFunction();

}
