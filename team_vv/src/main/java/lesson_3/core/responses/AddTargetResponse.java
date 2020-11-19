package lesson_3.core.responses;


import lesson_3.Target;

import java.util.List;

public class AddTargetResponse extends CoreResponse {

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
