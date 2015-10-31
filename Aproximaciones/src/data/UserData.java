package data;

import java.util.*;

import tclib.*;
import tclib.templates.LowpassTemplate;
import tclib.templates.SuperTemplate;

/**
 * Created by NEGU on 7/10/2015.
 */

public class UserData {

    public static List<Approximation> ApproximationList = new ArrayList<>();
    public static SuperTemplate CurrentTemplate = new LowpassTemplate(2*Math.PI*1000, 2*Math.PI*3000, 3, 10);

    private static SuperTemplate temp;
}
