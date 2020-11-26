package application_target_list.core.responses;


import application_target_list.core.database.Target;
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
