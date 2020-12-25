package java2.application_target_list.core.responses;


import java2.application_target_list.core.domain.Target;

import java.util.List;

public class SearchTargetByNameResponse extends CoreResponse {

    private  List<Target> targetsList;

    public SearchTargetByNameResponse(List<CoreError> errorList, List<Target> targetsList) {
        super(errorList);
        this.targetsList = targetsList;
    }

    public List<Target> getTargetsList() {
        return targetsList;
    }
}
