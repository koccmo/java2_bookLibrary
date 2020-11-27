package application_target_list.core.responses;

import application_target_list.core.database.Target;
import java.util.List;

public class SearchTargetByDescriptionResponse extends CoreResponse{

    private List<Target> targetList;

    public SearchTargetByDescriptionResponse(List<CoreError> errorList, List<Target> targetList) {
        super(errorList);
        this.targetList = targetList;
    }

    public List<Target> getTargetList() {
        return targetList;
    }
}
