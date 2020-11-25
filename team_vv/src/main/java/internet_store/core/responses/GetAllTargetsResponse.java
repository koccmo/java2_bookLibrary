package internet_store.core.responses;



import internet_store.Target;

import java.util.List;


public class GetAllTargetsResponse extends CoreResponse {

    private List<Target> targetList;


    public GetAllTargetsResponse(List<Target> targetList) {
        this.targetList = targetList;
    }

    public List<Target> getTargetList() {
        return targetList;
    }
}
