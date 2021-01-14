package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class ChangeTargetNameResponse extends TargetCoreResponse {

    private Long targetIdToChange;
    private String newTargetName;

    public ChangeTargetNameResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public ChangeTargetNameResponse(Long targetIdToChange, String newTargetName) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetName = newTargetName;
    }

    public String getNewTargetName() {
        return newTargetName;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }
}
