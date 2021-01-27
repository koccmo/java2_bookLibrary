package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class ChangeTargetDeadlineResponse extends TargetCoreResponse {

    private Long targetIdToChange;
    private Long newTargetDeadline;

    public ChangeTargetDeadlineResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public ChangeTargetDeadlineResponse(Long targetIdToChange, Long newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public Long getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
