package application_target_list.core.responses;

import java.util.List;

public class ChangeTargetNameResponse extends CoreResponse{

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
