package internet_store.core.responses;

import java.util.List;

public class ChangeTargetDescriptionResponse extends CoreResponse {

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
