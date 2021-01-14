package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.CoreError;


import java.util.List;

public class AddTargetResponse extends TargetCoreResponse {

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
