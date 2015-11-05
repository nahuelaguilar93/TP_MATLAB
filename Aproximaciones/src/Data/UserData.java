package Data;

import mathematics.Approximation;
import tclib.templates.LowpassTemplate;
import tclib.templates.SuperTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEGU on 7/10/2015.
 */

public class UserData {
    public List<Approximation> ApproximationList = new ArrayList<>();
    public SuperTemplate CurrentTemplate = new LowpassTemplate(2*Math.PI*1000, 2*Math.PI*1200, 3, 10);
}
