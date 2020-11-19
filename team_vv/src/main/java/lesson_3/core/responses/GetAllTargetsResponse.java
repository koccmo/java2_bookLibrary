package lesson_3.core.responses;



import lesson_3.Target;

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
