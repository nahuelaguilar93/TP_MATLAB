package Data;

import mathematics.Approximation;
import tclib.templates.LowpassTemplate;
import tclib.templates.SuperTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private List<Approximation> ApproximationList = new ArrayList<>();
    private SuperTemplate CurrentTemplate = new LowpassTemplate(3., 13., 6., 1, 1.2);
    private int selection = -1;

    public List<Approximation> getApproximationList() { return ApproximationList; }
    public SuperTemplate getCurrentTemplate() { return CurrentTemplate; }
    public void setCurrentTemplate(SuperTemplate currentTemplate) { CurrentTemplate = currentTemplate; }
    public int getSelection() { return selection; }
    public void setSelection(int selection) { this.selection = selection; }
}
