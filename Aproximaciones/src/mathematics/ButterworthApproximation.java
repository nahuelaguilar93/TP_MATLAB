package mathematics;
import tclib.*; //import of the mathematics library
/**
 * Created by Augusto on 8/10/2015.
 */
public class ButterworthApproximation extends Approximation {

    public ButterworthApproximation() { //en el constructor se tiene que pedir la user Data, porque se necesita la plantilla desnormalizada, y el orden o Q máximo...
    }

    @Override   //this is an override of the function that Approximation is asking
    protected TransferFunction getNormalizedTransferFunction() {
        return null;
    }
}
