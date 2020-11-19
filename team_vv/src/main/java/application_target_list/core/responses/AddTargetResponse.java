package application_target_list.core.responses;

import application_target_list.Target;


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
