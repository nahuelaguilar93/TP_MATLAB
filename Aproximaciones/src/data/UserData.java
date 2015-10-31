package data;

import java.util.*;

import tclib.*;
import tclib.templates.AbstractTemplate;
import tclib.templates.BandpassTemplate;

/**
 * Created by NEGU on 7/10/2015.
 */

public class UserData {
    public static List<TransferFunction> ApproximationList = new ArrayList<>(); //Will be treated as LIFO? or how?
    public static AbstractTemplate CurrentTemplate = null;

}
