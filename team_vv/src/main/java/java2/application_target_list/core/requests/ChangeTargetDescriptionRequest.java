package java2.application_target_list.core.requests;

public class ChangeTargetDescriptionRequest {

    private Long targetIdToChange;
    private String newTargetDescription;

    public ChangeTargetDescriptionRequest(Long targetIdToChange, String newTargetDescription) {
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
