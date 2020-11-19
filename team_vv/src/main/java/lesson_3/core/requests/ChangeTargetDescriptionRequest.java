package lesson_3.core.requests;

public class ChangeTargetDescriptionRequest {

    private Long targetIdTOChange;
    private String newTargetDescription;

    public ChangeTargetDescriptionRequest(Long targetIdTOChange, String newTargetDescription) {
        this.targetIdTOChange = targetIdTOChange;
        this.newTargetDescription = newTargetDescription;
    }

    public Long getTargetIdTOChange() {
        return targetIdTOChange;
    }

    public String getNewTargetDescription() {
        return newTargetDescription;
    }
}
