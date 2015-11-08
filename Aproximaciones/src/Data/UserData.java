package Data;

import mathematics.Approximation;
import mathematics.Stage;
import org.apache.commons.math3.complex.Complex;
import tclib.TransferFunction;
import tclib.templates.LowpassTemplate;
import tclib.templates.SuperTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private List<Approximation> ApproximationList = new ArrayList<>();
    private SuperTemplate CurrentTemplate = new LowpassTemplate(3., 13., 6., 1, 1.2);
    private int selection = -1;
    private TransferFunction transferFunction = new TransferFunction(new double[]{1},new double[]{1});
    private List<Complex> unmatchedPoles = new ArrayList<>();
    private List<Complex> unmatchedZeros = new ArrayList<>();
    private List<Stage> stageList = new ArrayList<>();

    public List<Approximation> getApproximationList() { return ApproximationList; }
    public SuperTemplate getCurrentTemplate() { return CurrentTemplate; }
    public void setCurrentTemplate(SuperTemplate currentTemplate) { CurrentTemplate = currentTemplate; }
    public int getSelection() { return selection; }
    public void setSelection(int selection) { this.selection = selection; }
    public TransferFunction getTransferFunction() { return transferFunction; }
    public void setTransferFunction(TransferFunction t) { transferFunction = t; }
    public List<Complex> getUnmatchedPoles() { return unmatchedPoles; }
    public List<Complex> getUnmatchedZeros() { return unmatchedZeros; }
    public List<Stage> getStageList() { return stageList; }
}
