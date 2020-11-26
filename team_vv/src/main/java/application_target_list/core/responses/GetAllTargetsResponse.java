package application_target_list.core.responses;



import application_target_list.core.database.Target;
import java.util.List;


public class GetAllTargetsResponse extends CoreResponse{

    private final List<Target> targetList;


    public GetAllTargetsResponse(List<Target> targetList) {
        this.targetList = targetList;
    }

    public List<Target> getTargetList() {
        return targetList;
    }
}
