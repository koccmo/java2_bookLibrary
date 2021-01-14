package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class ChangeTargetDescriptionResponse extends TargetCoreResponse {

    private Long targetIdToChange;
    private String newTargetDescription;

    public ChangeTargetDescriptionResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public ChangeTargetDescriptionResponse(Long targetIdToChange, String newTargetDescription) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDescription = newTargetDescription;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public String getNewTargetDescription() {
        return newTargetDescription;
    }
}
