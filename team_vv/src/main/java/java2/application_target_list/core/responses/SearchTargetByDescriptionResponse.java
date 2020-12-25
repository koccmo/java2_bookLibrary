package java2.application_target_list.core.responses;

import java2.application_target_list.core.domain.Target;

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
