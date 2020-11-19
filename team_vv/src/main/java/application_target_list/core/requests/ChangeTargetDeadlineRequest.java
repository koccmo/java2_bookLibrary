package application_target_list.core.requests;

public class ChangeTargetDeadlineRequest {

    private Long targetIdToChange;
    private int newTargetDeadline;

    public ChangeTargetDeadlineRequest(Long targetIdToChange, int newTargetDeadline) {
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
