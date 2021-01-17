package java2.application_target_list.core.responses.target;



import java2.application_target_list.core.domain.Target;

import java.util.List;


public class GetAllTargetsResponse extends TargetCoreResponse {

    private final List<Target> targetList;


    public GetAllTargetsResponse(List<Target> targetList) {
        this.targetList = targetList;
    }

    public List<Target> getTargetList() {
        return targetList;
    }
}
