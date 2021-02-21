package java2.application_target_list.core.requests.target;


public class ChangeTargetDeadlineRequest {

    private Long targetIdToChange;
    private Long newTargetDeadline;

    public ChangeTargetDeadlineRequest() {
    }

    public ChangeTargetDeadlineRequest(Long targetIdToChange, Long newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDeadline = newTargetDeadline;
    }

    public void setTargetIdToChange(Long targetIdToChange) {
        this.targetIdToChange = targetIdToChange;
    }

    public void setNewTargetDeadline(Long newTargetDeadline) {
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public Long getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
