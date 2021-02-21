package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class GetTargetResponse extends TargetCoreResponse{

    private Target target;

    public GetTargetResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetTargetResponse(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }
}
