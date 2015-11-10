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
    SuperTemplate temp;
    TransferFunction finalTransferFunction;

    StageDisposition(List<Stage> stages) {
        stageList = stages;
        temp = Singleton.getInstance().getUserData().getCurrentTemplate();
        finalTransferFunction = Singleton.getInstance().getUserData().getTransferFunction();
    }

    public void minimizeDRL(){

    }

}
