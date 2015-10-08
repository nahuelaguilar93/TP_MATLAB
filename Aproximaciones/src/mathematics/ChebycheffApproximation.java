package mathematics;

import tclib.Approximation;
import tclib.TransferFunction;

/**
 * Created by Augusto on 8/10/2015.
 */
public class ChebycheffApproximation extends Approximation {


    @Override   //this is an override of the function that Approximation is asking
    protected TransferFunction getNormalizedTransferFunction() {
        return null;
    }
}
