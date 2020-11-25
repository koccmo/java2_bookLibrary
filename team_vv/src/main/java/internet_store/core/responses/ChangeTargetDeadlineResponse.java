package internet_store.core.responses;

import java.util.List;

public class ChangeTargetDeadlineResponse extends CoreResponse {

    private Long targetIdToChange;
    private int newTargetDeadline;

    public ChangeTargetDeadlineResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public ChangeTargetDeadlineResponse(Long targetIdToChange, int newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public int getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
