package java2.application_target_list.core.requests.target;

public class ChangeTargetDescriptionRequest {

    private Long targetIdToChange;
    private String newTargetDescription;

    public ChangeTargetDescriptionRequest(Long targetIdToChange, String newTargetDescription) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDescription = newTargetDescription;
    }

    public ChangeTargetDescriptionRequest() {
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public String getNewTargetDescription() {
        return newTargetDescription;
    }

    public void setTargetIdToChange(Long targetIdToChange) {
        this.targetIdToChange = targetIdToChange;
    }

    public void setNewTargetDescription(String newTargetDescription) {
        this.newTargetDescription = newTargetDescription;
    }
}
