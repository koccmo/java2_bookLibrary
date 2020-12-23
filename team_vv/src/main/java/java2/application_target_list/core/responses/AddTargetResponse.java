package java2.application_target_list.core.responses;

import java2.application_target_list.core.domain.Target;


import java.util.List;

public class AddTargetResponse extends CoreResponse{

    private Target newTarget;


    public AddTargetResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddTargetResponse(Target newTarget) {
        this.newTarget = newTarget;
    }

    public Target getNewTarget() {
        return newTarget;
    }
}
