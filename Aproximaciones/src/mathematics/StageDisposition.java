package mathematics;

import Data.Singleton;
import tclib.TransferFunction;
import tclib.templates.SuperTemplate;

import java.util.List;

/**
 * Created by Nahuel on 10/11/2015.
 */
public class StageDisposition {
    private List<Stage> stageList;
    public List<Stage> getStageList() { return stageList; };
    private double wMax;
    private double wMin;
    SuperTemplate temp;
    TransferFunction finalTransferFunction;

    StageDisposition(List<Stage> stages) {
        stageList = stages;
        SuperTemplate temp = Singleton.getInstance().getUserData().getCurrentTemplate();
        wMax = temp.getWmax();
        wMin = temp.getWmin();
        finalTransferFunction = Singleton.getInstance().getUserData().getTransferFunction();
    }

    public void minimizeDRL(){

    }

}